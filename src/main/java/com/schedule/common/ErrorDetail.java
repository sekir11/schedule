package com.schedule.common;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

import static java.util.Objects.nonNull;

/**
 * エラーの詳細を表現するクラス。
 */
@Value
@Builder
public class ErrorDetail implements Serializable {

  private ErrorCode errorCode;

  private String detailCode;

  private String argument;

  /**
   * このインスタンスのログ出力用のメッセージを解決します。
   *
   * @return このインスタンスのログ出力用のメッセージ
   */
  public String resolve() {
    String result = errorCode.resolve() + " [detailCode=" + detailCode + "]";

    if (nonNull(argument)) {
      result = String.format(result, argument);
    }
    return result;
  }

}
