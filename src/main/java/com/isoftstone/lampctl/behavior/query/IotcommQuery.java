package com.isoftstone.lampctl.behavior.query;

/**
 * ClassName: HuatiQuery
 * Package: com.isoftstone.lampctl.querybehavior
 * Description:
 *
 * @Date: 2019/11/26 12:49
 * @Author: softbaddog@sina.com
 */
public class IotcommQuery implements QueryBehavior {
    @Override
    public byte[] getStatus() {
        System.out.println("--IOTCOMM getStatus---");
        return null;
    }

    @Override
    public byte[] getEnergy() {
        System.out.println("--IOTCOMM getEnergy---");
        return null;
    }
}
