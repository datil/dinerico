package com.dinerico.pos.model;

import com.dinerico.pos.R;
import com.dinerico.pos.exception.ValidationError;
import com.dinerico.pos.util.Utils;

import java.util.HashMap;

/**
 * Created by josephleon on 10/10/14.
 */
public class Invoice {

  private String email;
  private Customer customer;

  public boolean validate() throws ValidationError {
    return isValidEmail() && customer.validate();
  }

  public boolean isValidEmail() throws ValidationError {
    if (!Utils.isValidString(email) || !Utils.isValidEmail(email)){
      HashMap<String,Integer> errorData = new HashMap<String, Integer>();
      errorData.put("userMessage", R.string.noValidEmail);
      throw new ValidationError("Email null or blank", errorData);
    }
    return true;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }
}
