package com.gmail.nogovitsyndmitriy.controllers.validators;

import com.gmail.nogovitsyndmitriy.service.model.NewsDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class NewsValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return NewsDto.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "news.empty.title");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "news.empty.content");


    }
}
