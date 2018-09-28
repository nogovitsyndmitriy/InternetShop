package com.gmail.nogovitsyndmitriy.validators;

import com.gmail.nogovitsyndmitriy.service.model.UserDto;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Getter
@Component
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "user.password.invalid");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "user.email.invalid");
        UserDto user = (UserDto) o;

        Pattern pattern = Pattern.compile("[aA-zZ]+[0-9]*(\\.)?[aA-zZ]+[0-9]*@[a-z]*.[a-z]{2,3}", Pattern.CASE_INSENSITIVE);
        if (!(pattern.matcher(user.getEmail()).matches())) {
            errors.rejectValue("email", "user.email.invalid");
        }
    }
}
