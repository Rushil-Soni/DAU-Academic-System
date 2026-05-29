package com.ecampus.auth.user;

import java.util.Optional;

public interface UserDetailsRepository {
    // Returns the interface instead of the specific AppUser entity
    Optional<? extends AuthUserDetails> findWithName(String uname);
}