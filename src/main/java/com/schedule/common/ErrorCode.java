package com.schedule.common;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * ログ・メッセージを表現するクラス。
 */
@Value
@Builder
public class ErrorCode implements Serializable {

  private Level level;

  private String code;

  private String text;

  private String messageParameter;

  /**
   * このインスタンスのエラーコードを解決します。
   *
   * @return このインスタンスのエラーコード
   */
  public String resolveErrorCode() {
    return "schedule" + "-"  + code;
  }

  /**
   * このインスタンスのログ・メッセージを解決します。
   *
   * @return このインスタンスのログ・メッセージ
   */
  public String resolve() {
    return "schedule" + "-" + level.getValue() + "-" + code + " : " + text;
  }

}
