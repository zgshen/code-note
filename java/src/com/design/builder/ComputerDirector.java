package com.design.builder;

/**
 * @author nathan
 * @date 2020/8/22 17:34
 * @desc ComputerDirector
 */
public class ComputerDirector {
    public Computer makeComputer(ComputerBuilder builder){
        builder.setUsbCount();
        builder.setDisplay();
        builder.setKeyboard();
        return builder.getComputer();
    }
}
