package com.dinerico.pos.model;

/**
 * Created by josephleon on 10/25/14.
 */
public class SigningInvoice {
  private String apikey;
  private String weburl;
  private String password;
  private boolean procesocompleto;

  public static final boolean DO_COMPLETE_INVOICE_PROCESS = true;
  public static final String PASSWORD = "Edra102080";

  public SigningInvoice(String apikey, String weburl, String password,
                        boolean procesocompleto) {
    this.apikey = apikey;
    this.weburl = weburl;
    this.password = password;
    this.procesocompleto = procesocompleto;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isProcesocompleto() {
    return procesocompleto;
  }

  public void setProcesocompleto(boolean procesocompleto) {
    this.procesocompleto = procesocompleto;
  }


  @Override
  public String toString() {
    return "SigningInvoice{ \n" +
            "apikey='" + apikey + "'\n" +
            "weburl='" + weburl + "'\n" +
            "password='" + password + "'\n" +
            "procesocompleto=" + procesocompleto + "'\n" +
            '}';
  }
}
