package com.isoftstone.lampctl.behavior.query;

/**
 * ClassName: QueryBehavior
 * Package: com.isoftstone.lampctl.querybehavior
 * Description:
 *
 * @Date: 2019/11/26 12:47
 * @Author: softbaddog@sina.com
 */
public interface QueryBehavior {
    /**
     * 获取NB设备状态（开关状态）
     */
    byte[] getStatus();

    /**
     * 获取电能参数
     */
    byte[] getEnergy();
}
