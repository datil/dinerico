package com.dinerico.pos.db;

import android.app.Activity;
import android.util.Log;

import com.dinerico.pos.db.Config.DataBaseHelper;
import com.dinerico.pos.model.Tax;

import java.util.Collections;
import java.util.List;

/**
 * Created by josephleon on 10/21/14.
 */

public class TaxDB {

  private DataBaseHelper dbHelperORMLite;
  private final String LOG_TAG = getClass().getSimpleName();

  public TaxDB(Activity activity) {
    dbHelperORMLite = new DataBaseHelper(activity);
  }

  public Tax create(Tax tax) {
    try {
      dbHelperORMLite.getDaoTax().createOrUpdate(tax);
      List<Tax> list = dbHelperORMLite.getDaoTax().queryForAll();
      Tax taxSaved = list.get(list.size() - 1);
      Log.d(LOG_TAG, "Created tax: " + taxSaved);
      return taxSaved;
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
      Log.e(LOG_TAG, "Error on create tax");
      return null;
    }

  }

  public void delete(int id) {
    try {
      dbHelperORMLite.getDaoTax().deleteById(id);
      Log.d(LOG_TAG, "Deleted tax id: " + id);
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
      Log.e(LOG_TAG, "Error on delete tax");
    }
  }

  public List<Tax> getAll() {
    try {
      List<Tax> list = dbHelperORMLite.getDaoTax().queryForAll();
      Log.d(LOG_TAG, "Tax list: \n" + list);
      return list;
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
      Log.e(LOG_TAG, "Error on get all tax");
      return Collections.EMPTY_LIST;
    }
  }
}