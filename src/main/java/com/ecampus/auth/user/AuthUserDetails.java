package com.ecampus.auth.user;


public interface AuthUserDetails {
    String getUsername();
    String getpassword();
    String getrole(); // Returns role like "ROLE1"
}