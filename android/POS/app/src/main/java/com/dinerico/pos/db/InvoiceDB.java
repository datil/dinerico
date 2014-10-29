package com.dinerico.pos.db;

import android.app.Activity;
import android.util.Log;

import com.dinerico.pos.db.Config.DataBaseHelper;
import com.dinerico.pos.model.Invoice;

import java.util.Collections;
import java.util.List;

/**
 * Created by josephleon on 10/19/14.
 */

public class InvoiceDB {

  private DataBaseHelper dbHelperORMLite;
  private final String LOG_TAG = getClass().getSimpleName();

  public InvoiceDB(Activity activity) {
    dbHelperORMLite = new DataBaseHelper(activity);
  }

  public Invoice create(Invoice invoice) {
    try {
      dbHelperORMLite.getDaoInvoice().createOrUpdate(invoice);
      List<Invoice> list = dbHelperORMLite.getDaoInvoice().queryForAll();
      Invoice invoiceSaved = list.get(list.size() - 1);
      Log.d(LOG_TAG, "Created invoice: " + invoiceSaved);
      return invoiceSaved;
    } catch (java.sql.SQLException e) {
      Log.e(LOG_TAG, "Error on create invoice");
      e.printStackTrace();
      return invoice;
    }
  }

  public void delete(int id) {
    try {
      dbHelperORMLite.getDaoInvoice().deleteById(id);
      Log.d(LOG_TAG, "Deleted invoice id: " + id);
    } catch (java.sql.SQLException e) {
      Log.e(LOG_TAG, "Error on delete invoice");
      e.printStackTrace();
    }
  }

  public List<Invoice> getAll() {
    try {
      List<Invoice> list = dbHelperORMLite.getDaoInvoice().queryForAll();
      Log.d(LOG_TAG, "Invoice list: \n" + list);
      return list;
    } catch (java.sql.SQLException e) {
      Log.e(LOG_TAG, "Error on get all Invoice");
      e.printStackTrace();
      return Collections.EMPTY_LIST;
    }
  }


}