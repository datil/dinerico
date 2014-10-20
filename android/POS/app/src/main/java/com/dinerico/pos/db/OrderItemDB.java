package com.dinerico.pos.db;

import android.app.Activity;
import android.util.Log;

import com.dinerico.pos.db.Config.DataBaseHelper;
import com.dinerico.pos.model.OrderItem;

import java.util.Collections;
import java.util.List;

/**
 * Created by josephleon on 10/19/14.
 */

public class OrderItemDB {

  private DataBaseHelper dbHelperORMLite;
  private final String LOG_TAG = getClass().getSimpleName();

  public OrderItemDB(Activity activity) {
    dbHelperORMLite = new DataBaseHelper(activity);
  }

  public OrderItem create(OrderItem orderItem) {
    try {
      dbHelperORMLite.getDaoOrderItem().createOrUpdate(orderItem);
      List<OrderItem> list = dbHelperORMLite.getDaoOrderItem().queryForAll();
      OrderItem orderItemSaved = list.get(list.size() - 1);
      Log.d(LOG_TAG, "Created orderItem: " + orderItemSaved);
      return orderItemSaved;
    } catch (java.sql.SQLException e) {
      Log.e(LOG_TAG, "Error on create orderItem");
      e.printStackTrace();
      return orderItem;
    }
  }

  public void delete(int id) {
    try {
      dbHelperORMLite.getDaoOrderItem().deleteById(id);
      Log.d(LOG_TAG, "Deleted orderItem id: " + id);
    } catch (java.sql.SQLException e) {
      Log.e(LOG_TAG, "Error on delete orderItem");
      e.printStackTrace();
    }
  }

  public List<OrderItem> getAll() {
    try {
      List<OrderItem> list = dbHelperORMLite.getDaoOrderItem().queryForAll();
      Log.d(LOG_TAG, "OrderItem list: \n" + list);
      return list;
    } catch (java.sql.SQLException e) {
      Log.e(LOG_TAG, "Error on get all OrderItem");
      e.printStackTrace();
      return Collections.EMPTY_LIST;
    }
  }

}