package com.ecampus.controller.admin;

import com.ecampus.model.Programs;
import com.ecampus.model.Scheme;
import com.ecampus.repository.ProgramsRepository;
import com.ecampus.repository.SchemeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/programs")
public class ProgramsController {

    private final ProgramsRepository programsRepository;
    private final SchemeRepository schemeRepository;

    public ProgramsController(ProgramsRepository programsRepository, SchemeRepository schemeRepository) {
        this.programsRepository = programsRepository;
        this.schemeRepository = schemeRepository;
    }

    @GetMapping
    public String showPrograms(Model model) {
        model.addAttribute("programs", programsRepository.findAll(Sort.by(Sort.Direction.ASC, "prgid")));
        return "admin/programs";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("program", new Programs());
        model.addAttribute("editing", false);
        return "admin/program-form";
    }

    @PostMapping("/save")
    public String saveProgram(@ModelAttribute("program") Programs program,
                              @RequestParam("editing") boolean editing,
                              RedirectAttributes redirectAttributes) {

        if (!editing && programsRepository.existsById(program.getPrgid())) {
            redirectAttributes.addFlashAttribute("error", "Program with this ID already exists.");
            return "redirect:/admin/programs/add";
        }

        program.setPrgcreatedat(LocalDateTime.now());
        program.setPrgrowstate(1L); // Assuming 1 = active

        Programs savedProgram = programsRepository.save(program);

        if (editing) {
            return "redirect:/admin/programs/" + savedProgram.getPrgid();
        } else {
            return "redirect:/admin/programs";
        }
    }

    @GetMapping("/{id}")
    public String viewProgramSchemes(@PathVariable("id") Short programId, Model model) {
        Optional<Programs> program = programsRepository.findById(Long.valueOf(programId));
        if (program.isEmpty()) {
            return "redirect:/admin/programs";
        }
        List<Scheme> schemes = schemeRepository.findByProgram_Prgid(Long.valueOf(programId));
        model.addAttribute("program", program.get());
        model.addAttribute("schemes", schemes);
        return "admin/program-schemes";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Programs program = programsRepository.findById(id).orElseThrow();
        model.addAttribute("program", program);
        model.addAttribute("editing", true);
        return "admin/program-form";
    }
    @GetMapping("/secure")
    @ResponseBody
    public String testSecure() {
        return "You are authenticated!";
    }

}
