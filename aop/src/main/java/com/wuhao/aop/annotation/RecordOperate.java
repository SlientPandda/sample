package com.wuhao.aop.annotation;

import com.wuhao.aop.service.Convert;

import java.lang.annotation.*;

/**
 * @Author wuhao51
 * @Description //日志流水切片注释
 * @Date 2022/12/14 17:47
 * @Param
 * @return
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)//运行期也能使用注解
@Documented
public @interface RecordOperate {
    String desc() default "";

    Class<? extends Convert> convert();
}
