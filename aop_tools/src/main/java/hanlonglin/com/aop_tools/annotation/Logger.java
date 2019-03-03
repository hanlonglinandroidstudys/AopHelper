package hanlonglin.com.aop_tools.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

/**
 * 日志打印注解
 * 需要传入日志级别参数
 */
public @interface Logger {
    int value();
}
