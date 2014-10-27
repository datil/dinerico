package com.dinerico.pos.db;

import android.app.Activity;
import android.util.Log;

import com.dinerico.pos.db.Config.DataBaseHelper;
import com.dinerico.pos.model.Product;
import com.dinerico.pos.model.TaxProduct;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by josephleon on 10/21/14.
 */

public class TaxProductDB {

  private DataBaseHelper dbHelperORMLite;
  private final String LOG_TAG = getClass().getSimpleName();

  public TaxProductDB(Activity activity) {
    dbHelperORMLite = new DataBaseHelper(activity);
  }

  public TaxProduct create(TaxProduct taxProduct) {
    try {
      dbHelperORMLite.getDaoTaxProduct().createOrUpdate(taxProduct);
      List<TaxProduct> list = dbHelperORMLite.getDaoTaxProduct().queryForAll();
      TaxProduct taxProductSaved = list.get(list.size() - 1);
      Log.d(LOG_TAG, "Created taxProduct: " + taxProductSaved);
      return taxProductSaved;
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
      Log.e(LOG_TAG, "Error on create taxProduct");
      return null;
    }

  }

  public void delete(int id) {
    try {
      dbHelperORMLite.getDaoTaxProduct().deleteById(id);
      Log.d(LOG_TAG, "Deleted taxProduct id: " + id);
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
      Log.e(LOG_TAG, "Error on delete taxProduct");
    }
  }

  public List<TaxProduct> getAll() {
    try {
      List<TaxProduct> list = dbHelperORMLite.getDaoTaxProduct().queryForAll();
      Log.d(LOG_TAG, "TaxProduct list: \n" + list);
      return list;
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
      Log.e(LOG_TAG, "Error on get all taxProduct");
      return Collections.EMPTY_LIST;
    }
  }

  public List<TaxProduct> queryByProductAndTypeTax(Product product,
                                                   String taxType) {
    try {
      Map<String, Object> arg = new HashMap<String, Object>();
      arg.put(TaxProduct.PRODUCT_ID_FIELD_NAME, product);
      arg.put("type", taxType);
      List<TaxProduct> list = dbHelperORMLite.getDaoTaxProduct()
              .queryForFieldValues(arg);
      Log.d(LOG_TAG, "TaxProduct list: \n" + list);
      return list;
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
      Log.e(LOG_TAG, "Error on get taxProduct by Product and TaxType");
      return Collections.EMPTY_LIST;
    }
  }
}