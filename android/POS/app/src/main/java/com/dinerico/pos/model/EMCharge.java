package com.dinerico.pos.model;

import java.io.Serializable;

/**
 * Created by josephleon on 10/11/14.
 */
public class EMCharge implements Serializable{

  public EMCharge(){

  }

  private String mobilePhone;
  private float amount;

  public String getMobilePhone() {
    return mobilePhone;
  }

  public void setMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
  }

  public float getAmount() {
    return amount;
  }

  public void setAmount(float amount) {
    this.amount = amount;
  }
}
