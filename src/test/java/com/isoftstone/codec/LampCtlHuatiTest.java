package com.isoftstone.codec;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.huawei.m2m.cig.tup.modules.protocol_adapter.IProtocolAdapter;
import com.isoftstone.lampctl.streetlight.ProtocolAdapterImpl;
import com.isoftstone.utility.Utilty;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class LampCtlHuatiTest {

    private IProtocolAdapter protocolAdapter;

    public static String parseByte2HexStr(byte[] buf) {
        if (null == buf) {
            return null;
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    @Before
    public void setProtocolAdapter() {
        System.out.println("Before");
        this.protocolAdapter = new ProtocolAdapterImpl();
    }

    @After
    public void finalTest() {
        System.out.println("After");
    }

    /**
     * 测试用例1：灯控向平台上报电量数据。
     *
     * <pre>
     * 设备上报数据:AA72000000420101F3000008D70000000000000000000000000000000000000000000000000000000
     * 11D54000000000000000000096E8100000000000000000000000F00000000B2
     * </pre>
     *
     * @throws Exception
     */
    @Test
    public void testLampCtlEnergyReport() throws Exception {
        String data = "AA72000000420101F3000008D70000000000000000000000000000000000000000000000000000000" +
                "11D54000000000000000000096E8100000000000000000000000F00000000B2";
        byte[] deviceReqByte = Utilty.getByteArray(data);
        System.out.println(parseByte2HexStr(deviceReqByte));
        ObjectNode objectNode = protocolAdapter.decode(deviceReqByte);
        String str = objectNode.toString();
        System.out.println(str);
    }

    /**
     * 测试用例2：设备对平台命令的应答消息
     *
     * <pre>
     * 设备应答消息:AA720100000201FE
     *
     * <pre>
     *
     * @throws Exception
     */
    @Test
    public void testLampCtlCmdResp() throws Exception {
        String data = "AA720100000201FE";
        byte[] deviceRspByte = Utilty.getByteArray(data);
        System.out.println(parseByte2HexStr(deviceRspByte));
        ObjectNode objectNode = protocolAdapter.decode(deviceRspByte);
        String str = objectNode.toString();
        System.out.println(str);
    }
}
