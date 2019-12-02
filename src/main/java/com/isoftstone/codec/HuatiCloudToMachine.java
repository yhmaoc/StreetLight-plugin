package com.isoftstone.codec;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.isoftstone.lampctl.HuatiLampCtl;
import com.isoftstone.lampctl.LampCtl;
import com.isoftstone.lampctl.constant.HuatiConstant;
import com.isoftstone.lampctl.params.ControlParam;
import com.isoftstone.utility.Utilty;

/**
 * ClassName: HuatiCloudToMachine
 * Package: com.isoftstone.codec
 * Description:
 *
 * @Date: 2019/11/26 15:40
 * @Author: softbaddog@sina.com
 */
public class HuatiCloudToMachine extends AbsCloudToMachine {

    private static HuatiCloudToMachine instance = null;

    public static HuatiCloudToMachine getInstance(ObjectNode input) {
        if (instance == null) {
            instance = new HuatiCloudToMachine();
        }

        instance.setInput(input);

        //解析OC下发的参数
        instance.setEncodeParam(input);
        return instance;
    }

    @Override
    public byte[] cmd() {
        LampCtl lampCtl = HuatiLampCtl.getInstance();
        JsonNode paras = encodeParam.getParas();

        //封装下发参数
        ControlParam param = getSendParam();
        switch (encodeParam.getCmd()) {
            case "CHECK_TIME":
                return null;
            case "SET_SWITCH":
                if ("open".equals(paras.get("cmd").asText())) {
                    return lampCtl.on(param);
                } else if ("close".equals(paras.get("cmd").asText())) {
                    return lampCtl.off(param);
                }
                break;
            case "SET_DIMMING":
                return lampCtl.dim(param);
            case "GET_SWITCH_STATUS":
                return lampCtl.getStatus();
            case "GET_ENERGY":
                return null;
            case "SEND_CMD"://透传
                return lampCtl.passthrough(param);
            default:
        }
        return null;
    }

    /**
     * OC收到华体NB设备上报数据后，响应设备
     * @return
     */
    @Override
    public byte[] ack() {
        if(0 == encodeParam.getErrcode()){
            return Utilty.getByteArray(HuatiConstant.REPORT_RESP);
        }
        return null;
    }
}
