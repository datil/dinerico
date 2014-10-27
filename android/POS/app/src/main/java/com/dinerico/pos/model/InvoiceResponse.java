package com.dinerico.pos.model;

/**
 * Created by josephleon on 10/25/14.
 */
public class InvoiceResponse {
  private String result;
  private String error;
  private String weburl;

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public String getWeburl() {
    return weburl;
  }

  public void setWeburl(String weburl) {
    this.weburl = weburl;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }
}
