package com.isoftstone.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 通用时间格式工具类
 *
 * @author yhmaoc
 * @since 0.1.0, 2019/11/28
 */
public class DateUtil {

  /**
   * 格式化时间
   */
  public static String format(Date date, String pattern) {
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    return sdf.format(date);
  }

  /**
   *Date转TZ格式
   * @param date
   * @return
   */
  public static String formatDateAndTime(Date date) {
    StringBuffer buffer = new StringBuffer();
    String dateStr = format(date, "yyyy-MM-dd HH:mm:ss");
    buffer.append(dateStr.substring(0, 10)).append("T");
    buffer.append(dateStr.substring(11)).append("Z");
    return buffer.toString();
  }

}
