package com.schedule.common;

import org.slf4j.Logger;

/**
 * ログに関するのユーティリティクラス。
 */
public final class LoggerUtils {

  private LoggerUtils() {
    // Do Nothing.
  }

  /**
   * 指定されたエラー詳細のメッセージを解決して、適切なレベルでログに出力します。
   *
   * @param logger ロガー
   * @param errorDetail エラー詳細
   */
  public static void log(Logger logger, ErrorDetail errorDetail) {
    String message = errorDetail.resolve();

    switch (errorDetail.getErrorCode().getLevel()) {
      case TRACE:
        logger.trace(message);
        break;

      case DEBUG:
        logger.debug(message);
        break;

      case INFO:
        logger.info(message);
        break;

      case WARN:
        logger.warn(message);
        break;

      case ERROR:
        logger.error(message);
        break;

      case FATAL:
        logger.error(message);
        break;

      default:
        logger.info(message);
    }

  }

  /**
   * 指定されたエラー詳細のメッセージを解決して、適切なレベルでログに出力します。
   *
   * @param logger ロガー
   * @param errorDetail エラー詳細
   * @param exception 例外
   */
  public static void log(Logger logger, ErrorDetail errorDetail, Throwable exception) {
    String message = errorDetail.resolve();

    switch (errorDetail.getErrorCode().getLevel()) {
      case TRACE:
        logger.trace(message, exception);
        break;

      case DEBUG:
        logger.debug(message, exception);
        break;

      case INFO:
        logger.info(message, exception);
        break;

      case WARN:
        logger.warn(message, exception);
        break;

      case ERROR:
        logger.error(message, exception);
        break;

      case FATAL:
        logger.error(message, exception);
        break;

      default:
        logger.info(message, exception);
    }
  }


  /**
   * 指定された送信電文の JSON メッセージをログにデバッグ出力します。
   *
   * @param logger ロガー
   * @param payload 送信電文の JSON メッセージ
   */
  public static void debugSendPayload(Logger logger, String payload) {
    debugPayload(logger, "SEND_PAYLOAD", payload);
  }

  /**
   * 指定された受信電文の JSON メッセージをログにデバッグ出力します。
   *
   * @param logger ロガー
   * @param payload 受信電文の JSON メッセージ
   */
  public static void debugReceivePayload(Logger logger, String payload) {
    debugPayload(logger, "RECEIVE_PAYLOAD", payload);
  }

  /**
   * 指定されたエラー電文のメッセージをログにデバッグ出力します。
   *
   * @param logger ロガー
   * @param payload エラー電文のメッセージ
   */
  public static void debugErrorResponse(Logger logger, String payload) {
    debugPayload(logger, "ERROR_PAYLOAD", payload);
  }


  private static void debugPayload(Logger logger, String type, String payload) {
    if (logger.isDebugEnabled()) {
      logger.debug("==================== {} START ====================", type);
      logger.debug(payload);
      logger.debug("==================== {} END ====================", type);
    }
  }

}
