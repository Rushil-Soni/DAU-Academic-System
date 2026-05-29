package com.ecampus.auth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "security")
public class RoleSecurityProperties {

    private Map<String, List<String>> roleRules = new HashMap<>();

    private Map<String, String> defaultSuccessUrls = new HashMap<>();

    public Map<String, List<String>> getRoleRules() {
        return roleRules;
    }

    public void setRoleRules(Map<String, List<String>> roleRules) {
        this.roleRules = roleRules;
    }

    public Map<String, String> getDefaultSuccessUrls() {
        return defaultSuccessUrls;
    }

    public void setDefaultSuccessUrls(Map<String, String> defaultSuccessUrls) {
        this.defaultSuccessUrls = defaultSuccessUrls;
    }
}
