package com.gmail.nogovitsyndmitriy.controllers.handlers;

import com.gmail.nogovitsyndmitriy.config.PageProperties;
import com.gmail.nogovitsyndmitriy.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class AppExceptionHandler {
    private final PageProperties pageProperties;

    @Autowired
    public AppExceptionHandler(PageProperties pageProperties) {
        this.pageProperties = pageProperties;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String accessDeniedException(HttpServletRequest request, AccessDeniedException e) {
        return pageProperties.getErrorsPagePath();
    }

    @ExceptionHandler(ServiceException.class)
    public String serviceException(HttpServletRequest request, ServiceException ex) {
        request.setAttribute("errCode", ex.getErrCode());
        request.setAttribute("errMsg", ex.getErrMsg());
        return pageProperties.getErrorsPagePath();
    }

    @ExceptionHandler(Exception.class)
    public String defaultErrorHandler(HttpServletRequest request, Exception e) {
        request.setAttribute("exception", e);
        request.setAttribute("url", request.getRequestURL());
        return pageProperties.getErrorsPagePath();
    }
}
