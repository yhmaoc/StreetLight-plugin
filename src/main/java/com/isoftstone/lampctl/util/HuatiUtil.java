package com.isoftstone.lampctl.util;


import com.isoftstone.utility.Utilty;

public class HuatiUtil {
    /**
     * 计算包长
     */
    public static String packageLength(String dataStr) {
        Integer sum = Utilty.handleString(dataStr).trim().split(" ").length + 1;

        String sumHex = Integer.toHexString(sum);
        //位数不足则用0补齐高位
        while (sumHex.length() < 4) {
            sumHex = "0" + sumHex;
        }
        return sumHex;
    }

    /**
     * 计算校验位
     */
    public static String getCheckNum(String dataStr) {
        String[] data = Utilty.handleString(dataStr).trim().split(" ");

        //求和
        int sum = 0;
        for(int i = 0;i < data.length;i++){
            sum += Integer.parseInt(data[i],16);
        }
        String sumHex = Integer.toHexString(~sum);
        int sumHexLength = sumHex.length();
        if(sumHexLength > 2){
            sumHex = sumHex.substring(sumHexLength - 2,sumHexLength);
        }

        //位数不足则用0补齐高位
        while (sumHexLength < 2) {
            sumHex = "0" + sumHex;
        }
        return sumHex;
    }
}
