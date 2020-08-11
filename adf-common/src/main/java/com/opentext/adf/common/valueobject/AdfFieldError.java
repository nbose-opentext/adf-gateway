package com.opentext.adf.common.valueobject;

/**
 * Represents an error passed in Validation of ADF Request.
 *
 * @author opentext
 */
public class AdfFieldError {
  private final String field;
  private final String message;

  public AdfFieldError(String field, String message) {
    this.field = field;
    this.message = message;
  }

  public String getField() {
    return field;
  }

  public String getMessage() {
    return message;
  }
}
