package com.dinerico.pos.viewmodel;

import com.dinerico.pos.db.AccountDB;
import com.dinerico.pos.db.SessionDB;
import com.dinerico.pos.model.Account;
import com.dinerico.pos.model.Session;

import java.util.List;

/**
 * Created by josephleon on 10/2/14.
 */
public class HomeViewModel {

  private SessionDB sessionDB;
  private AccountDB accountDB;

  public HomeViewModel(SessionDB sessionDB, AccountDB accountDB) {
    this.sessionDB = sessionDB;
    this.accountDB = accountDB;
  }

  public Session getCurrentSession() {
    List<Session> list = sessionDB.getAll();
    accountDB.getAll();
    if (list.size() > 0) {
      Session session = list.get(0);
      Account.setInstance(session.getAccount());
      Account.getInstance().setSession(session);
      return session;
    } else
      return null;
  }
}

