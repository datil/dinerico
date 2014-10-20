package com.dinerico.pos.db;

import android.app.Activity;
import android.util.Log;

import com.dinerico.pos.db.Config.DataBaseHelper;
import com.dinerico.pos.model.Address;

import java.util.Collections;
import java.util.List;

/**
 * Created by josephleon on 10/19/14.
 */

public class AddressDB {

  private DataBaseHelper dbHelperORMLite;
  private final String LOG_TAG = getClass().getSimpleName();

  public AddressDB(Activity activity) {
    dbHelperORMLite = new DataBaseHelper(activity);
  }

  public Address create(Address address) {
    try {
      dbHelperORMLite.getDaoAddress().createOrUpdate(address);
      List<Address> list = dbHelperORMLite.getDaoAddress().queryForAll();
      Address addressSaved = list.get(list.size() - 1);
      Log.d(LOG_TAG, "Created address: " + addressSaved);
      return addressSaved;
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
      Log.e(LOG_TAG, "Error on create address");
      return null;
    }

  }

  public void delete(int id) {
    try {
      dbHelperORMLite.getDaoAddress().deleteById(id);
      Log.d(LOG_TAG, "Deleted address id: " + id);
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
      Log.e(LOG_TAG, "Error on delete address");
    }
  }

  public List<Address> getAll() {
    try {
      List<Address> list = dbHelperORMLite.getDaoAddress().queryForAll();
      Log.d(LOG_TAG, "Address list: \n" + list);
      return list;
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
      Log.e(LOG_TAG, "Error on get all address");
      return Collections.EMPTY_LIST;
    }
  }
}