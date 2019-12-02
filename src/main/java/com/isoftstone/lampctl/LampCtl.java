package com.isoftstone.lampctl;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.isoftstone.lampctl.behavior.ack.AckBehavior;
import com.isoftstone.lampctl.behavior.control.ControlBehavior;
import com.isoftstone.lampctl.behavior.passthrough.PassthroughBehavior;
import com.isoftstone.lampctl.behavior.query.QueryBehavior;
import com.isoftstone.lampctl.behavior.report.ReportBehavior;
import com.isoftstone.lampctl.behavior.task.TaskBehavior;
import com.isoftstone.lampctl.params.ControlParam;

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
    PassthroughBehavior passthroughBehavior;

    public byte[] passthrough(ControlParam param) {
        return passthroughBehavior.send(param);
    }

    /**
     * 开灯操作
     * @param param
     * @return
     */
    public byte[] on(ControlParam param) {
        return controlBehavior.on(param);
    }

    /**
     * 关灯操作
     * @param param
     * @return
     */
    public byte[] off(ControlParam param) {
        return controlBehavior.off(param);
    }

    /**
     * 调光操作
     * @param param
     * @return
     */
    public byte[] dim(ControlParam param) {
        return controlBehavior.dim(param);
    }

    public byte[] getStatus() {
        return queryBehavior.getStatus();
    }

    public void task() {
        taskBehavior.task();
    }

    public ObjectNode ack(String[] dataArray) { return ackBehavior.cmdRsp(dataArray); }

    public ArrayNode report(String[] dataArray) { return reportBehavior.report(dataArray); }

    public ArrayNode passthroughReport(String dataArray) { return reportBehavior.passthroughReport(dataArray); }

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
