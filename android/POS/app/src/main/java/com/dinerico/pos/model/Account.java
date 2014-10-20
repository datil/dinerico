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

public class Account implements Serializable {

  @DatabaseField(generatedId = true)
  private int id;
  @DatabaseField
  private String email;
  @DatabaseField
  private String password;
  @DatabaseField(foreign = true, foreignAutoRefresh = true)
  private Session session;
  @DatabaseField(foreign = true, foreignAutoRefresh = true)
  private Store store;

  public static Account account;

  public Account() {
    store = new Store();
  }

  public static Account getInstance() {
    if (account == null)
      account = new Account();
    return account;
  }

  public static void setInstance(Account account1) {
    if (account1 != null)
      account = account1;
  }

  public static void reset() {
    account = null;
  }

  public boolean validate() throws ValidationError {
    if (isValidEmail() && isValidPassword()) ;
    return true;
  }

  public Boolean isValidPassword() throws ValidationError {
    if (!Utils.isValidString(password)) {
      HashMap<String, Integer> errorData = new HashMap<String, Integer>();
      errorData.put("userMessage", R.string.noValidPassword);
      throw new ValidationError("Password null or blank", errorData);
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

  public Store getStore() {
    return store;
  }

  public void setStore(Store store) {
    this.store = store;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public Session getSession() {
    return session;
  }

  public void setSession(Session session) {
    this.session = session;
  }

  @Override
  public String toString() {
    return "Account{ \n" +
            "id=" + id + "'\n" +
            "email='" + email + "'\n" +
            "password='" + password + "'\n" +
            "session=" + session + "'\n" +
            "store=" + store + "'\n" +
            '}';
  }
}
