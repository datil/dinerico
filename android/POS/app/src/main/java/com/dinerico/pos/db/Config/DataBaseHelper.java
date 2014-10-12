package com.dinerico.pos.db.Config;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dinerico.pos.R;
import com.dinerico.pos.model.Account;
import com.dinerico.pos.model.Charge;
import com.dinerico.pos.model.Product;
import com.dinerico.pos.model.Sales;
import com.dinerico.pos.model.Session;
import com.dinerico.pos.model.Store;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by josephleon on 10/1/14.
 */

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {

  private static final String DATABASE_NAME = "database.db";
  private static final int DATABASE_VERSION = 8;

  private Dao<Session, Integer> sessionDAO = null;
  private Dao<Account, Integer> accountDAO = null;
  private Dao<Product, Integer> productDAO = null;
  private Dao<Store, Integer> storeDAO = null;
  private Dao<Sales, Integer> salesDAO = null;
  private Dao<Charge, Integer> chargeDAO = null;

  public DataBaseHelper(Activity context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION,
            R.raw.ormlite_config);
  }

  @Override
  public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
    try {
      Log.i(DataBaseHelper.class.getSimpleName(), "onCreate");
      TableUtils.createTable(connectionSource, Session.class);
      TableUtils.createTable(connectionSource, Account.class);
      TableUtils.createTable(connectionSource, Product.class);
      TableUtils.createTable(connectionSource, Store.class);
      TableUtils.createTable(connectionSource, Sales.class);
      TableUtils.createTable(connectionSource, Charge.class);
    } catch (SQLException e) {
      Log.e(DataBaseHelper.class.getSimpleName(), "Can't create database", e);
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
                        int oldVersion, int newVersion) {
    try {
      Log.i(DataBaseHelper.class.getName(), "onUpgrade");
      TableUtils.dropTable(connectionSource, Session.class, true);
      TableUtils.dropTable(connectionSource, Account.class, true);
      TableUtils.dropTable(connectionSource, Product.class, true);
      TableUtils.dropTable(connectionSource, Store.class, true);
      TableUtils.dropTable(connectionSource, Sales.class, true);
      TableUtils.dropTable(connectionSource, Charge.class, true);
      onCreate(db);
    } catch (SQLException e) {
      Log.e(DataBaseHelper.class.getName(), "Can't drop databases", e);
      throw new RuntimeException(e);
    }
  }

  public Dao<Session, Integer> getDaoSession() throws SQLException {
    if (sessionDAO == null) {
      sessionDAO = getDao(Session.class);
    }
    return sessionDAO;
  }

  public Dao<Account, Integer> getDaoAccount() throws SQLException {
    if (accountDAO == null) {
      accountDAO = getDao(Account.class);
    }
    return accountDAO;
  }

  public Dao<Product, Integer> getDaoProduct() throws SQLException {
    if (productDAO == null) {
      productDAO = getDao(Product.class);
    }
    return productDAO;
  }

  public Dao<Store, Integer> getDaoStore() throws SQLException {
    if (storeDAO == null) {
      storeDAO = getDao(Store.class);
    }
    return storeDAO;
  }
  public Dao<Sales, Integer> getDaoSales() throws SQLException {
    if (salesDAO == null) {
      salesDAO = getDao(Sales.class);
    }
    return salesDAO;
  }
  public Dao<Charge, Integer> getDaoCharge() throws SQLException {
    if (chargeDAO == null) {
      chargeDAO = getDao(Charge.class);
    }
    return chargeDAO;
  }

  @Override
  public void close() {
    super.close();
    sessionDAO = null;
    accountDAO = null;
    productDAO = null;
    storeDAO = null;
    salesDAO = null;
    chargeDAO = null;
  }
}
