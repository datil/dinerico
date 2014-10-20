package com.dinerico.pos.db;

import android.app.Activity;
import android.util.Log;

import com.dinerico.pos.db.Config.DataBaseHelper;
import com.dinerico.pos.model.PaymentType;

import java.util.Collections;
import java.util.List;

/**
 * Created by josephleon on 10/19/14.
 */

public class PaymentTypeDB {

  private DataBaseHelper dbHelperORMLite;
  private final String LOG_TAG = getClass().getSimpleName();

  public PaymentTypeDB(Activity activity) {
    dbHelperORMLite = new DataBaseHelper(activity);
  }

  public PaymentType create(PaymentType paymentType) {
    try {
      dbHelperORMLite.getDaoPaymentType().createOrUpdate(paymentType);
      List<PaymentType> list = dbHelperORMLite.getDaoPaymentType().queryForAll();
      PaymentType paymentTypeSaved = list.get(list.size() - 1);
      Log.d(LOG_TAG, "Created paymentType: " + paymentTypeSaved);
      return paymentTypeSaved;
    } catch (java.sql.SQLException e) {
      Log.e(LOG_TAG, "Error on create paymentType");
      e.printStackTrace();
      return paymentType;
    }
  }

  public void delete(int id) {
    try {
      dbHelperORMLite.getDaoPaymentType().deleteById(id);
      Log.d(LOG_TAG, "Deleted paymentType id: " + id);
    } catch (java.sql.SQLException e) {
      Log.e(LOG_TAG, "Error on delete paymentType");
      e.printStackTrace();
    }
  }

  public List<PaymentType> getAll() {
    try {
      List<PaymentType> list = dbHelperORMLite.getDaoPaymentType().queryForAll();
      Log.d(LOG_TAG, "PaymentType list: \n" + list);
      return list;
    } catch (java.sql.SQLException e) {
      Log.e(LOG_TAG, "Error on get all PaymentType");
      e.printStackTrace();
      return Collections.EMPTY_LIST;
    }
  }

}