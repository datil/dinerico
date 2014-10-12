package com.dinerico.pos.db;

import android.app.Activity;
import android.util.Log;

import com.dinerico.pos.db.Config.DataBaseHelper;
import com.dinerico.pos.model.Charge;

import java.util.Collections;
import java.util.List;

/**
 * Created by josephleon on 10/11/14.
 */
public class ChargeDB {

  private DataBaseHelper dbHelperORMLite;
  private final String LOG_TAG = getClass().getSimpleName();

  public ChargeDB(Activity activity) {
    dbHelperORMLite = new DataBaseHelper(activity);
  }

  public Charge create(Charge charge) {
    try {
      dbHelperORMLite.getDaoCharge().createOrUpdate(charge);
      List<Charge> list = dbHelperORMLite.getDaoCharge().queryForAll();
      Charge chargeSaved = list.get(list.size() - 1);
      Log.d(LOG_TAG, "Created charge: " + chargeSaved);
      return chargeSaved;
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
      Log.e(LOG_TAG, "Error on create charge");
      return null;
    }

  }

  public void delete(int id) {
    try {
      dbHelperORMLite.getDaoCharge().deleteById(id);
      Log.d(LOG_TAG, "Deleted charge id: " + id);
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
      Log.e(LOG_TAG, "Error on delete charge");
    }
  }

  public List<Charge> getAll() {
    try {
      List<Charge> list = dbHelperORMLite.getDaoCharge().queryForAll();
      Log.d(LOG_TAG, "Charge list: \n" + list);
      return list;
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
      Log.e(LOG_TAG, "Error on get all charge");
      return Collections.EMPTY_LIST;
    }
  }
}