package com.isoftstone.codec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.isoftstone.type.DeviceType;
import com.isoftstone.type.Manufacture;
import org.msgpack.core.MessagePack;
import org.msgpack.core.MessageUnpacker;

import java.io.IOException;
import java.util.EnumMap;

public abstract class AbsMachineToCloud {

    protected EnumMap<Manufacture, Byte> mapManufacture;
    protected EnumMap<DeviceType, Byte> mapDeviceType;

    protected byte[] binaryData;
    protected ObjectNode root;
    protected ObjectMapper mapper;

    protected String msgType;
    protected int hasMore;
    protected int errCode;

    public void setBinaryData(byte[] binaryData) {
        this.binaryData = binaryData;
    }

    public AbsMachineToCloud() {
        mapManufacture = new EnumMap<Manufacture, Byte>(Manufacture.class);
        mapDeviceType = new EnumMap<DeviceType, Byte>(DeviceType.class);

        mapper = new ObjectMapper();
        root = mapper.createObjectNode();
    }

    public ObjectNode handle() {
        String msgType = chop();

        root.put("msgType", msgType);
        if ("deviceRsp".equals(msgType)) {
            root.put("errCode", this.errCode);
            ObjectNode body = ack();
            root.set("body", body);
        } else {
            root.put("hasMore", 0);
            ArrayNode data = report();
            root.set("data", data);
        }

        hook();
        return root;
    }

    /**
     * 处理设备到平台的码流模板，先解析头部，然后根据
     * 本例入参：AA 72 00 00
     * byte[0]--byte[1]:  AA 72 命令头
     * byte[2]:   00 mstType 00表示设备上报数据deviceReq
     * byte[3]:   00 hasMore  0表示没有后续数据，1表示有后续数据，不带按照0处理
     * byte[4]:   00 errCode
     *
     * @return
     */
    public final String chop() {
        System.out.println("------chop()-------");
        switch (binaryData[2]) {
            case 0x00:
                msgType = "deviceReq";
                hasMore = binaryData[3];
                break;
            case 0x01:
                msgType = "deviceRsp";
                errCode = binaryData[4];
                break;
            default:
                break;
        }

        return msgType;
    }

    public void hook() {
        System.out.println("------hook()-------");
    }

    public abstract boolean check();
    public abstract ArrayNode report();
    public abstract ObjectNode ack();
}