package com.isoftstone.lampctl.behavior.passthrough;


import com.isoftstone.lampctl.params.ControlParam;

/**
 * 透传
 * ClassName: OnBehavior
 * Package: com.isoftstone.lampctl.on
 * Description:
 *
 * @Date: 2019/11/26 12:25
 * @Author: softbaddog@sina.com
 */
public interface PassthroughBehavior {

    byte[] send(ControlParam param);
}
