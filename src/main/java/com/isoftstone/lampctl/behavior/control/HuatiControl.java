package com.isoftstone.lampctl.behavior.control;

import com.isoftstone.lampctl.constant.HuatiConstant;
import com.isoftstone.lampctl.params.ControlParam;
import com.isoftstone.lampctl.util.HuatiUtil;
import com.isoftstone.utility.Utilty;

/**
 * 华体NB灯控开、关、调光控制
 *
 * @author yhmaoc
 * @since 0.1.0, 2019/11/27
 */
public class HuatiControl implements ControlBehavior {

    /**
     * 华体灯控开灯操作
     * @param param
     * @return
     */
    @Override
    public byte[] on(ControlParam param) {
        System.out.println("---Huati NB-lamp on---");
        if(null == param.getMid()){
            return null;
        }

        int value = 100;
        String mid = Utilty.intToHex2(param.getMid());
        if(null != param.getValue()){
            value = param.getValue();
        }

        StringBuilder dataStr = new StringBuilder();
        dataStr.append(HuatiConstant.CONTROL_CMD);
        dataStr.append(HuatiConstant.A_ADDRESS);
        dataStr.append(HuatiConstant.SWITCH_WORD_NUM);

        StringBuilder openCmd = new StringBuilder();
        for(int i = 0;i < 3;i++){
            openCmd.append(HuatiConstant.OPEN_CMD);
            openCmd.append(Utilty.intToHex(value));
        }
        dataStr.append(openCmd.toString());

        String length = HuatiUtil.packageLength(dataStr.toString());
        String checkNum = HuatiUtil.getCheckNum(dataStr.toString());

        StringBuilder cmdBuf = new StringBuilder(HuatiConstant.SEND_FRAME_HEADER);
        cmdBuf.append(mid);
        cmdBuf.append(length);
        cmdBuf.append(dataStr.toString());
        cmdBuf.append(checkNum);
        return Utilty.getByteArray(cmdBuf.toString());
    }

    @Override
    public byte[] off(ControlParam param) {
        System.out.println("---Huati NB-lamp off---");
        if(null == param.getMid()){
            return null;
        }

        String mid = Utilty.intToHex2(param.getMid());

        StringBuilder dataStr = new StringBuilder();
        dataStr.append(HuatiConstant.CONTROL_CMD);
        dataStr.append(HuatiConstant.A_ADDRESS);
        dataStr.append(HuatiConstant.SWITCH_WORD_NUM);
        dataStr.append(HuatiConstant.CLOSE_CMD);

        String length = HuatiUtil.packageLength(dataStr.toString());
        String checkNum = HuatiUtil.getCheckNum(dataStr.toString());

        StringBuilder cmdBuf = new StringBuilder(HuatiConstant.SEND_FRAME_HEADER);
        cmdBuf.append(mid);
        cmdBuf.append(length);
        cmdBuf.append(dataStr.toString());
        cmdBuf.append(checkNum);
        return Utilty.getByteArray(cmdBuf.toString());
    }

    @Override
    public byte[] dim(ControlParam param) {
        System.out.println("---Huati NB-lamp dim---");
        return this.on(param);
    }
}
