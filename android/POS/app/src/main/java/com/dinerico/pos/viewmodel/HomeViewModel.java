package com.dinerico.pos.viewmodel;

import com.dinerico.pos.db.SessionDB;
import com.dinerico.pos.model.Session;

import java.util.List;

/**
 * Created by josephleon on 10/2/14.
 */
public class HomeViewModel {

  private SessionDB sessionDB;

  public HomeViewModel(SessionDB sessionDB) {
    this.sessionDB = sessionDB;
  }

  public Session getCreatedSession() {
    List<Session> list = sessionDB.getAll();
    if (list.size() > 0) {
      Session session = list.get(0);
      Session.setInstance(session);
      return session;
    } else
      return null;
  }

}

