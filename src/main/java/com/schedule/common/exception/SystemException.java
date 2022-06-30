package com.schedule.common.exception;

import com.schedule.common.ErrorDetail;
import lombok.Builder;
import lombok.Value;

/**
 * システム例外を表現するクラスです。
 */
@Value
@Builder
public class SystemException extends RuntimeException {

  private final ErrorDetail errorDetail;

  /**
   * 指定されたエラー詳細を使用して {@link SystemException} を構築します。
   *
   * @param errorDetail エラー詳細
   */
  public SystemException(ErrorDetail errorDetail) {
    super();
    this.errorDetail = errorDetail;
  }

  /**
   * 指定された原因、およびエラー詳細を使用して {@link SystemException} を構築します。
   *
   * @param errorDetail エラー詳細
   * @param cause 原因
   */
  public SystemException(ErrorDetail errorDetail, Throwable cause) {
    super(cause);
    this.errorDetail = errorDetail;
  }

}
