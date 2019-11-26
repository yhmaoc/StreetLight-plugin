package com.isoftstone.lampctl.streetlight;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.huawei.m2m.cig.tup.modules.protocol_adapter.IProtocolAdapter;
import com.isoftstone.codec.AbsCloudToMachine;
import com.isoftstone.codec.HuatiCloudToMachine;
import com.isoftstone.codec.HuatiMachineToCloud;
import com.isoftstone.codec.AbsMachineToCloud;

/**
 * @author FZM
 */
public class ProtocolAdapterImpl implements IProtocolAdapter {

    // 厂商名称
    private static final String MANU_FACTURERID = "e4028b332c164b00a51eec6ac3f78bf3";
    // 设备型号
    private static final String MODEL = "lampctl";

    @Override
    public String getManufacturerId() {
        return MANU_FACTURERID;
    }

    @Override
    public String getModel() {
        return MODEL;
    }

    @Override
    public byte[] encode(ObjectNode input) throws Exception {
        try {
            AbsCloudToMachine msg = HuatiCloudToMachine.getInstance(input);
            return msg.handle();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ObjectNode decode(byte[] binaryData) throws Exception {
        try {
            AbsMachineToCloud data = HuatiMachineToCloud.getInstance(binaryData);
            return data.handle();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
