package com.isoftstone.lampctl.behavior.report;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
        ObjectNode serviceData = mapper.createObjectNode();
        serviceData.put("serviceId", "EnergyReport");
        ObjectNode energyData = mapper.createObjectNode();
        energyData.put("currentA", currentA);
        energyData.put("voltageA", voltageA);
        energyData.put("powerA", powerA);
        energyData.put("powerFactorA", powerFactorA);
        energyData.put("energy",energy);
        serviceData.set("serviceData",energyData);
        SimpleDateFormat simpleDateFormat  =new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        serviceData.put("eventTime", simpleDateFormat.format(new Date()));
        data.add(serviceData);

        return data;
    }
}
