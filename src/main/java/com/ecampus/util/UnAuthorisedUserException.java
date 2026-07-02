package com.ecampus.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnAuthorisedUserException extends RuntimeException {

    public UnAuthorisedUserException() {
        super("UnAuthorised Access");
    }

    public UnAuthorisedUserException(String message) {
        super(message);
    }
}
