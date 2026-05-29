package com.ecampus.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecampus.model.*;
import com.ecampus.repository.*;
import com.ecampus.service.*;

@Controller
@RequestMapping("/forgot-password")
public class ForgotPasswordController {

    @Autowired
    private ForgotPasswordService forgotService;
    
    @Autowired 
    private UserRepository userRepo; 

    @GetMapping
    public String showForgotPage() {
        return "forgot-password";
    }

    @PostMapping("/send")
    public String processForgot(@RequestParam String username, RedirectAttributes ra) {
        String token = forgotService.createResetToken(username);
        
        if (token != null) {
            Optional<Users> userOptional = userRepo.findWithName(username);
            if (userOptional.isEmpty()) {
                ra.addFlashAttribute("error", "User not found.");
                return "redirect:/forgot-password";
            }
            Users user = userOptional.get();
            forgotService.sendEmail(user.getUemail(), token); 
            ra.addFlashAttribute("success", "A reset link has been sent to your registered email.");

        } else {
            ra.addFlashAttribute("error", "Username not found.");
        }

        return "redirect:/forgot-password";
    }

    @GetMapping("/reset")
    public String showResetPage(@RequestParam String token, Model model) {
        String username = forgotService.validateTokenAndGetUsername(token);

        if (username == null) {
            return "redirect:/login?error=InvalidOrExpiredToken";
        }
        model.addAttribute("token", token);
        return "reset-password";
    }

    @PostMapping("/update")
    public String updatePassword(@RequestParam String token, 
                                 @RequestParam String password, 
                                 @RequestParam String confirmPassword,
                                 RedirectAttributes ra) {
        
        String username = forgotService.validateTokenAndGetUsername(token);
        if (username == null) return "redirect:/login?error=sessionExpired";

        String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$";

        if (!password.equals(confirmPassword)) {
            ra.addFlashAttribute("error", "Passwords do not match!");
            return "redirect:/forgot-password/reset?token=" + token;
        }

        if (!password.matches(pattern)) {
            ra.addFlashAttribute("error",
                    "Password must have 8+ chars, uppercase, lowercase, number and special character");
            return "redirect:/forgot-password/reset?token=" + token;
        }

        forgotService.resetPassword(username, password, token);
        return "redirect:/login";
    }
}
