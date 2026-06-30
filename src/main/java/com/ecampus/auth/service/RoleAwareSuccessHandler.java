package com.ecampus.auth.service;

import com.ecampus.model.Users;
import com.ecampus.repository.UserRepository;
import com.ecampus.session.SessionConstants;
import com.ecampus.util.LoggedUser;

import jakarta.servlet.http.HttpSession;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Map;

public class RoleAwareSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final Map<String, String> defaultSuccessUrls;
    private final UserRepository userRepository;

    public RoleAwareSuccessHandler(
            Map<String, String> defaultSuccessUrls,
            UserRepository userRepository) {
        this.defaultSuccessUrls = defaultSuccessUrls;
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        loadSessionUser(request, authentication);

        super.onAuthenticationSuccess(request, response, authentication);
    }

    @Override
    protected String determineTargetUrl(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) {

        for (GrantedAuthority authority : authentication.getAuthorities()) {
            String role = authority.getAuthority();

            if (defaultSuccessUrls.containsKey(role)) {
                return defaultSuccessUrls.get(role);
            }
        }

        return "/login?error";
    }

    private void loadSessionUser(HttpServletRequest request, Authentication authentication) {

        HttpSession session = request.getSession(true);

        if (authentication == null ||
                authentication.getName() == null ||
                authentication.getName().isBlank()) {

            session.removeAttribute(SessionConstants.CURRENT_USER);
            return;
        }

        Users user = userRepository.findWithName(authentication.getName()).orElse(null);

        if (user == null ||
                user.getUnivId() == null ||
                user.getUnivId().isBlank()) {

            session.removeAttribute(SessionConstants.CURRENT_USER);
            return;
        }

        LoggedUser l_user = new LoggedUser(user);
        session.setAttribute(SessionConstants.CURRENT_USER, l_user);
    }
}
