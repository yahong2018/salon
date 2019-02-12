package com.zhxh.core.data.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Constraint(validatedBy = FixedLengthValidator.class)
@Target({java.lang.annotation.ElementType.METHOD,
        java.lang.annotation.ElementType.FIELD})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Documented
public @interface FixedLength {
    String message() default "{com.zhxh.core.data.validate.FixedLength}";

    int length() default 5;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

