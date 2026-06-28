package com.ecampus.auth.autoconfig;

import com.ecampus.auth.config.RoleSecurityProperties;
import com.ecampus.auth.service.DatabaseUserDetailsService;
import com.ecampus.auth.service.RoleAwareSuccessHandler;
import com.ecampus.auth.user.UserDetailsRepository;
import com.ecampus.repository.UserRepository;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@EnableConfigurationProperties(RoleSecurityProperties.class)
public class AuthAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @ConditionalOnMissingBean(UserDetailsService.class)
    public UserDetailsService userDetailsService(UserDetailsRepository repository) {
        return new DatabaseUserDetailsService(repository);
    }

    @Bean
    @ConditionalOnMissingBean(AuthenticationSuccessHandler.class)
    public AuthenticationSuccessHandler authenticationSuccessHandler(
            RoleSecurityProperties props,
            UserRepository userRepository) {

        return new RoleAwareSuccessHandler(
                props.getDefaultSuccessUrls(),
                userRepository);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            AuthenticationSuccessHandler authenticationSuccessHandler,
            RoleSecurityProperties props) throws Exception {

        http.authorizeHttpRequests(auth -> {

            auth.requestMatchers(
                    "/",
                    "/login",
                    "/forgot-password/**",
                    "/error",
                    "/h2-console/**").permitAll();

            auth.requestMatchers(
                    "/css/**",
                    "/js/**",
                    "/images/**",
                    "/webjars/**",
                    "/favicon.ico").permitAll();

            props.getRoleRules().forEach((role, paths) -> {
                paths.forEach(path -> auth.requestMatchers(path).hasAuthority(role));
            });

            auth.anyRequest().authenticated();
        });

        http.formLogin(form -> form
                .loginPage("/login")
                .successHandler(authenticationSuccessHandler)
                .permitAll());

        http.logout(logout -> logout
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("ECAMPUS_SESSION", "JSESSIONID")
                .logoutSuccessUrl("/login?logout")
                .permitAll());

        http.exceptionHandling(exception -> exception
                .accessDeniedHandler((request, response, accessDeniedException) ->
                        response.sendRedirect(request.getContextPath() + "/login?error=forbidden")));

        http.csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**"));

        http.headers(headers -> headers
                .frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    }
}
