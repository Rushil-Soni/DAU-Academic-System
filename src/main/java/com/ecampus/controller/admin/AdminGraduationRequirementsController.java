package com.ecampus.controller.admin;

import com.ecampus.dto.BatchViewDTO;
import com.ecampus.dto.OverallCourseTypeProgressDTO;
import com.ecampus.dto.StudentGraduationRequirementsAdminDTO;
import com.ecampus.model.Batches;
import com.ecampus.model.Students;
import com.ecampus.repository.BatchesRepository;
import com.ecampus.service.StudentGraduationRequirementsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/admin/graduation-requirements/batches")
public class AdminGraduationRequirementsController {

    @Autowired
    private StudentGraduationRequirementsService srgService;

    @Autowired
    private BatchesRepository batchesRepo;

    @Autowired
    private StudentGraduationRequirementsService graduationReqService;

    /**
     * Show all batches for selecting graduation requirements
     */
    @GetMapping
    public String listBatches(Model model) {
        List<Object[]> rows = batchesRepo.getAllBatchesDetailsRaw();

        List<BatchViewDTO> batches = rows.stream()
                .map(r -> new BatchViewDTO(
                        ((Number) r[0]).longValue(),
                        (String) r[1],
                        (String) r[2],
                        (String) r[3],
                        ((Number) r[4]).intValue(),
                        r[5] != null ? ((Number) r[5]).intValue() : null
                )).toList();

        model.addAttribute("batches", batches);
        return "admin/graduation-requirements/batches";
    }

    /**
     * Show graduation requirements for all students in a specific batch
     */
    @GetMapping("/{batchId}")
    public String showBatchGraduationRequirements(
            @PathVariable Long batchId,
            Model model) {

        // Get batch details
        Batches batch = batchesRepo.findById(batchId)
                .orElseThrow(() -> new IllegalArgumentException("Batch not found: " + batchId));

        // Get all students' graduation requirements for this batch
        List<StudentGraduationRequirementsAdminDTO> studentRequirements = 
                graduationReqService.buildBatchGraduationRequirements(
                        batchId, batch.getSchemeId(), batch.getSplid());

        model.addAttribute("batch", batch);
        model.addAttribute("studentRequirements", studentRequirements);
        
        // Calculate overall batch statistics
        if (!studentRequirements.isEmpty()) {
            long completedCount = studentRequirements.stream()
                    .filter(s -> "Completed".equals(s.getGraduationStatus()))
                    .count();
            long totalStudents = studentRequirements.size();
            double completionPercentage = (completedCount * 100.0) / totalStudents;
            
            model.addAttribute("completedCount", completedCount);
            model.addAttribute("totalStudents", totalStudents);
            model.addAttribute("completionPercentage", completionPercentage);
            // model.addAttribute("completionPercentage", String.format("%.1f", completionPercentage));
        }

        return "admin/graduation-requirements/batch-details";
    }

        @GetMapping("/{batchid}/{stdinstid}")
        public String graduationRequirements(
                @PathVariable Long batchid,
                @PathVariable Long stdinstid,
                Model model) {
                model.addAttribute("username", stdinstid);
                StudentContext context = buildStudentContext(model);

                List<OverallCourseTypeProgressDTO> progressList = srgService
                                .buildOverallProgress(context.stdid(), context.schemeId(), context.splid());

                long totalExtraCourses = progressList.stream().mapToLong(OverallCourseTypeProgressDTO::getExtraCourses).sum();
                long totalCompletedCourses = progressList.stream().mapToLong(OverallCourseTypeProgressDTO::getCompletedCourses).sum() - totalExtraCourses;
                long totalRequiredCourses = progressList.stream().mapToLong(OverallCourseTypeProgressDTO::getMinCourses).sum();
                long totalRemainingCourses = Math.max(0L, totalRequiredCourses - totalCompletedCourses);

                BigDecimal totalExtraCredits = progressList.stream()
                                .map(OverallCourseTypeProgressDTO::getExtraCredits)
                                .reduce(BigDecimal.ZERO, BigDecimal::add);
                BigDecimal totalCompletedCredits = progressList.stream()
                                .map(OverallCourseTypeProgressDTO::getCompletedCredits)
                                .reduce(BigDecimal.ZERO, BigDecimal::add)
                                .subtract(totalExtraCredits);
                BigDecimal totalRequiredCredits = progressList.stream()
                                .map(OverallCourseTypeProgressDTO::getMinCredits)
                                .reduce(BigDecimal.ZERO, BigDecimal::add);
                BigDecimal totalRemainingCredits = totalRequiredCredits.subtract(totalCompletedCredits).max(BigDecimal.ZERO);

                long fullySatisfiedTypes = progressList.stream()
                                .filter(OverallCourseTypeProgressDTO::isFullySatisfied)
                                .count();

                model.addAttribute("progressList", progressList);
                model.addAttribute("totalCompletedCourses", totalCompletedCourses);
                model.addAttribute("totalRequiredCourses", totalRequiredCourses);
                model.addAttribute("totalRemainingCourses", totalRemainingCourses);
                model.addAttribute("totalCompletedCredits", formatDecimal(totalCompletedCredits));
                model.addAttribute("totalRequiredCredits", formatDecimal(totalRequiredCredits));
                model.addAttribute("totalRemainingCredits", formatDecimal(totalRemainingCredits));
                model.addAttribute("typesFullySatisfied", fullySatisfiedTypes);
                model.addAttribute("totalTypes", progressList.size());

                return "admin/graduation-requirements/student-requirements";
        }

        private StudentContext buildStudentContext(Model model) {
                String username = model.getAttribute("username").toString();
                Long stdid = srgService.getStudentIdByUsername(username);
                Students student = srgService.getStudent(stdid);

                Long batchId = student.getStdbchid();
                Batches batch = srgService.getBatch(batchId);
                String currentSemester = srgService.getCurrentSemesterName(batchId);
                String studentName = (student.getStdfirstname() != null ? student.getStdfirstname() : "")
                                + (student.getStdlastname() != null ? " " + student.getStdlastname() : "");

                if (model != null) {
                        model.addAttribute("studentName", studentName.trim());
                        model.addAttribute("studentId", student.getStdinstid());
                        model.addAttribute("batchName", batch.getBchname());
                        model.addAttribute("currentSemester", currentSemester);
                }

                return new StudentContext(stdid, batchId, batch.getSchemeId(), batch.getSplid(), currentSemester);
        }

        private String formatDecimal(BigDecimal value) {
                BigDecimal normalized = (value != null ? value : BigDecimal.ZERO).stripTrailingZeros();
                if (normalized.scale() < 0) {
                        normalized = normalized.setScale(0);
                }
                return normalized.toPlainString();
        }

        private record StudentContext(Long stdid, Long batchId, Long schemeId, Long splid, String currentSemester) {
        }
}
