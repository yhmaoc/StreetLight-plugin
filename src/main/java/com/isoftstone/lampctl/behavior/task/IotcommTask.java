package com.isoftstone.lampctl.behavior.task;

/**
 * ClassName: IotcommTask
 * Package: com.isoftstone.lampctl.taskbehavior
 * Description:
 *
 * @Date: 2019/11/26 14:22
 * @Author: softbaddog@sina.com
 */
public class IotcommTask implements TaskBehavior {
    @Override
    public void task()  {
        System.out.println("--IOTCOMM task---");
    }
}
