package com.isoftstone.lampctl.constant;

/**
 * 华体NB设备常量类
 *
 * @author yhmaoc
 * @since 0.1.0, 2019/11/27
 */
public class HuatiConstant {

    /**
     * 设备控制帧头
     */
    public final static String SEND_FRAME_HEADER = "AA720200";

    /**
     * OC响应设备上报CMD
     */
    public final static String REPORT_RESP = "AAAA030000";

    /**
     *设备能耗数据上报
     */
    public final static String ENERGY_REPORT_CMD = "01";

    /**
     * 时钟校时命令
     */
    public final static String CHECK_TIME_CMD = "03";

    /**
     * 设置基本参数命令（开/调光、关）
     */
    public final static String CONTROL_CMD = "05";

    /**
     * A相寄存器地址（开/调光、关）
     */
    public final static String A_ADDRESS = "0259";

    /**
     * 发送的字的个数
     */
    public final static String SWITCH_WORD_NUM = "03";

    /**
     * 关灯指令
     */
    public final static String OPEN_CMD = "03";

    /**
     * 关灯指令
     */
    public final static String CLOSE_CMD = "020002000200";
}
