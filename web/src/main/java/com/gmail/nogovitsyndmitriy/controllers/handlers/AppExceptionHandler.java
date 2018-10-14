package com.gmail.nogovitsyndmitriy.controllers.handlers;

import com.gmail.nogovitsyndmitriy.config.PageProperties;
import com.gmail.nogovitsyndmitriy.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@ControllerAdvice
public class AppExceptionHandler {
    private final PageProperties pageProperties;

    @Autowired
    public AppExceptionHandler(PageProperties pageProperties) {
        this.pageProperties = pageProperties;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public String entityNotFoundException(HttpServletRequest request, Exception e, Long id){
        request.setAttribute("id", id);
        request.setAttribute("exception", e);
        request.setAttribute("url", request.getRequestURL());
        return pageProperties.getErrorsPagePath();
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "IOException occurred")
    @ExceptionHandler(IOException.class)
    public String notFoundException() {
        return pageProperties.getErrorsPagePath();
    }

    @ExceptionHandler(Exception.class)
    public String defaultErrorHandler(HttpServletRequest request, Exception e) {
        request.setAttribute("exception", e);
        request.setAttribute("url", request.getRequestURL());
        return pageProperties.getErrorsPagePath();
    }

}
