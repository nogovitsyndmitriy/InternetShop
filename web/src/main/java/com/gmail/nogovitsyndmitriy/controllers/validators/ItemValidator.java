package com.gmail.nogovitsyndmitriy.controllers.validators;

import com.gmail.nogovitsyndmitriy.dao.ItemDao;
import com.gmail.nogovitsyndmitriy.service.model.ItemDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ItemDao.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "item.empty.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "item.empty.description");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "item.empty.price");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "uniqueNumber", "item.empty.number");

        ItemDto item = (ItemDto) o;

        if (item.getPrice() == null && item.getPrice().signum() == -1) {
            errors.rejectValue("price", "item.price.invalid");
        }
    }
}
