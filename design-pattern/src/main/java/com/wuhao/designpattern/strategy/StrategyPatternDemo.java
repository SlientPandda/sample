package com.wuhao.designpattern.strategy;

/**
 * 策略模式实现
 * <p>
 * 策略模式是行为型模式的一种，一个类的行为或其算法可以在运行时进行更改。
 * 但是这种模式会把策略类暴漏给调用者。
 */
public class StrategyPatternDemo {
    public static void main(String[] args) {
        Context context = new Context(new OperationAdd());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationSubtract());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationMultiply());
        System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
    }
}