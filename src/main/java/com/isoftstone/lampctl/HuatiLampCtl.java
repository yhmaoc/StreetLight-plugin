package com.isoftstone.lampctl;

import com.isoftstone.lampctl.behavior.ack.NormalAck;
import com.isoftstone.lampctl.behavior.control.HuatiControl;
import com.isoftstone.lampctl.behavior.query.HuatiQuery;
import com.isoftstone.lampctl.behavior.report.HuatiReport;
import com.isoftstone.lampctl.behavior.task.NoTask;

/**
 * ClassName: HuatiLampCtl
 * Package: com.isoftstone.lampctl.streetlight.huati
 * Description:
 *
 * @Date: 2019/11/26 11:51
 * @Author: softbaddog@sina.com
 */
public class HuatiLampCtl extends LampCtl {

    private static HuatiLampCtl instance = null;

    private HuatiLampCtl() {
        controlBehavior = new HuatiControl();
        queryBehavior = new HuatiQuery();
        taskBehavior = new NoTask();
        reportBehavior = new HuatiReport();
        ackBehavior = new NormalAck();
    }

    public static HuatiLampCtl getInstance() {
        if (instance == null) {
            instance = new HuatiLampCtl();
        }
        return instance;
    }

    @Override
    public void display() {
        System.out.println("****Huati****");
    }
}
