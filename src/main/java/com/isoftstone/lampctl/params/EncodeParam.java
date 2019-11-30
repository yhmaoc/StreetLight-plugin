package com.isoftstone.lampctl.params;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * OC下发给设备的参数
 *
 * @author yhmaoc
 * @since 0.1.0, 2019/11/28
 */
public class EncodeParam {

  /**
   * 设备在应用协议里的标识
   */
  private String identifier;

  /**
   * 消息类型（cloudRsp:平台响应设备，cloudReq:平台下发请求）
   */
  private String msgType;

  /**
   * 设备id
   */
  private Integer mid;

  /**
   * 服务ID（profile服务）
   */
  private String serviceId;

  /**
   * 命令ID（profile服务下的命令）
   */
  private String cmd;

  /**
   * 命令参数（profile命令参数）
   */
  private JsonNode paras;

  /**
   * 平台是否有后续命令下发（0：没有，1：有）
   */
  private Integer hasMore;

  /**
   * 设备上报的数据
   */
  private byte[] request;

  /**
   * 平台处理结果（0：成功，1：失败）
   */
  private Integer errcode;

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public String getMsgType() {
    return msgType;
  }

  public void setMsgType(String msgType) {
    this.msgType = msgType;
  }

  public Integer getMid() {
    return mid;
  }

  public void setMid(Integer mid) {
    this.mid = mid;
  }

  public String getServiceId() {
    return serviceId;
  }

  public void setServiceId(String serviceId) {
    this.serviceId = serviceId;
  }

  public String getCmd() {
    return cmd;
  }

  public void setCmd(String cmd) {
    this.cmd = cmd;
  }

  public JsonNode getParas() {
    return paras;
  }

  public void setParas(JsonNode paras) {
    this.paras = paras;
  }

  public Integer getHasMore() {
    return hasMore;
  }

  public void setHasMore(Integer hasMore) {
    this.hasMore = hasMore;
  }

  public byte[] getRequest() {
    return request;
  }

  public void setRequest(byte[] request) {
    this.request = request;
  }

  public Integer getErrcode() {
    return errcode;
  }

  public void setErrcode(Integer errcode) {
    this.errcode = errcode;
  }
}
