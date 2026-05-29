package com.ecampus.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.util.Map;

public class RoleAwareSuccessHandler
        extends SavedRequestAwareAuthenticationSuccessHandler {

    private final Map<String, String> defaultSuccessUrls;

    public RoleAwareSuccessHandler(Map<String, String> defaultSuccessUrls) {
        this.defaultSuccessUrls = defaultSuccessUrls;
    }

    @Override
    protected String determineTargetUrl(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) {

        // If there was a saved request → use it
        String saved = super.determineTargetUrl(request, response);
        if (saved != null && !saved.equals("/") && !saved.isEmpty()) {
            return saved;
        }

        // -- soft distinguishing logic - Dean or Registrar for time-being
        Object principal = authentication.getPrincipal();
        if (principal instanceof com.ecampus.model.Users user) {
            String role0 = user.getUrole0();
            String type0 = user.getUtype0();

            // Professional Implicit Logic
            if ("U".equals(type0)) {
                if ("FACULTY".equals(role0)) {
                    return "/dean/pending-approvals"; // Dean AP
                } else if ("EMPLOYEE".equals(role0)) {
                    return "/registrar/pending-approvals"; // Registrar
                }
            }
        }

        // Otherwise, resolve based on role
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            String role = authority.getAuthority(); // e.g. ROLE1
            if (defaultSuccessUrls.containsKey(role)) {
                return defaultSuccessUrls.get(role);
            }
        }

        // Final fallback (safe default)
        return "/";
    }
}
