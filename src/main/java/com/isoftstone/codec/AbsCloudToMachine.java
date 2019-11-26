package com.isoftstone.codec;

import com.fasterxml.jackson.databind.node.ObjectNode;

public abstract class AbsCloudToMachine {
    protected ObjectNode input;
    protected byte[] bytes;

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
}
