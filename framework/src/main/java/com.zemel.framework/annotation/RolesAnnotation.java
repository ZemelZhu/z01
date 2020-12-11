package com.zemel.framework.annotation;

import com.zemel.data.type.Roles;

import java.lang.annotation.*;

/**
 * @Author: zemel
 * @Date: 2020/5/4 13:35
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface RolesAnnotation {
    Roles roles() default Roles.USER;
}
