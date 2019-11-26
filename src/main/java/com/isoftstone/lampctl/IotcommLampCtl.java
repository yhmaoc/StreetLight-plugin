package com.isoftstone.lampctl;

import com.isoftstone.lampctl.behavior.control.IotcommControl;
import com.isoftstone.lampctl.behavior.query.IotcommQuery;
import com.isoftstone.lampctl.behavior.task.IotcommTask;

/**
 * ClassName: HuatiLampCtl
 * Package: com.isoftstone.lampctl.streetlight.huati
 * Description:
 *
 * @Date: 2019/11/26 11:51
 * @Author: softbaddog@sina.com
 */
public class IotcommLampCtl extends LampCtl {

    private static IotcommLampCtl instance = null;

    private IotcommLampCtl() {
        controlBehavior = new IotcommControl();
        queryBehavior = new IotcommQuery();
        taskBehavior = new IotcommTask();
    }

    public static IotcommLampCtl getInstance() {
        if (instance == null) {
            instance = new IotcommLampCtl();
        }
        return instance;
    }

    @Override
    public void display() {
        System.out.println("****IOTCOMM****");
    }
}
