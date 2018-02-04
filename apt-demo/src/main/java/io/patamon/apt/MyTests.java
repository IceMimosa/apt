package io.patamon.apt;

import io.patamon.apt.annotation.TestAnnotation;

/**
 * Desc:
 * <p>
 * Mail: chk@terminus.io
 * Created by IceMimosa
 * Date: 2018/1/30
 */
@TestAnnotation(name = "TestAnnotationName")
public class MyTests {

    public static void main(String[] args) throws Exception {
        com.sun.tools.javac.Main.main(new String[] {"-proc:only",
                "-processor", "io.patamon.apt.processor.TestProcessor",
                "/Users/icemimosa/Documents/github/AskMisa/apt/apt-demo/src/main/java/io/patamon/apt/MyTests.java"});
    }

}
