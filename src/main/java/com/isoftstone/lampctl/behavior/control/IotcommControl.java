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
public class IotcommControl implements ControlBehavior {
    @Override
    public byte[] on() {
        System.out.println("---IOTCOMM on---");
        return Utilty.getByteArray("0000");
    }

    @Override
    public byte[] off() {
        System.out.println("---IOTCOMM off---");
        return Utilty.getByteArray("0000");
    }

    @Override
    public byte[] dim(int value) {
        System.out.println("---IOTCOMM dim---");
        return Utilty.getByteArray("0000");
    }
}
