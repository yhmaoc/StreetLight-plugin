package com.isoftstone.utility;

public class Utilty {

    private static Utilty instance = new Utilty();

    public static Utilty getInstance() {
        return instance;
    }

    public static final int MIN_MID_VALUE = 1;

    public static final int MAX_MID_VALUE = 65535;

    public int bytes2Int(byte[] b, int start, int length) {
        int sum = 0;
        int end = start + length;
        for (int k = start; k < end; k++) {
            int n = ((int) b[k]) & 0xff;
            n <<= (--length) * 8;
            sum += n;
        }
        return sum;
    }

    public byte[] int2Bytes(int value, int length) {
        byte[] b = new byte[length];
        for (int k = 0; k < length; k++) {
            b[length - k - 1] = (byte) ((value >> 8 * k) & 0xff);
        }
        return b;
    }

    public boolean isValidofMid(int mId) {
        if (mId < MIN_MID_VALUE || mId > MAX_MID_VALUE) {
            return false;
        }
        return true;
    }

    /***
     * byte数组转16进制字符串
     */
    public static String parseByte2HexStr(byte[] buf) {
        if (null == buf) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 每两个字符直接加一个空格
     */
    public static String handleString(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            result += str.charAt(i);
            if (i % 2 == 1) {
                result += " ";
            }
        }
        return result;
    }

    /**
     * 从给定的字符串数组中获取指定位置的字符组成字符串
     */
    public static String getHexString(String[] b, int index, int end) {
        StringBuilder sb = new StringBuilder();
        String str = "";
        for (int i = index; i <= index + end; i++) {
            if (i >= b.length) {
                break;
            }
            str = b[i];
            sb.append(str);
        }
        return sb.toString();
    }

    /**
     * 字符串转byte数组
     * <br>
     */
    public static byte[] getByteArray(String str) {
        String[] strArray = handleString(str).trim().split(" ");
        byte[] cmdMsg = new byte[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            int v = Integer.valueOf(strArray[i], 16).byteValue();
            cmdMsg[i] = (byte) v;
        }
        return cmdMsg;
    }

    /**
     * 十进制转16进制
     * @param param 整形
     * @return 两位16进制字符串【不足高位补0】
     */
    public static String intToHex2(int param) {
        String hexStr = Integer.toHexString(param);
        while(hexStr.length() < 4){
            hexStr = "0" + hexStr;
        }
        return hexStr;
    }

    /**
     * 十进制转16进制
     *
     * @param param 整形
     * @return 两位16进制字符串【不足高位补0】
     */
    public static String intToHex(int param) {
        if (String.valueOf(param).isEmpty()) {
            return "00";
        }
        String hexStr = Integer.toHexString(param);
        while (hexStr.length() < 2) {
            hexStr = "0" + hexStr;
        }
        return hexStr;
    }
}
