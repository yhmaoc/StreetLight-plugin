package com.isoftstone.codec;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.isoftstone.lampctl.HuatiLampCtl;
import com.isoftstone.lampctl.LampCtl;
import org.msgpack.core.MessageBufferPacker;
import org.msgpack.core.MessagePack;

import java.io.IOException;
import java.util.EnumSet;

/**
 * ClassName: HuatiCloudToMachine
 * Package: com.isoftstone.codec
 * Description:
 *
 * @Date: 2019/11/26 15:40
 * @Author: softbaddog@sina.com
 */
public class HuatiCloudToMachine extends AbsCloudToMachine {

    protected JsonNode paras;

    private static HuatiCloudToMachine instance = null;

    public static HuatiCloudToMachine getInstance(ObjectNode input) {
        if (instance == null) {
            instance = new HuatiCloudToMachine();
        }
        instance.setInput(input);
        return instance;
    }

    @Override
    public byte[] cmd() {
        LampCtl lampCtl = HuatiLampCtl.getInstance();
        String method = input.get("method").asText();
        JsonNode paras = input.get("paras");
        switch (method) {
            case "SET_SWITCH":
                if ("open".equals(paras.get("cmd").asText())) {
                    return lampCtl.on();
                } else if ("close".equals(paras.get("cmd").asText())) {
                    return lampCtl.off();
                }
                break;
            case "SET_DIMMING":
                return lampCtl.dim(paras.get("value").asInt());
            default:
                break;
        }
        return null;
    }

    @Override
    public byte[] ack() {
        int hasMore = 0;
        int errcode = input.get("errcode").asInt();
        byte[] ack = new byte[4];
        ack[0] = (byte) 0xAA;
        ack[1] = (byte) 0xAA;
        ack[2] = (byte) errcode;
        ack[3] = (byte) hasMore;
        return ack;
    }
}
