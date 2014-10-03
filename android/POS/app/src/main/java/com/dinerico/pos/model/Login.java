package com.dinerico.pos.model;

import com.dinerico.pos.R;
import com.dinerico.pos.exception.ValidationError;
import com.dinerico.pos.util.Utils;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by josephleon on 10/2/14.
 */
public class Login implements Serializable{

  private String email;
  private String password;

  public Boolean isValidEmail() throws ValidationError {
    if (!Utils.isValidString(email) || !Utils.isValidEmail(email)){
      HashMap<String,Integer> errorData = new HashMap<String, Integer>();
      errorData.put("userMessage", R.string.noValidEmail);
      throw new ValidationError("Email null or blank", errorData);
    }
    return true;
  }

  public Boolean isValidPassword() throws ValidationError{
    if (!Utils.isValidString(password)){
      HashMap<String,Integer> errorData = new HashMap<String, Integer>();
      errorData.put("userMessage", R.string.noValidPassword);
      throw new ValidationError("Password null or blank", errorData);
    }
    return true;
  }

  public boolean validate() throws ValidationError {
    return isValidEmail() && isValidPassword();
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
