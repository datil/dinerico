package com.dinerico.pos.model;

/**
 * Created by josephleon on 10/22/14.
 */
public class EMPayment {
  private double amount;
  private int brandId;
  private String document;
  private int currency;
  private String language;
  private String msisdnSrc;
  private String msisdnTar;
  private String pin;
  private String utfi;

  public static final String LANGUAGE="es";
  public static final String UTFI="";
  public static final int CURRENCY=1;
  public static final int BRAND_ID=1;

  public EMPayment(double amount, int brandId, String document, int currency,
                   String language, String msisdnSrc, String msisdnTar,
                   String pin, String utfi) {
    this.amount = amount;
    this.brandId = brandId;
    this.document = document;
    this.currency = currency;
    this.language = language;
    this.msisdnSrc = msisdnSrc;
    this.msisdnTar = msisdnTar;
    this.pin = pin;
    this.utfi = utfi;
  }

  public EMPayment(){

  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public int getBrandId() {
    return brandId;
  }

  public void setBrandId(int brandId) {
    this.brandId = brandId;
  }

  public String getDocument() {
    return document;
  }

  public void setDocument(String document) {
    this.document = document;
  }

  public int getCurrency() {
    return currency;
  }

  public void setCurrency(int currency) {
    this.currency = currency;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getMsisdnSrc() {
    return msisdnSrc;
  }

  public void setMsisdnSrc(String msisdnSrc) {
    this.msisdnSrc = msisdnSrc;
  }

  public String getMsisdnTar() {
    return msisdnTar;
  }

  public void setMsisdnTar(String msisdnTar) {
    this.msisdnTar = msisdnTar;
  }

  public String getPin() {
    return pin;
  }

  public void setPin(String pin) {
    this.pin = pin;
  }

  public String getUtfi() {
    return utfi;
  }

  public void setUtfi(String utfi) {
    this.utfi = utfi;
  }


  @Override
  public String toString() {
    return "DEPayment{ \n" +
            "amount=" + amount + "'\n" +
            "brandId=" + brandId + "'\n" +
            "document='" + document + "'\n" +
            "currency=" + currency + "'\n" +
            "language='" + language + "'\n" +
            "msisdnSrc='" + msisdnSrc + "'\n" +
            "msisdnTar='" + msisdnTar + "'\n" +
            "pin='" + pin + "'\n" +
            "utfi='" + utfi + "'\n" +
            '}';
  }
}
