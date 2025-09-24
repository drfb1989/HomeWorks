package app;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Retention(RetentionPolicy.RUNTIME)  // доступно під час виконання
@Target(ElementType.METHOD)          // можна вішати тільки на методи
public @interface MethodInfo {
    String name();
    String returnType();
    String description();
}
