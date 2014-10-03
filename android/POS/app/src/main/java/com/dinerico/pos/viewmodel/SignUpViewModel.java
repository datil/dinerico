package com.dinerico.pos.viewmodel;

import com.dinerico.pos.db.AccountDB;
import com.dinerico.pos.db.SessionDB;
import com.dinerico.pos.model.Account;
import com.dinerico.pos.model.Session;

/**
 * Created by josephleon on 10/2/14.
 */
public class SignUpViewModel {

  private AccountDB accountDB;
  private SessionDB sessionDB;

  public static AccountViewModel viewModel;

  public SignUpViewModel(AccountDB accountDB,SessionDB sessionDB) {
    this.accountDB = accountDB;
    this.sessionDB = sessionDB;
  }

  public Account createAccount(Account account) {
    return accountDB.create(account);
  }

  public Session createSession(Session session) {
    return sessionDB.create(session);
  }

}


