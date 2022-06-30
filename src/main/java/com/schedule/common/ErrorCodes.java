package com.schedule.common;

/**
 * Smart Phone Adviser における共通のエラーコード。
 */
public final class ErrorCodes {

  private ErrorCodes() {
    // Do Nothing.
  }

  /** REST 通信で500エラーが発生した場合のエラーコード。 */
  public static final ErrorCode REST_CLIENT_SYSTEM_ERROR
          = ErrorCode.builder()
                     .level(Level.ERROR)
                     .code("0000")
                     .text("Server side error is occurred from backend.")
                     .build();

  /** REST 通信で400エラーが発生した場合のエラーコード。 */
  public static final ErrorCode REST_CLIENT_BUSINESS_ERROR
          = ErrorCode.builder()
                     .level(Level.WARN)
                     .code("0001")
                     .text("Client side error is occurred from backend.")
                     .build();

  /** ログイン時にパスワードが異なった場合のエラーコード。 */
  public static final ErrorCode PASSWORD_ERROR
          = ErrorCode.builder()
                     .level(Level.WARN)
                     .code("0002")
                     .text("password is different")
                     .build();

  /** 指定した名前のユーザーがいない場合のエラーコード。 */
  public static final ErrorCode USER_NOT_FOUND
          = ErrorCode.builder()
                     .level(Level.WARN)
                     .code("0003")
                     .text("user was not found")
                     .build();

  /** 指定した id のレコードが存在しない場合のエラーコード。 */
  public static final ErrorCode RECORD_NOT_FOUND
          = ErrorCode.builder()
                     .level(Level.WARN)
                     .code("0004")
                     .text("record was not found (id = %s)")
                     .build();

  /** 登録しようとしたユーザーの名前がすでに使われている場合のエラーコード。 */
  public static final ErrorCode NAME_IS_ALREADY_IN_USE
          = ErrorCode.builder()
                     .level(Level.WARN)
                     .code("0005")
                     .text("the name is already in use. (id = %s)")
                     .build();

  /** 二つのパスワードが一致しない場合のエラーコード。 */
  public static final ErrorCode PASSWORD_NOT_EQUAL
          = ErrorCode.builder()
                     .level(Level.WARN)
                     .code("0006")
                     .text("the two passwords are different.")
                     .build();

  /** 想定していないクライアントアボードが変出された場合。 */
  public static final ErrorCode UNEXPECTED_CLIENT_ABORT
          = ErrorCode.builder()
                     .level(Level.WARN)
                     .code("9997")
                     .text("Unexpected client abort is detected (%s).")
                     .build();

  /** 想定していない HTTP メソッドのアクセスが変出された場合。 */
  public static final ErrorCode UNEXPECTED_HTTP_METHOD
          = ErrorCode.builder()
                     .level(Level.WARN)
                     .code("9998")
                     .text("Unexpected HTTP method is detected (%s).")
                     .build();

  /** 予期せぬ例外が発生した場合。 */
  public static final ErrorCode UNEXPECTED_EXCEPTION_OCCURRED
          = ErrorCode.builder()
                     .level(Level.ERROR)
                     .code("9999")
                     .text("Unexpected error is occurred.")
                     .build();

}
