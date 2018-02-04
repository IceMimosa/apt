package io.patamon.apt.processor;

import io.patamon.apt.annotation.TestAnnotation;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

/**
 * Desc: {@link TestAnnotation} 的processor
 *  当前processor的注册方式为:
 *      * 在resources/META-INF/services/javax.annotation.processing.Processor 文件中进行服务注册
 *      * 后续会采用谷歌的auto-service: https://github.com/google/auto
 *
 * Mail: chk19940609@gmail.com
 * Created by IceMimosa
 * Date: 2018/1/30
 */
@SupportedAnnotationTypes({"io.patamon.apt.annotation.TestAnnotation"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class TestProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement element : annotations) {
            this.processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "HelloWorld, " + element.getSimpleName());
            System.out.println("HelloWorld, " + element.getSimpleName());
        }
        return false;
    }
}
