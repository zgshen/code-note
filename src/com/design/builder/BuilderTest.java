package com.design.builder;

import com.design.builder.inner.ComputerInner;

/**
 * @author nathan
 * @date 2020/8/22 17:35
 * @desc BuilderTest
 */
public class BuilderTest {
    public static void main(String[] args) {
        ComputerDirector director = new ComputerDirector();//指导器

        ComputerBuilder macBuilder = new MacComputerBuilder("I5处理器","三星125");
        Computer macComputer = director.makeComputer(macBuilder);//传入子类执行set属性//指导组装
        System.out.println(macComputer);//建造者实例

        ComputerBuilder lenovoBuilder=new LenovoComputerBuilder("I7处理器","海力士222");
        Computer lenovoComputer = director.makeComputer(lenovoBuilder);
        System.out.println(lenovoComputer);

        ComputerInner computerInner = new ComputerInner.Builder("因特尔", "三星")
                .setDisplay("三星24寸")
                .setKeyboard("罗技")
                .setUsbCount(2)
                .build();
        System.out.println(computerInner);
        /**
         * 参考 https://zhuanlan.zhihu.com/p/58093669
         */
    }
}
