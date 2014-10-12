package com.dinerico.pos.viewmodel;

import com.dinerico.pos.db.SessionDB;
import com.dinerico.pos.exception.ValidationError;
import com.dinerico.pos.model.Login;
import com.dinerico.pos.model.Session;
import com.dinerico.pos.network.service.LoginService;

import java.util.Calendar;

/**
 * Created by josephleon on 10/2/14.
 */

public class LoginViewModel {
  private String email;
  private String password;

  private Login model;

  private SessionDB sessionDB;
  private LoginService service;

  public LoginViewModel(Login model,LoginService service, SessionDB sessionDB) {
    this.model = model;
    this.service = service;
    this.sessionDB = sessionDB;
  }

  public void login() throws ValidationError {
    model.validate();
    Calendar c = Calendar.getInstance();
    Session sessionFake = new Session();
    sessionFake.setCreated(c.toString());
    sessionDB.create(sessionFake);
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
    model.setEmail(email);
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    model.setPassword(password);
    this.password = password;
  }

  public Login getModel() {
    return model;
  }

  @Override
  public String toString() {
    return "LoginViewModel{ \n" +
            "customerId='" + email + "'\n" +
            "password='" + password + "'\n" +
            '}';
  }
}
