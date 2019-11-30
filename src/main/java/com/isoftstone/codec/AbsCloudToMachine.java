package com.isoftstone.codec;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.isoftstone.lampctl.params.ControlParam;
import com.isoftstone.lampctl.params.EncodeParam;

public abstract class AbsCloudToMachine {
    protected ObjectNode input;
    protected byte[] bytes;
    protected EncodeParam encodeParam;

    public AbsCloudToMachine() {

    }

    public void setInput(ObjectNode input) {
        this.input = input;
    }



    public final byte[] handle() {
        String msgType = chop();
        if (msgType.equals("cloudRsp")) {
            bytes = ack();
        } else {
            bytes = cmd();
        }
        return hook();
    }

    public final String chop() {
        System.out.println("------分离头部-------");
        return input.get("msgType").asText();
    }

    public byte[] hook() {
        System.out.println("------钩子函数-------");
        return this.bytes;
    }

    public abstract byte[] ack();

    public abstract byte[] cmd();

    /**
     * 解析OC平台下发给NB设备的参数
     * @param input
     */
    public void setEncodeParam(ObjectNode input) {
        EncodeParam param = new EncodeParam();
        if(null != input.get("identifier")){
            param.setIdentifier(input.get("identifier").asText());
        }

        param.setMsgType(input.get("msgType").asText());
        param.setServiceId(input.get("serviceId").asText());
        param.setCmd(input.get("cmd").asText());
        param.setParas(input.get("paras"));

        if (null != input.get("mid")) {
            param.setMid(input.get("mid").intValue());
        }

        if (null != input.get("hasMore")) {
            param.setHasMore(input.get("hasMore").intValue());
        }

        if (null != input.get("errcode")) {
            param.setErrcode(input.get("errcode").intValue());
        }

        try {
            if(null != input.get("request")){
                param.setRequest(input.get("request").binaryValue());
            }

        }catch (Exception e){
            System.out.println(e);
        }
        this.encodeParam = param;
    }

    /**
     * 获取下发参数
     * @return
     */
    public ControlParam getSendParam(){
        JsonNode paras = encodeParam.getParas();

        ControlParam param = new ControlParam();
        if (null != encodeParam.getMid()) {
            param.setMid(encodeParam.getMid());
        }
        if(null != paras){
            if (null != paras.get("value")) {
                //设置调光值
                param.setValue(paras.get("value").intValue());
            }

            if(null != paras.get("cmd")){
                param.setCmd(paras.get("cmd").asText());
            }

        }

        return param;
    }
}
