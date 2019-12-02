package com.isoftstone.lampctl.behavior.report;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.isoftstone.utility.DateUtil;
import com.isoftstone.utility.Utilty;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ClassName: HuatiReport
 * Package: com.isoftstone.lampctl.behavior.report
 * Description:
 *
 * @Date: 2019/11/26 19:00
 * @Author: softbaddog@sina.com
 */
public class HuatiReport implements ReportBehavior {
    @Override
    public ArrayNode report(String[] dataArray) {
        //电流A  2
        double currentA =
                Long.valueOf(Utilty.getHexString(dataArray, 13, 1),16) / 100.0;
        //电压A  2
        double voltageA =
                Long.valueOf(Utilty.getHexString(dataArray, 11, 1),16) / 10.0;
        //功率A 2
        double powerA =
                Long.valueOf(Utilty.getHexString(dataArray, 9, 1),16) / 10.0;
        //功率因素A 2
        double powerFactorA =
                Long.valueOf(Utilty.getHexString(dataArray, 15, 1),16) / 100.0;
        //电能
        double energy = Long.valueOf(Utilty.getHexString(dataArray, 39, 3),16) / 10.0;
        //组装body体
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode data = mapper.createArrayNode();

        //解析能耗上报
        ObjectNode energyData = mapper.createObjectNode();
        energyData.put("currentA", currentA);
        energyData.put("voltageA", voltageA);
        energyData.put("powerA", powerA);
        energyData.put("powerFactorA", powerFactorA);
        energyData.put("energy",energy);
        ObjectNode energyReport = reportNode("EnergyReport",energyData);

        //解析信号强度
        Integer strength = Integer.valueOf(Utilty.getHexString(dataArray, 65, 1),16);
        ObjectNode signalData = mapper.createObjectNode();
        signalData.put("strength", strength);
        ObjectNode signalReport = reportNode("SignalQualityReport",signalData);

        //封装上报数据项（电能参数、信号强度）
        data.add(energyReport);
        data.add(signalReport);

        return data;
    }

    @Override
    public ArrayNode passthroughReport(String data) {
        //组装body体
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode node = mapper.createArrayNode();
        ObjectNode msg = mapper.createObjectNode();
        msg.put("msg", data);
        ObjectNode objectNode = reportNode("Passthrough",msg);
        //封装透传数据
        node.add(objectNode);
        return node;
    }

    private ObjectNode reportNode(String serviceId,ObjectNode serviceNode){
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode reportNode = mapper.createObjectNode();
        reportNode.put("serviceId", serviceId);
        reportNode.set("serviceData",serviceNode);
        reportNode.put("eventTime", DateUtil.formatDateAndTime(new Date()));
        return reportNode;
    }
}
