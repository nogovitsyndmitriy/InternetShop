package com.gmail.nogovitsyndmitriy.controllers.validators;

import com.gmail.nogovitsyndmitriy.dao.CommentDao;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class CommentValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return CommentDao.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "comment.content.empty");
    }
}
