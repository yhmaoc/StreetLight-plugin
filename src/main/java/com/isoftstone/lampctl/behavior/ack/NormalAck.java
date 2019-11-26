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
public class NormalAck implements AckBehavior {
    @Override
    public ObjectNode cmdRsp(String[] dataArray) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode body = mapper.createObjectNode();
        body.put("result", 0);
        return body;
    }
}
