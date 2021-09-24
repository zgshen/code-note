package com.jdk.java12to17;

import org.junit.Assert;
import org.junit.Test;

public class InstanceofExample {

    @Test
    public void instanceofTest() {
        Object obj = "string value";
        //传统写法
        if (obj instanceof String) {
            String str = (String) obj;
            Assert.assertEquals(str, obj);
        }
        //新写法
        if (obj instanceof String s) {
            Assert.assertEquals(s, obj);
        }
        //还可以做其他操作
        if (obj instanceof String s && s.contains("val")) {
            Assert.assertEquals(s, obj);
        }
    }

}
