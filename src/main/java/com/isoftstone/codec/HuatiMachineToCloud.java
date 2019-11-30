package com.isoftstone.codec;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.isoftstone.lampctl.constant.HuatiConstant;
import com.isoftstone.utility.Utilty;
import com.isoftstone.lampctl.HuatiLampCtl;
import com.isoftstone.lampctl.LampCtl;

/**
 * ClassName: HuatiMachineToCloud
 * Package: com.isoftstone.codec
 * Description:
 *
 * @Date: 2019/11/26 16:01
 * @Author: softbaddog@sina.com
 */
public class HuatiMachineToCloud extends AbsMachineToCloud {

    private static HuatiMachineToCloud instance = null;

    private HuatiMachineToCloud() {
        mapDeviceType.put(DeviceType.LAMPCTL, (byte)0xAA);
        mapManufacture.put(Manufacture.HUATI, (byte)0x72);
    }

    public static HuatiMachineToCloud getInstance(byte[] binaryData) {
        if (instance == null) {
            instance = new HuatiMachineToCloud();
        }
        instance.setBinaryData(binaryData);
        return instance;
    }

    @Override
    public boolean check() {
        if (binaryData[0] != mapDeviceType.get(DeviceType.LAMPCTL)
        || binaryData[1] != mapManufacture.get(Manufacture.HUATI)) {
            return false;
        }
        return true;
    }

    /**
     * {
     * "msgType":"deviceReq",
     * "hasMore":0,
     * "data":[{
     * "serviceId": "EnergyReport",
     * "serviceData": {
     * "currentA": 0.0,
     * "voltageA": 226.3,
     * "powerA": 0.0,
     * "powerFactorA": 0.0,
     * "energy": 7304.4
     * },
     * "eventTime": "2019-11-25T18:19:01Z"
     * }
     * ]
     * }
     *
     * @return
     */
    @Override
    public ArrayNode report() {
        LampCtl lampCtl = HuatiLampCtl.getInstance();
        ArrayNode data = mapper.createArrayNode();
        String dataStr = Utilty.parseByte2HexStr(binaryData);
        if (null == dataStr) {
            return null;
        }

        //数据为华体NB设备上报
        String[] dataArray = Utilty.handleString(dataStr).trim().split(" ");
        lampCtl.display();

        //华体上报报文解析
        switch (dataArray[6]){
            case HuatiConstant.ENERGY_REPORT_CMD:
                //电能参数、信号强度上报
                data = lampCtl.report(dataArray);
                break;
            case "FF":
                //透传上报
                data = lampCtl.passthroughReport(dataStr);
                break;
            default:
                data = null;
        }
        return data;
    }

    @Override
    public ObjectNode ack() {
        LampCtl lampCtl = HuatiLampCtl.getInstance();
        ObjectNode body = mapper.createObjectNode();
        String dataStr = Utilty.parseByte2HexStr(binaryData);
        if (null == dataStr) {
            return null;
        }

        //数据为华体NB设备上报
        String[] dataArray = Utilty.handleString(dataStr).trim().split(" ");
        lampCtl.display();
        body = lampCtl.ack(dataArray);

        return body;
    }
}
