package com.ecampus.controller.faculty;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecampus.dto.StudentGradeDTO;
import com.ecampus.dto.StudentGradeDTOWrapper;
import com.ecampus.model.Users;
import com.ecampus.repository.TermCoursesRepository;
import com.ecampus.session.SessionConstants;
import com.ecampus.service.GradeService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/grades")
public class GradeModRequestController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private TermCoursesRepository termCourseRepository;

    @InitBinder("gradeForm")
    public void initBinder(WebDataBinder binder) {
        binder.setAutoGrowNestedPaths(true);
        binder.setAutoGrowCollectionLimit(1024);
    }

    @PostMapping("/request")
    public String submitGradeModificationRequest(
            @ModelAttribute("gradeForm") StudentGradeDTOWrapper gradeWrapper,
            @RequestParam(name = "deanRemark", required = false) String deanRemark,
            @RequestParam(name = "requestSource", required = false, defaultValue = "MODIFY_GRADE") String requestSource,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        Users currentUser = (Users) session.getAttribute(SessionConstants.CURRENT_USER);
        Long uid = currentUser == null ? null : currentUser.getUid();
        Long trmid = (Long) session.getAttribute("TRMID");
        Long crsid = (Long) session.getAttribute("CRSID");
        Long examTypeId = (Long) session.getAttribute("examTypeId");

        String retryUrl = "REVISE_I_GRADE".equalsIgnoreCase(requestSource)
                ? "redirect:/grades/reviseIGrade/form"
                : "redirect:/grades/update/form";

        if (uid == null || trmid == null || crsid == null || examTypeId == null) {
            redirectAttributes.addFlashAttribute("error", "Session expired or incomplete context.");
            return "redirect:/faculty/dashboard";
        }

        Long tcrid = termCourseRepository.findTcridByCrsidAndTrmid(crsid, trmid);
        if (tcrid == null) {
            redirectAttributes.addFlashAttribute("error", "Invalid term-course combination.");
            return "redirect:/faculty/dashboard";
        }

        List<StudentGradeDTO> gradesToProcess = gradeWrapper.getGradesList().stream()
                .filter(StudentGradeDTO::isSelectedForUpdate)
                .collect(Collectors.toList());

        if (gradesToProcess.isEmpty()) {
            redirectAttributes.addFlashAttribute("warning", "No students were selected for grade modification.");
            return retryUrl;
        }

        boolean invalidSelectionExists = gradesToProcess.stream()
                .anyMatch(dto -> dto.getModifiedGrade() == null || dto.getModifiedGrade().isBlank());

        if (invalidSelectionExists) {
            redirectAttributes.addFlashAttribute("error", "Please select a new grade for every selected student.");
            return retryUrl;
        }

        String defaultDescription = "REVISE_I_GRADE".equalsIgnoreCase(requestSource)
                ? "I Grade revision request"
                : "Grade modification request";

        String requestDescription = deanRemark == null || deanRemark.isBlank()
                ? defaultDescription
                : deanRemark;

        try {
            Long requestId = gradeService.createGradeModificationRequest(
                    uid,
                    tcrid,
                    examTypeId,
                    requestDescription,
                    gradesToProcess);

            redirectAttributes.addFlashAttribute("success",
                    "Grade modification request created successfully. Request ID: " + requestId + ".");

        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error",
                    "Could not create grade modification request: " + ex.getMessage());
            return retryUrl;
        }

        return "redirect:/grades/approval/status";
    }
}
