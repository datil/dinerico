package com.dinerico.pos.model;

import java.io.Serializable;

/**
 * Created by josephleon on 10/22/14.
 */
public class EMResponse implements Serializable{

  private int result;
  private String text;
  private String utfi;

  public int getResult() {
    return result;
  }

  public void setResult(int result) {
    this.result = result;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getUtfi() {
    return utfi;
  }

  public void setUtfi(String utfi) {
    this.utfi = utfi;
  }

  @Override
  public String toString() {
    return "EMMessage{ \n" +
            "result=" + result +  "'\n" +
            "text='" + text + "'\n" +
            "utfi='" + utfi + "'\n" +
            '}';
  }
}
