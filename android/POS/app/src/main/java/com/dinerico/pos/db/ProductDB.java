package com.dinerico.pos.db;

import android.app.Activity;
import android.util.Log;

import com.dinerico.pos.db.Config.DataBaseHelper;
import com.dinerico.pos.model.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by josephleon on 10/3/14.
 */

public class ProductDB {

  private DataBaseHelper dbHelperORMLite;
  private final String LOG_TAG = getClass().getSimpleName();

  public ProductDB(Activity activity) {
    dbHelperORMLite = new DataBaseHelper(activity);
  }

  public Product create(Product product) {
    try {
      dbHelperORMLite.getDaoProduct().createOrUpdate(product);
      List<Product> list = dbHelperORMLite.getDaoProduct().queryForAll();
      Product productSaved = list.get(list.size() - 1);
      Log.d(LOG_TAG, "Created product: " + productSaved);
      return productSaved;
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
      Log.e(LOG_TAG, "Error on create product");
      return null;
    }
  }

  public void delete(int id) {
    try {
      dbHelperORMLite.getDaoProduct().deleteById(id);
      Log.d(LOG_TAG, "Deleted product id: " + id);
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
      Log.e(LOG_TAG, "Error on delete product");
    }
  }

  public List<Product> getAll() {
    List<Product> list = new ArrayList<Product>();
    try {
      list = dbHelperORMLite.getDaoProduct().queryForAll();
      Log.d(LOG_TAG, "Product list: \n" + list);
      return list;
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
      Log.e(LOG_TAG, "Error on get all product");
      return list;
    }
  }

  public void refresh(Product product){
    try {
      dbHelperORMLite.getDaoProduct().refresh(product);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}