package io.patamon.apt;

import io.patamon.apt.lombok.annotation.Getter;
import io.patamon.apt.lombok.annotation.Setter;

/**
 * Desc:
 * <p>
 * Mail: chk@terminus.io
 * Created by IceMimosa
 * Date: 2018/2/4
 */
public class TestLombokDemo {

    @Getter
    @Setter
    private String name;

    public static void main(String[] args) {
        // TestLombokDemo d = new TestLombokDemo();
        // d.setName("aaa");
        // System.out.println(d.getName());
    }
}
