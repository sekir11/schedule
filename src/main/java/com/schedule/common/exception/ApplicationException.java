package com.schedule.common.exception;

import com.schedule.common.ErrorDetail;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.NonFinal;

/**
 * アプリケーション例外を表現するクラスです。
 */
@Value
@NonFinal
@Builder
public class ApplicationException extends RuntimeException {

  private final ErrorDetail errorDetail;

  private final String messageParameter;

  /**
   * 指定されたエラー詳細を使用して {@link ApplicationException} を構築します。
   *
   * @param errorDetail エラー詳細
   */
  public ApplicationException(ErrorDetail errorDetail) {
    super();
    this.errorDetail = errorDetail;
    this.messageParameter = "";
  }

  /**
   * 指定されたエラー詳細を使用して {@link ApplicationException} を構築します。
   *
   * @param errorDetail エラー詳細
   */
  public ApplicationException(ErrorDetail errorDetail, String messageParameter) {
    super();
    this.errorDetail = errorDetail;
    this.messageParameter = messageParameter;
  }

  /**
   * 指定された原因、およびエラー詳細を使用して {@link ApplicationException} を構築します。
   *
   * @param errorDetail エラー詳細
   * @param cause 原因
   */
  public ApplicationException(ErrorDetail errorDetail, Throwable cause) {
    super(cause);
    this.errorDetail = errorDetail;
    this.messageParameter = "";
  }

}
