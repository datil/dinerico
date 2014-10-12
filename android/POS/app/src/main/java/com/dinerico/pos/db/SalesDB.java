package com.dinerico.pos.db;

import android.app.Activity;
import android.util.Log;

import com.dinerico.pos.db.Config.DataBaseHelper;
import com.dinerico.pos.model.Sales;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by josephleon on 10/11/14.
 */

public class SalesDB {

  private DataBaseHelper dbHelperORMLite;
  private final String LOG_TAG = getClass().getSimpleName();

  public SalesDB(Activity activity) {
    dbHelperORMLite = new DataBaseHelper(activity);
  }

  public Sales create(Sales sales) {
    try {
      dbHelperORMLite.getDaoSales().createOrUpdate(sales);
      List<Sales> list = dbHelperORMLite.getDaoSales().queryForAll();
      Sales salesSaved = list.get(list.size() - 1);
      Log.d(LOG_TAG, "Created sales: " + salesSaved);
      return salesSaved;
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
      Log.e(LOG_TAG, "Error on create sales");
      return null;
    }
  }

  public void delete(int id) {
    try {
      dbHelperORMLite.getDaoSales().deleteById(id);
      Log.d(LOG_TAG, "Deleted sales id: " + id);
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
      Log.e(LOG_TAG, "Error on delete sales");
    }
  }

  public List<Sales> getAll() {
    List<Sales> list = new ArrayList<Sales>();
    try {
      list = dbHelperORMLite.getDaoSales().queryForAll();
      Log.d(LOG_TAG, "Sales list: \n" + list);
      return list;
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
      Log.e(LOG_TAG, "Error on get all sales");
      return list;
    }
  }
}