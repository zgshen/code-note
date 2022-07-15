package com.design.strategy;

/**
 * @author nathan
 * @date 2020/8/21 18:12
 * @desc StrategyOne
 */
public class StrategyOne implements Strategy {
    @Override
    public String output(String input) {
        return "strategy one input is:" + input;
    }
}
