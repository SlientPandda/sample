package com.wuhao.aop.annotation;

import java.lang.annotation.*;

/**
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Documented
public @interface Excel {
    /**
     * 字段名称
     */
    String name() default "";

    /**
     * 字段枚举值描述
     */
    String dictValue() default "";

}
