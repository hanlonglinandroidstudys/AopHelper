package hanlonglin.com.aop_tools.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 主线程方法
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface Main {

}
