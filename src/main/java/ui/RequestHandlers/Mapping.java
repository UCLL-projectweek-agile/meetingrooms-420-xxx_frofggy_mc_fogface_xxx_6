package ui.RequestHandlers;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.*;  

@Retention(RetentionPolicy.RUNTIME)
@Target(TYPE)
public @interface Mapping {
	String value() default "";
}
