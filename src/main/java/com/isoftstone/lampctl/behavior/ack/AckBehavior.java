package com.isoftstone.lampctl.behavior.ack;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * ClassName: TaskBehavior
 * Package: com.isoftstone.lampctl.taskbehavior
 * Description:
 *
 * @Date: 2019/11/26 14:21
 * @Author: softbaddog@sina.com
 */
public interface AckBehavior {
    ObjectNode cmdRsp(String[] dataArray);
}
