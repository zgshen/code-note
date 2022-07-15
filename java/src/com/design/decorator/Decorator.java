package com.design.decorator;

/**
 * 装饰者模式
 * 动态地给一个对象添加一些额外的职责。就增加功能来说，装饰器模式相比生成子类更为灵活。
 */
public class Decorator {
    public static void main(String[] args) {
        Component commonComponent = new CommonComponent();
        DecoratorImpl decorator = new DecoratorImpl(commonComponent);

        commonComponent.action();
        decorator.action();
    }
}

/**
 * 接口
 */
interface Component {
    void action();
}

/**
 * 普通具体对象，被装饰者
 */
class CommonComponent implements Component {
    @Override
    public void action() {
        System.out.println("common action.");
    }
}

/**
 * 抽象装饰者
 */
abstract class AbsDecorator implements Component{
    Component component;

    public AbsDecorator(Component component) {
        this.component = component;
    }

    @Override
    public void action() {
        component.action();
    }
}

/**
 * 具体装饰者
 */
class DecoratorImpl extends AbsDecorator {

    public DecoratorImpl(Component component) {
        super(component);
    }

    @Override
    public void action() {
        component.action();
        otherAction();
    }

    public void otherAction() {
        System.out.println("other action.");
    }
}
