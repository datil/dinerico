package com.dinerico.pos.viewmodel;

import com.dinerico.pos.db.AccountDB;
import com.dinerico.pos.db.SessionDB;
import com.dinerico.pos.exception.ValidationError;
import com.dinerico.pos.model.Account;
import com.dinerico.pos.model.Login;
import com.dinerico.pos.model.Session;
import com.dinerico.pos.network.service.LoginService;

import java.util.Calendar;
import java.util.List;

/**
 * Created by josephleon on 10/2/14.
 */

public class LoginViewModel {
  private String email;
  private String password;

  private Login model;

  private SessionDB sessionDB;
  private AccountDB accountDB;
  private LoginService service;

  public LoginViewModel(Login model, LoginService service,
                        SessionDB sessionDB, AccountDB accountDB) {
    this.model = model;
    this.service = service;
    this.sessionDB = sessionDB;
    this.accountDB = accountDB;
  }

  public Account getAccount() {
    List<Account> list = accountDB.getAll();
    int size = list.size();
    if (size > 0)
      return list.get(size);
    else return null;
  }

  public boolean login() throws ValidationError {
    model.validate();
    List<Account> list = accountDB.queryByEmail(email);
    int size = list.size();
    if (size == 0)
      return false;
    Account account = list.get(size - 1);
    if (account.getPassword().equals(password)) {
      Account.setInstance(account);
      Calendar c = Calendar.getInstance();
      Session sessionFake = new Session();
      sessionFake.setCreated(c.toString());
      sessionDB.create(sessionFake);
      return true;
    } else
      return false;
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
