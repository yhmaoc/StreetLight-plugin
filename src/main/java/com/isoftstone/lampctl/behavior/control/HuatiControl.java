package com.isoftstone.lampctl.behavior.control;

import com.isoftstone.utility.Utilty;

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
    public byte[] on() {
        System.out.println("---Huati on---");
        return Utilty.getByteArray("0000");
    }

    @Override
    public byte[] off() {
        System.out.println("---Huati off---");
        return Utilty.getByteArray("0000");
    }

    @Override
    public byte[] dim(int value) {
        System.out.println("---Huati dim---");
        return Utilty.getByteArray("0000");
    }
}
