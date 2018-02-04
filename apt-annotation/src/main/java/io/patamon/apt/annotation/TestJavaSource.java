package io.patamon.apt.annotation;

import io.patamon.apt.processor.TestJavaSourceProcessor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Desc: 02. 测试生成java源代码的注解, 对应的processor为{@link TestJavaSourceProcessor}
 * <p>
 * Mail: chk19940609@gmail.com
 * Created by IceMimosa
 * Date: 2018/1/30
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
@Documented
public @interface TestJavaSource {

}
