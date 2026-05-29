package com.ecampus.controller.admin;

import com.ecampus.model.AcademicYears;
import com.ecampus.repository.AcademicYearsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/admin/academicyears")
public class AcademicYearsController {

    @Autowired
    private AcademicYearsRepository academicYearsRepository;

    // 1. LIST ALL ACADEMIC YEARS
    @GetMapping
    public String listAcademicYears(Model model) {
        model.addAttribute("academicyears",
                academicYearsRepository.findAll()
                        .stream()
                        .sorted((a, b) -> b.getAyrid().compareTo(a.getAyrid())) // DESC
                        .toList()
        );
        return "admin/academic-years";
    }

    // 2. SHOW ADD FORM
    @GetMapping("/add")
    public String showAddForm(Model model) {
        return "admin/academic-year-form";
    }

    // 3. HANDLE ADD FORM SUBMISSION
    @PostMapping("/add")
    public String saveAcademicYear(@RequestParam("startYear") int startYear,
                                   RedirectAttributes redirectAttributes) {

        // 1. Generate ayrname from startYear
        String ayrname = startYear + "-" + String.valueOf(startYear + 1).substring(2);

        // 2. Check if already exists
        Long existingId = academicYearsRepository.findAcademicYearIdByName(ayrname);
        if (existingId != null) {
            redirectAttributes.addFlashAttribute("error", "Academic year " + ayrname + " already exists.");
            return "redirect:/admin/academicyears/add";
        }

        // 3. Fetch current max ID
        Long maxId = academicYearsRepository.findMaxAyrid();
        Long newId = (maxId == null ? 0 : maxId) + 1;

        // 4. Create and save
        AcademicYears ay = new AcademicYears();
        ay.setAyrid(newId);
        ay.setAyrname(ayrname);
        ay.setAyrcreatedat(LocalDateTime.now());
        ay.setAyrlastupdatedat(LocalDateTime.now());
        ay.setAyrcreatedby(1L);
        ay.setAyrlastupdatedby(1L);
        ay.setAyrrowstate(1L);

        academicYearsRepository.save(ay);

        return "redirect:/admin/academicyears";
    }
}
