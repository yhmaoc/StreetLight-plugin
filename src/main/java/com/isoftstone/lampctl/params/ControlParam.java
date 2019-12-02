package com.isoftstone.lampctl.params;

/**
 * 控制参数（开、关、调光）
 *
 * @author yhmaoc
 * @since 0.1.0, 2019/11/27
 */
public class ControlParam {

  /**
   * 设备的mid编号
   */
  Integer mid;

  /**
   * 命令字符串（用于透传，16进制字符串）
   */
  String cmd ;

  /**
   * 调光值（开、调光）
   */
  Integer value;

  public Integer getMid() {
    return mid;
  }

  public void setMid(Integer mid) {
    this.mid = mid;
  }

  public String getCmd(){return cmd;}

  public void setCmd(String cmd) {
    this.cmd = cmd;
  }

  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }
}
