package com.gmail.nogovitsyndmitriy.controllers.validators;

import com.gmail.nogovitsyndmitriy.service.model.OrderDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class OrderValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return OrderDto.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "item.quantity.empty");

        OrderDto orderDto = (OrderDto) o;

        if (orderDto.getQuantity() <= 0){
            errors.rejectValue("quantity","item.quantity.invalid" );
        }
    }
}
