package io.mangue.aspect.annotations;

import io.mangue.aspect.SubdomainTypes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SubdomainMapping {
    public SubdomainTypes value() default SubdomainTypes.CONSOLE;
}
