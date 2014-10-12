package com.dinerico.pos.db;

import android.app.Activity;
import android.util.Log;

import com.dinerico.pos.db.Config.DataBaseHelper;
import com.dinerico.pos.model.Store;

import java.util.Collections;
import java.util.List;

/**
 * Created by josephleon on 10/11/14.
 */

public class StoreDB {

  private DataBaseHelper dbHelperORMLite;
  private final String LOG_TAG = getClass().getSimpleName();

  public StoreDB(Activity activity) {
    dbHelperORMLite = new DataBaseHelper(activity);
  }

  public Store create(Store store) {
    try {
      dbHelperORMLite.getDaoStore().createOrUpdate(store);
      List<Store> list = dbHelperORMLite.getDaoStore().queryForAll();
      Store storeSaved = list.get(list.size() - 1);
      Log.d(LOG_TAG, "Created store: " + storeSaved);
      return storeSaved;
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
      Log.e(LOG_TAG, "Error on create store");
      return null;
    }

  }

  public void delete(int id) {
    try {
      dbHelperORMLite.getDaoStore().deleteById(id);
      Log.d(LOG_TAG, "Deleted store id: " + id);
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
      Log.e(LOG_TAG, "Error on delete store");
    }
  }

  public List<Store> getAll() {
    try {
      List<Store> list = dbHelperORMLite.getDaoStore().queryForAll();
      Log.d(LOG_TAG, "Store list: \n" + list);
      return list;
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
      Log.e(LOG_TAG, "Error on get all store");
      return Collections.EMPTY_LIST;
    }
  }
}