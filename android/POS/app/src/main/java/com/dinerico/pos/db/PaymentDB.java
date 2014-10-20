package com.dinerico.pos.db;

import android.app.Activity;
import android.util.Log;

import com.dinerico.pos.db.Config.DataBaseHelper;
import com.dinerico.pos.model.Payment;

import java.util.Collections;
import java.util.List;

/**
 * Created by josephleon on 10/11/14.
 */
public class PaymentDB {

  private DataBaseHelper dbHelperORMLite;
  private final String LOG_TAG = getClass().getSimpleName();

  public PaymentDB(Activity activity) {
    dbHelperORMLite = new DataBaseHelper(activity);
  }

  public Payment create(Payment payment) {
    try {
      dbHelperORMLite.getDaoPayment().createOrUpdate(payment);
      List<Payment> list = dbHelperORMLite.getDaoPayment().queryForAll();
      Payment paymentSaved = list.get(list.size() - 1);
      Log.d(LOG_TAG, "Created payment: " + paymentSaved);
      return paymentSaved;
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
      Log.e(LOG_TAG, "Error on create payment");
      return null;
    }

  }

  public void delete(int id) {
    try {
      dbHelperORMLite.getDaoPayment().deleteById(id);
      Log.d(LOG_TAG, "Deleted payment id: " + id);
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
      Log.e(LOG_TAG, "Error on delete payment");
    }
  }

  public List<Payment> getAll() {
    try {
      List<Payment> list = dbHelperORMLite.getDaoPayment().queryForAll();
      Log.d(LOG_TAG, "Payment list: \n" + list);
      return list;
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
      Log.e(LOG_TAG, "Error on get all payment");
      return Collections.EMPTY_LIST;
    }
  }
}