package com.jlopez.bugtracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidInformationException extends RuntimeException {

    private static final long serialVersionUID = -3362202531758557974L;

    public InvalidInformationException() {
        super();
    }

    public InvalidInformationException(String message) {
        super(message);
    }

    public InvalidInformationException(String message, Throwable cause) {
        super(message, cause);
    }

}
