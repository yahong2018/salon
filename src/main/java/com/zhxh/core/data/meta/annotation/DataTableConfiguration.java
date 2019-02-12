package com.zhxh.core.data.meta.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataTableConfiguration {
    String value() default "";
    AutoGenerationType keyCreateType() default AutoGenerationType.AUTO_INCREMENT;
}
