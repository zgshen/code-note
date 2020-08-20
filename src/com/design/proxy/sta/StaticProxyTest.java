package com.design.proxy.sta;

import com.design.proxy.Subject;
import com.design.proxy.SubjectImpl;

/**
 * @author nathan
 * @date 2020/8/20 22:14
 * @desc StaticProxyTest
 * 静态代理测试
 */
public class StaticProxyTest {

    public static void main(String[] args) {
        /**
         * 代理可在 SubjectImpl 实现类方法前后干一些其他事情
         */
        Subject proxy = new SubjectProxy(new SubjectImpl());
        proxy.doSomething();
    }

}
