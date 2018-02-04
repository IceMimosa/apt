package io.patamon.apt;

import io.patamon.apt.lombok.annotation.Getter;

/**
 * Desc:
 * <p>
 * Mail: chk@terminus.io
 * Created by IceMimosa
 * Date: 2018/2/4
 */
public class TestLombok {

    @Getter
    private String name;

    public static void main(String[] args) throws Exception {
//        com.sun.tools.javac.Main.main(new String[] {"-proc:only",
//                "-processor", "io.patamon.apt.processor.lombok.LombokProcessor",
//                "/Users/icemimosa/Documents/github/AskMisa/apt/apt-demo/src/main/java/io/patamon/apt/TestLombok.java"});

        TestLombok l = new TestLombok();
        l.name = "aaa";
        System.out.println(l.getName());
    }

    public String a() {
        return this.name;
    }
}
