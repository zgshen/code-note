package com.design.strategy;

/**
 * @author nathan
 * @date 2020/8/21 18:14
 * @desc StrategyTwo
 */
public class StrategyTwo implements Strategy {
    @Override
    public String output(String input) {
        return "stratey two input is:" + input;
    }
}
