package com.isoftstone.lampctl.behavior.passthrough;

import com.isoftstone.lampctl.params.ControlParam;

/**
 * TODO
 *
 * @author yhmaoc
 * @since 0.1.0, 2019/11/29
 */
public class IotcommPassthrough implements PassthroughBehavior {

  @Override
  public byte[] send(ControlParam param) {
    return new byte[0];
  }
}
