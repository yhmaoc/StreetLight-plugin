package com.isoftstone.lampctl;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.isoftstone.lampctl.behavior.ack.AckBehavior;
import com.isoftstone.lampctl.behavior.control.ControlBehavior;
import com.isoftstone.lampctl.behavior.query.QueryBehavior;
import com.isoftstone.lampctl.behavior.report.ReportBehavior;
import com.isoftstone.lampctl.behavior.task.TaskBehavior;

/**
 * ClassName: Light
 * Package: com.isoftstone.lampctl.streetlight
 * Description:
 *
 * @Date: 2019/11/26 11:20
 * @Author: softbaddog@sina.com
 */
public abstract class LampCtl {

    ControlBehavior controlBehavior;
    QueryBehavior queryBehavior;
    TaskBehavior taskBehavior;
    ReportBehavior reportBehavior;
    AckBehavior ackBehavior;

    public void on() {
        controlBehavior.on();
    }

    public void off() {
        controlBehavior.off();
    }

    public void dim(int value) {
        controlBehavior.dim(value);
    }

    public void getStatus() {
        queryBehavior.getStatus();
    }

    public void task() {
        taskBehavior.task();
    }

    public ObjectNode ack(String[] dataArray) { return ackBehavior.cmdRsp(dataArray); }

    public ArrayNode report(String[] dataArray) { return reportBehavior.report(dataArray); }

    public abstract void display();

    public void setControlBehavior(ControlBehavior mControlBehavior) {
        this.controlBehavior = mControlBehavior;
    }

    public void setQueryBehavior(QueryBehavior queryBehavior) {
        this.queryBehavior = queryBehavior;
    }

    public void setReportBehavior(ReportBehavior reportBehavior) {this.reportBehavior = reportBehavior; }

    public void setTaskBehavior(TaskBehavior taskBehavior) {
        this.taskBehavior = taskBehavior;
    }
}
