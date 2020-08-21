package com.design.strategy;

/**
 * @author nathan
 * @date 2020/8/21 23:35
 * @desc BizUnitService
 * 业务操作
 */
public class BizUnitService {

    public String bizOne(String order) {
        return order + "操作1";
    }
    public String bizTwo(String order) {
        return order + "操作2";
    }
    public String bizThree(String order) {
        return order + "操作3";
    }

}
