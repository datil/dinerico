package com.dinerico.pos.viewmodel;

import com.dinerico.pos.db.AccountDB;
import com.dinerico.pos.db.SessionDB;
import com.dinerico.pos.exception.ValidationError;
import com.dinerico.pos.model.Account;
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

  private Account account;

  private AccountDB accountDB;
  private SessionDB sessionDB;
  private LoginService service;

  public LoginViewModel(Account account, LoginService service,
                        SessionDB sessionDB, AccountDB accountDB) {
    this.account = account;
    this.service = service;
    this.sessionDB = sessionDB;
    this.accountDB = accountDB;
  }

  public boolean login() throws ValidationError {
    account.validate();
    List<Account> list = accountDB.queryByEmail(email);
    int size = list.size();
    if (size == 0)
      return false;
    Account account = list.get(0);
    if (account.getPassword().equals(password)) {
      Account.setInstance(account);
      Calendar c = Calendar.getInstance();
      Session sessionFake = new Session();
      sessionFake.setCreated(c.toString());
      sessionFake.setAccount(account);
      Account.getInstance().setSession(sessionFake);
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
    account.setEmail(email);
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    account.setPassword(password);
    this.password = password;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  @Override
  public String toString() {
    return "LoginViewModel{ \n" +
            "customerId='" + email + "'\n" +
            "password='" + password + "'\n" +
            '}';
  }
}
