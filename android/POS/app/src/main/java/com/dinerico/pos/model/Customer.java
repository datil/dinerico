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
  @DatabaseField
  private String email;
  @DatabaseField
  private String name;
  @DatabaseField(foreign = true, foreignAutoRefresh = true)
  private Address address;
  @DatabaseField
  private String telephone;

  public Customer() {
  }

  public boolean isValidCustomerId() throws ValidationError {
    if (!Utils.isValidString(customerId) || customerId.length() < 10) {
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

  public boolean isValidName() throws ValidationError {
    if (!Utils.isValidString(name)) {
      HashMap<String, Integer> errorData = new HashMap<String, Integer>();
      errorData.put("userMessage", R.string.noValidCustomerName);
      throw new ValidationError("Customer name null or blank", errorData);
    }
    return true;
  }

  public boolean isValidEmail() throws ValidationError {
    if (!Utils.isValidString(email) || !Utils.isValidEmail(email)) {
      HashMap<String, Integer> errorData = new HashMap<String, Integer>();
      errorData.put("userMessage", R.string.noValidEmail);
      throw new ValidationError("Email null or blank", errorData);
    }
    return true;
  }

  public boolean isValidTelephone() throws ValidationError {
    if (!Utils.isValidString(telephone)) {
      HashMap<String, Integer> errorData = new HashMap<String, Integer>();
      errorData.put("userMessage", R.string.noValidTelephone);
      throw new ValidationError("Telephone null or blank", errorData);
    }
    return true;
  }

  public boolean isValidAddress() throws ValidationError {
    return address.isValidCalle();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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
