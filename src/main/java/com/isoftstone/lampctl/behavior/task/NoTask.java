package com.isoftstone.lampctl.behavior.task;

/**
 * ClassName: NoTask
 * Package: com.isoftstone.lampctl.taskbehavior
 * Description:
 *
 * @Date: 2019/11/26 14:23
 * @Author: softbaddog@sina.com
 */
public class NoTask implements TaskBehavior {
    @Override
    public void task() {
        System.out.println("???No Task????");
    }
}
