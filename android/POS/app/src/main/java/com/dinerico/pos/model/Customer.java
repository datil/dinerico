package com.dinerico.pos.model;

import com.dinerico.pos.R;
import com.dinerico.pos.exception.ValidationError;
import com.dinerico.pos.util.Utils;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by josephleon on 10/10/14.
 */
public class Customer implements Serializable {

  @DatabaseField(generatedId = true)
  private int id;
  @DatabaseField
  private String customerId;
  @DatabaseField
  private String mobilePhone;

  public Customer() {

  }

  public boolean validate() throws ValidationError {
    return isValidMobilePhone() && isValidCustomerId();
  }

  public boolean isValidCustomerId() throws ValidationError {
    if (!Utils.isValidString(customerId) && customerId.length() < 10) {
      HashMap<String, Integer> errorData = new HashMap<String, Integer>();
      errorData.put("userMessage", R.string.noValidCustomerId);
      throw new ValidationError("CustomerId null, blank or incorrect size",
              errorData);
    }
    return true;
  }

  public boolean isValidMobilePhone() throws ValidationError {
    if (!Utils.isValidString(mobilePhone) || mobilePhone.length() < 10) {
      HashMap<String, Integer> errorData = new HashMap<String, Integer>();
      errorData.put("userMessage", R.string.noValidMobilePhone);
      throw new ValidationError("Mobile phone length lower 10, blank or null",
              errorData);
    }
    return true;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public void setMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
  }


  @Override
  public String toString() {
    return "Customer{ \n" +
            "id=" + id + "'\n" +
            "customerId='" + customerId + "'\n" +
            "sendReceipt='" + mobilePhone + "'\n" +
            '}';
  }
}
