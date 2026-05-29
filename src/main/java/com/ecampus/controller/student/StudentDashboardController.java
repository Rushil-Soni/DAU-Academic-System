package com.ecampus.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecampus.model.Users;
import com.ecampus.repository.UserRepository;

@Controller
@RequestMapping("/student")
public class StudentDashboardController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "student-dashboard";
    }

    @GetMapping("/change-password")
    public String changePassword() {
        return "student/change-password";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam String newPassword,
                                @RequestParam String confirmPassword,
                                Authentication authentication,
                                RedirectAttributes redirectAttributes) {

        String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$";

        // Check match
        if (!newPassword.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "Passwords do not match");
            return "redirect:/student/change-password";
        }

        // Check strength
        if (!newPassword.matches(pattern)) {
            redirectAttributes.addFlashAttribute("error",
                    "Password must have 8+ chars, uppercase, lowercase, number and special character");
            return "redirect:/student/change-password";
        }

        // Encrypt password using BCrypt
        String encodedPassword = passwordEncoder.encode(newPassword);

        String username = authentication.getName();
        Users user = userRepo.findByUname(username).orElseThrow(() -> new RuntimeException("User not found"));

        // Save password
        user.setPassword(encodedPassword);
        userRepo.save(user);

        redirectAttributes.addFlashAttribute("success", "Password changed successfully");

        return "redirect:/student/change-password";
    }
}
