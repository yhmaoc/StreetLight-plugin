package com.isoftstone.lampctl.behavior.control;

/**
 * ClassName: OnBehavior
 * Package: com.isoftstone.lampctl.on
 * Description:
 *
 * @Date: 2019/11/26 12:25
 * @Author: softbaddog@sina.com
 */
public interface ControlBehavior {
    byte[] on();
    byte[] off();
    byte[] dim(int value);
}
