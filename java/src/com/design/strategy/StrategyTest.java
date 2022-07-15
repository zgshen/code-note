package com.design.strategy;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author nathan
 * @date 2020/8/21 0:22
 * @desc StrategyTest
 * 策略模式
 */
public class StrategyTest {

    /**
     * 业务逻辑分派Map
     * Function为函数式接口，下面代码中 Function<String, String> 的含义是接收一个String类型的变量，返回一个String类型的结果
     */
    private static Map<String, Function<String, String>> checkResultDispatcher = new HashMap<>();

    private static BizUnitService bizUnitService = new BizUnitService();

    static {
        checkResultDispatcher.put("key_订单1", order -> bizUnitService.bizOne(order));
        checkResultDispatcher.put("key_订单1_订单2", order -> bizUnitService.bizTwo(order));
        checkResultDispatcher.put("key_订单1_订单2_订单3", order -> bizUnitService.bizThree(order));
    }

    public String getCheckResultMuti(String order, int level) {
        //写一段生成key的逻辑：
        String key = getDispatcherKey(order, level);
        Function<String, String> result = checkResultDispatcher.get(key);
        if (result != null) {
            //执行这段表达式获得String类型的结果
            return result.apply(key);
        }
        return "不存在处理逻辑";
    }

    private String getDispatcherKey(String order, int level) {
        StringBuilder key = new StringBuilder("key");
        for (int i = 1; i <= level; i++) {
            key.append("_" + order + i);
        }
        return key.toString();
    }

    public static void main(String[] args) {
        Strategy one = new StrategyOne();
        System.out.println(one.output("one"));

        Strategy two = new StrategyTwo();
        System.out.println(two.output("two"));

        new StrategyTest().getCheckResultMuti("订单",3);
    }
}
