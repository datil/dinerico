package com.dinerico.pos.db;

import android.app.Activity;
import android.util.Log;

import com.dinerico.pos.db.Config.DataBaseHelper;
import com.dinerico.pos.model.Session;

import java.util.Collections;
import java.util.List;


/**
 * Created by josephleon on 8/12/14.
 */

public class SessionDB {

  private DataBaseHelper dbHelperORMLite;
  private final String LOG_TAG = getClass().getSimpleName();

  public SessionDB(Activity activity) {
      dbHelperORMLite = new DataBaseHelper(activity);
  }

  public Session create(Session session) {
    try {
      dbHelperORMLite.getDaoSession().create(session);
      List<Session> list = dbHelperORMLite.getDaoSession().queryForAll();
      Session sessionSaved = list.get(list.size() - 1);
      Log.d(LOG_TAG, "Created session: " + sessionSaved);
      return sessionSaved;
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
      Log.e(LOG_TAG, "Error on create session");
      return null;
    }
  }

  public void delete(int id) {
    try {
      dbHelperORMLite.getDaoSession().deleteById(id);
      Log.d(LOG_TAG, "Deleted session id: " + id);
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
      Log.e(LOG_TAG, "Error on delete session");
    }
  }

  public List<Session> getAll() {
    try {
      List<Session> list = dbHelperORMLite.getDaoSession().queryForAll();
      Log.d(LOG_TAG, "Session list: \n" + list);
      return list;
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
      Log.e(LOG_TAG, "Error on get all session");
      return Collections.EMPTY_LIST;
    }
  }
}