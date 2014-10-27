package com.dinerico.pos.model;

/**
 * Created by josephleon on 10/25/14.
 */
public class MailingInvoice {
  private String apikey;
  private String weburl;

  public MailingInvoice(String apikey, String weburl) {
    this.apikey = apikey;
    this.weburl = weburl;
  }

  public String getApikey() {
    return apikey;
  }

  public void setApikey(String apikey) {
    this.apikey = apikey;
  }

  public String getWeburl() {
    return weburl;
  }

  public void setWeburl(String weburl) {
    this.weburl = weburl;
  }

  @Override
  public String toString() {
    return "InvoiceMail{ \n" +
            "apikey='" + apikey + "'\n" +
            "weburl='" + weburl + "'\n" +
            '}';
  }
}
