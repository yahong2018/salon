package com.zhxh.core.data.validate;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class FixedLengthValidator implements ConstraintValidator<FixedLength, String> {
    private static final String formatter = "长度必须是%d";
    private int length;

    @Override
    public void initialize(FixedLength arg0) {
        this.length = arg0.length();
    }

    @Override
    public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isEmpty(str) || str.length() != this.length) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(String.format(formatter, length)).addConstraintViolation();
            return false;
        }
        return true;
    }
}
