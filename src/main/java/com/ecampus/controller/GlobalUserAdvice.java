package com.ecampus.controller;

import com.ecampus.service.GlobalConstantsService;
import com.ecampus.session.SessionConstants;
import com.ecampus.util.LoggedUser;
import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalUserAdvice {

    private final GlobalConstantsService globalConstantsService;

    public GlobalUserAdvice(GlobalConstantsService globalConstantsService) {
        this.globalConstantsService = globalConstantsService;
    }

    @ModelAttribute
    public void addGlobalAttributes(Model model,HttpSession session) {

        LoggedUser currentUser = session == null
                ? null
                : (LoggedUser) session.getAttribute(SessionConstants.CURRENT_USER);

        model.addAttribute("currentUser", currentUser);
        model.addAttribute(
                "currentAcademicYearName",
                getDisplayValue(globalConstantsService.getCurrentAcademicYearName()));

        model.addAttribute(
                "currentTermName",
                getDisplayValue(globalConstantsService.getCurrentTermName()));
    }

    private String getDisplayValue(String value) {
        if (value == null || value.isBlank()) {
            return "Not Set";
        }
        return value;
    }
}
