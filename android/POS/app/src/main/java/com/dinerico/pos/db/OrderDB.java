package com.dinerico.pos.db;

import android.app.Activity;
import android.util.Log;

import com.dinerico.pos.db.Config.DataBaseHelper;
import com.dinerico.pos.model.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by josephleon on 10/11/14.
 */

public class OrderDB {

  private DataBaseHelper dbHelperORMLite;
  private final String LOG_TAG = getClass().getSimpleName();

  public OrderDB(Activity activity) {
    dbHelperORMLite = new DataBaseHelper(activity);
  }

  public Order create(Order order) {
    try {
      dbHelperORMLite.getDaoOrder().createOrUpdate(order);
      List<Order> list = dbHelperORMLite.getDaoOrder().queryForAll();
      Order orderSaved = list.get(list.size() - 1);
      Log.d(LOG_TAG, "Created order: " + orderSaved);
      return orderSaved;
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
      Log.e(LOG_TAG, "Error on create order");
      return null;
    }
  }

  public void delete(int id) {
    try {
      dbHelperORMLite.getDaoOrder().deleteById(id);
      Log.d(LOG_TAG, "Deleted order id: " + id);
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
      Log.e(LOG_TAG, "Error on delete order");
    }
  }

  public List<Order> getAll() {
    List<Order> list = new ArrayList<Order>();
    try {
      list = dbHelperORMLite.getDaoOrder().queryForAll();
      Log.d(LOG_TAG, "Order list: \n" + list);
      return list;
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
      Log.e(LOG_TAG, "Error on get all order");
      return list;
    }
  }
}