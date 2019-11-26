package com.isoftstone.lampctl.behavior.control;

/**
 * ClassName: HuatiOnBehavior
 * Package: com.isoftstone.lampctl.on
 * Description:
 *
 * @Date: 2019/11/26 12:25
 * @Author: softbaddog@sina.com
 */
public class IotcommControl implements ControlBehavior {
    @Override
    public void on() {
        System.out.println("---IOTCOMM on---");
    }

    @Override
    public void off() {
        System.out.println("---IOTCOMM off---");
    }

    @Override
    public void dim(int value) {
        System.out.println("---IOTCOMM dim---");
    }
}
