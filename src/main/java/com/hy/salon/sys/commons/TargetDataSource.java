package com.hy.salon.sys.commons;



import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author RocLiu [apedad@qq.com]
 * @version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TargetDataSource {
    DataSourceKey dataSourceKey() default DataSourceKey.DB_MASTER;
}
