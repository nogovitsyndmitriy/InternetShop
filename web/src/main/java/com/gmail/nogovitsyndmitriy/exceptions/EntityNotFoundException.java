package com.gmail.nogovitsyndmitriy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entity not found")
public class EntityNotFoundException extends Exception {
private String url;
private String message;
    public EntityNotFoundException(Long id, String url, String message) {
        super("EntityNitFoundException with id:" + id);
        this.url = url;
        this.message = message;
    }
}
