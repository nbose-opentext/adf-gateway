package com.opentext.adf.gateway.config;

public enum GatewayResponseCode {
  SUCCESS("200", "response.success"),
  BAD_REQUEST("400", "request.bad"),
  VALIDATION_ERROR("400", "request.validation.error"),
  INTERNAL_SERVER_ERROR("500", "response.internal.server.error");

  /**
   * Status Code.
   */
  private final String statusCode;
  /**
   * Status Message code defined classpath:i18n/messages.
   */
  private final String statusMsgCode;

  GatewayResponseCode(String statusCode, String statusMsgCode) {
    this.statusCode = statusCode;
    this.statusMsgCode = statusMsgCode;
  }

  public String getStatusCode() {
    return statusCode;
  }

  public String getStatusMsgCode() {
    return statusMsgCode;
  }
}
