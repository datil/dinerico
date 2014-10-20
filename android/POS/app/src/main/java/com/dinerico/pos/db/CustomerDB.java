package com.dinerico.pos.db;

import android.app.Activity;
import android.util.Log;

import com.dinerico.pos.db.Config.DataBaseHelper;
import com.dinerico.pos.model.Customer;

import java.util.Collections;
import java.util.List;

/**
 * Created by josephleon on 10/19/14.
 */

public class CustomerDB {

  private DataBaseHelper dbHelperORMLite;
  private final String LOG_TAG = getClass().getSimpleName();

  public CustomerDB(Activity activity) {
    dbHelperORMLite = new DataBaseHelper(activity);
  }

  public Customer create(Customer customer) {
    try {
      dbHelperORMLite.getDaoCustomer().createOrUpdate(customer);
      List<Customer> list = dbHelperORMLite.getDaoCustomer().queryForAll();
      Customer customerSaved = list.get(list.size() - 1);
      Log.d(LOG_TAG, "Created customer: " + customerSaved);
      return customerSaved;
    } catch (java.sql.SQLException e) {
      Log.e(LOG_TAG, "Error on create customer");
      e.printStackTrace();
      return customer;
    }
  }

  public void delete(int id) {
    try {
      dbHelperORMLite.getDaoCustomer().deleteById(id);
      Log.d(LOG_TAG, "Deleted customer id: " + id);
    } catch (java.sql.SQLException e) {
      Log.e(LOG_TAG, "Error on delete customer");
      e.printStackTrace();
    }
  }

  public List<Customer> getAll() {
    try {
      List<Customer> list = dbHelperORMLite.getDaoCustomer().queryForAll();
      Log.d(LOG_TAG, "Customer list: \n" + list);
      return list;
    } catch (java.sql.SQLException e) {
      Log.e(LOG_TAG, "Error on get all Customer");
      e.printStackTrace();
      return Collections.EMPTY_LIST;
    }
  }

}