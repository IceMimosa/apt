package io.patamon.apt.lombok.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Desc: 生成getter方法
 * <p>
 * Mail: chk@terminus.io
 * Created by IceMimosa
 * Date: 2018/2/4
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
@Documented
public @interface Getter {



}
