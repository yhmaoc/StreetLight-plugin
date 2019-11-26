package com.isoftstone.lampctl.behavior.control;

/**
 * ClassName: HuatiOnBehavior
 * Package: com.isoftstone.lampctl.on
 * Description:
 *
 * @Date: 2019/11/26 12:25
 * @Author: softbaddog@sina.com
 */
public class HuatiControl implements ControlBehavior {
    @Override
    public void on() {
        System.out.println("---Huati on---");
    }

    @Override
    public void off() {
        System.out.println("---Huati off---");
    }

    @Override
    public void dim(int value) {
        System.out.println("---Huati dim---");
    }
}
