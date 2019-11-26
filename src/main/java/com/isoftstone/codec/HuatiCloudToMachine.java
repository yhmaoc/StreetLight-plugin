package com.isoftstone.codec;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

    protected String cmd = "SET_SWITCH_STATE";
    protected int hasMore = 0;
    protected int errcode = 0;
    protected short mid = 0;
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

        this.mid = (short)input.get("mid").asInt();
        this.cmd = input.get("cmd").asText();
        this.paras = input.get("paras");
        if (this.cmd.equals("SET_SWITCH_STATE")) {
            int status = paras.get("value").asInt();
            MessageBufferPacker packer = MessagePack.newDefaultBufferPacker();
            try {
                packer
                        .packByte((byte) status)
                        .packByte((byte) errcode)
                        .packShort(mid);
                packer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return packer.toByteArray();
        }
        return null;
    }

    @Override
    public byte[] ack() {
        this.errcode = input.get("errcode").asInt();
        byte[] ack = new byte[4];
        ack[0] = (byte) 0xAA;
        ack[1] = (byte) 0xAA;
        ack[2] = (byte) this.errcode;
        ack[3] = (byte) this.hasMore;
        return ack;
    }
}
