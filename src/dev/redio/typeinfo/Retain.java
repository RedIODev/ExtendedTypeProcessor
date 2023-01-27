package dev.redio.typeinfo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.TYPE_PARAMETER, ElementType.TYPE })
@Retention(RetentionPolicy.SOURCE)
public @interface Retain {

}