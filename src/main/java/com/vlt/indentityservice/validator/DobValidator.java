package com.vlt.indentityservice.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class DobValidator implements ConstraintValidator<DobConstraint, LocalDate> {

    private int min;

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        /*Theo nguyên tắc Single Responsibility (Đơn trách nhiệm), hàm này chỉ chịu trách nhiệm kiểm tra xem
        "Tuổi có đủ lớn hay không". Việc "Nó có bị Null hay không" là nhiệm vụ của thằng @NotNull hoặc @NotBlank.*/
        if (Objects.isNull(value))
            return true;

        long years = ChronoUnit.YEARS.between(value, LocalDate.now());

        return years >= min;
    }
    @Override
    public void initialize(DobConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        min = constraintAnnotation.min();
    }

}
