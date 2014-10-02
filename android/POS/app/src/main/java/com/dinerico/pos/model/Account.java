package com.dinerico.pos.model;

import com.dinerico.pos.R;
import com.dinerico.pos.exception.ValidationError;
import com.dinerico.pos.util.Utils;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by josephleon on 9/30/14.
 */

public class Account implements Serializable{

  @DatabaseField
  private String id;
  @DatabaseField
  private String businessName;
  @DatabaseField
  private String email;
  @DatabaseField
  private String RUC;
  @DatabaseField
  private String address;
  @DatabaseField
  private String mobilePhone;
  @DatabaseField
  private String password;
  @DatabaseField
  private String specialContributor;
  @DatabaseField
  private boolean forcedAccounting;

  public static Account account;

  public Account() {
  }

  public static Account getInstance() {
    if (account == null)
      account = new Account();
    return account;
  }

  public void reset() {
    account = null;
  }

  public boolean validate() throws ValidationError {
    if( isValidEmail() && isValidRUC() && isValidPassword() &&
            isValidMobilePhone());
    return true;
  }

  public boolean isValidEmail() throws ValidationError{
    if (!Utils.isValidString(email) || !Utils.isValidEmail(email)){
      HashMap<String,Integer> errorData = new HashMap<String, Integer>();
      errorData.put("userMessage", R.string.no_valid_email);
      throw new ValidationError("Email null or blank", errorData);
    }
    return true;
  }

  public boolean isValidRUC() throws ValidationError{
    if (!Utils.isValidString(RUC)){
      HashMap<String,Integer> errorData = new HashMap<String, Integer>();
      errorData.put("userMessage", R.string.no_valid_ruc);
      throw new ValidationError("RUC null or blank", errorData);
    }
    return true;
  }

  public Boolean isValidPassword() throws ValidationError{
    if (!Utils.isValidString(password)){
      HashMap<String,Integer> errorData = new HashMap<String, Integer>();
      errorData.put("userMessage", R.string.no_valid_password);
      throw new ValidationError("Password null or blank", errorData);
    }
    return true;
  }

  public boolean isValidMobilePhone() throws ValidationError{
    if (!Utils.isValidString(mobilePhone) || mobilePhone.length() < 10){
      HashMap<String,Integer> errorData = new HashMap<String, Integer>();
      errorData.put("userMessage", R.string.no_valid_mobile_phone);
      throw new ValidationError("Mobile phone length lower 10, blank or null",
              errorData);
    }
    return true;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getBusinessName() {
    return businessName;
  }

  public void setBusinessName(String businessName) {
    this.businessName = businessName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getRUC() {
    return RUC;
  }

  public void setRUC(String RUC) {
    this.RUC = RUC;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public void setMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSpecialContributor() {
    return specialContributor;
  }

  public void setSpecialContributor(String specialContributor) {
    this.specialContributor = specialContributor;
  }

  public boolean isForcedAccounting() {
    return forcedAccounting;
  }

  public void setForcedAccounting(boolean forcedAccounting) {
    this.forcedAccounting = forcedAccounting;
  }


  @Override
  public String toString() {
    return "Account{ \n" +
            "id='" + id + "'\n" +
            "businessName='" + businessName + "'\n" +
            "email='" + email + "'\n" +
            "RUC='" + RUC + "'\n" +
            "address='" + address + "'\n" +
            "mobilePhone='" + mobilePhone + "'\n" +
            "password='" + password + "'\n" +
            "specialContributor='" + specialContributor + "'\n" +
            "forcedAccounting=" + forcedAccounting +
            '}';
  }
}
