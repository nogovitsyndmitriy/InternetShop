package com.gmail.nogovitsyndmitriy.controllers.validators;


import com.gmail.nogovitsyndmitriy.service.model.BusinessCardDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class BusinessCardValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return BusinessCardDto.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "invalid.card.title");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "workingTelephone", "invalid.working.phone");

        BusinessCardDto businessCardDto = (BusinessCardDto) o;
        if (businessCardDto.getWorkingTelephone().length() >= 20) {
            errors.rejectValue("workingTelephone", "invalid.working.phone");
        }
    }
}
