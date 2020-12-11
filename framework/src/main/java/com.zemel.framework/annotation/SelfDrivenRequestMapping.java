package com.zemel.framework.annotation;


import java.lang.annotation.*;

/**
 * @Author: zemel
 * @Date: 2020/1/16 23:24
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SelfDrivenRequestMapping {
    int value() default 0;
}
