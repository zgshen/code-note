package com.swordoffer.offer30;

import java.util.Stack;

/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
 *
 * 示例:
 *
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.min();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.min();   --> 返回 -2.
 *  
 *
 * 提示：
 * 各函数的调用总次数不超过 20000 次
 */
public class MinStack {

    Stack<Integer> a, b;

    /** initialize your data structure here. */
    public MinStack() {
        a = new Stack<>();//数据栈
        b = new Stack<>();//辅助栈，用于记录最小值
    }

    public void push(int x) {
        a.add(x);
        if (b.isEmpty() || b.peek()>=x)
            b.add(x);
    }

    public void pop() {
        //必须用 equals，Integer 用缓存池，范围[-128,127]
        if (a.pop().equals(b.peek()))
            b.pop();//a 出栈与 b 对比，若在 b 中是当前最小，b 也要出栈
    }

    public int top() {
        return a.peek();
    }

    public int min() {
        return b.peek();
    }

}
