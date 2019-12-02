package com.isoftstone.lampctl.behavior.ack;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * ClassName: NormalAck
 * Package: com.isoftstone.lampctl.behavior.ack
 * Description:
 *
 * @Date: 2019/11/26 22:20
 * @Author: softbaddog@sina.com
 */
public class HuatiAck implements AckBehavior {
    @Override
    public ObjectNode cmdRsp(String[] dataArray) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode body = mapper.createObjectNode();
        if("01".equals(dataArray[6])){
            body.put("result", "success");
        }else{
            body.put("result", "fail");
        }
        return body;

    }
}
