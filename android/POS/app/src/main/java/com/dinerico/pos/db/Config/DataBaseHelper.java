package com.dinerico.pos.db.Config;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dinerico.pos.R;
import com.dinerico.pos.model.Account;
import com.dinerico.pos.model.Address;
import com.dinerico.pos.model.Customer;
import com.dinerico.pos.model.Invoice;
import com.dinerico.pos.model.Order;
import com.dinerico.pos.model.OrderItem;
import com.dinerico.pos.model.Payment;
import com.dinerico.pos.model.PaymentType;
import com.dinerico.pos.model.Product;
import com.dinerico.pos.model.Session;
import com.dinerico.pos.model.Store;
import com.dinerico.pos.model.Tax;
import com.dinerico.pos.model.TaxProduct;
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
  private static final int DATABASE_VERSION = 20;

  private Dao<Session, Integer> sessionDAO = null;
  private Dao<Account, Integer> accountDAO = null;
  private Dao<Product, Integer> productDAO = null;
  private Dao<Store, Integer> storeDAO = null;
  private Dao<PaymentType, Integer> paymentTypeDAO = null;
  private Dao<OrderItem, Integer> orderItemDAO = null;
  private Dao<Order, Integer> orderDAO = null;
  private Dao<Customer, Integer> customerDAO = null;
  private Dao<Invoice, Integer> invoiceDAO = null;
  private Dao<Payment, Integer> paymentDAO = null;
  private Dao<Address, Integer> addressDAO = null;
  private Dao<Tax, Integer> taxDAO = null;
  private Dao<TaxProduct, Integer> taxProductDAO = null;

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
      TableUtils.createTable(connectionSource, Address.class);
      TableUtils.createTable(connectionSource, Store.class);
      TableUtils.createTable(connectionSource, Order.class);
      TableUtils.createTable(connectionSource, OrderItem.class);
      TableUtils.createTable(connectionSource, Payment.class);
      TableUtils.createTable(connectionSource, PaymentType.class);
      TableUtils.createTable(connectionSource, Invoice.class);
      TableUtils.createTable(connectionSource, Customer.class);
      TableUtils.createTable(connectionSource, Tax.class);
      TableUtils.createTable(connectionSource, TaxProduct.class);

      Tax tax1 = new Tax("iva", "6", "0%", 0, "No incluye I.V.A.");
      Tax tax2 = new Tax("iva", "2", "12%", 12, "I.V.A. 12%");
      Tax tax3 = new Tax("iva", "0", "0%", 0, "I.V.A. 0%");
      Tax tax4 = new Tax("iva", "", "0%", 0, "Exento de I.V.A.");

      getDaoTax().create(tax1);
      getDaoTax().create(tax2);
      getDaoTax().create(tax3);
      getDaoTax().create(tax4);

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
      TableUtils.dropTable(connectionSource, Address.class, true);
      TableUtils.dropTable(connectionSource, Store.class, true);
      TableUtils.dropTable(connectionSource, Order.class, true);
      TableUtils.dropTable(connectionSource, Payment.class, true);
      TableUtils.dropTable(connectionSource, PaymentType.class, true);
      TableUtils.dropTable(connectionSource, Invoice.class, true);
      TableUtils.dropTable(connectionSource, OrderItem.class, true);
      TableUtils.dropTable(connectionSource, Customer.class, true);
      TableUtils.dropTable(connectionSource, Tax.class, true);
      TableUtils.dropTable(connectionSource, TaxProduct.class, true);
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
  public Dao<Order, Integer> getDaoOrder() throws SQLException {
    if (orderDAO == null) {
      orderDAO = getDao(Order.class);
    }
    return orderDAO;
  }

  public Dao<OrderItem, Integer> getDaoOrderItem() throws SQLException {
    if (orderItemDAO == null) {
      orderItemDAO = getDao(OrderItem.class);
    }
    return orderItemDAO;
  }

  public Dao<PaymentType, Integer> getDaoPaymentType() throws SQLException {
    if (paymentTypeDAO == null) {
      paymentTypeDAO = getDao(PaymentType.class);
    }
    return paymentTypeDAO;
  }

  public Dao<Invoice, Integer> getDaoInvoice() throws SQLException {
    if (invoiceDAO == null) {
      invoiceDAO = getDao(Invoice.class);
    }
    return invoiceDAO;
  }

  public Dao<Payment, Integer> getDaoPayment() throws SQLException {
    if (paymentDAO == null) {
      paymentDAO = getDao(Payment.class);
    }
    return paymentDAO;
  }

  public Dao<Customer, Integer> getDaoCustomer() throws SQLException {
    if (customerDAO == null) {
      customerDAO = getDao(Customer.class);
    }
    return customerDAO;
  }

  public Dao<Address, Integer> getDaoAddress() throws SQLException {
    if (addressDAO == null) {
      addressDAO = getDao(Address.class);
    }
    return addressDAO;
  }

  public Dao<Tax, Integer> getDaoTax() throws SQLException {
    if (taxDAO == null) {
      taxDAO = getDao(Tax.class);
    }
    return taxDAO;
  }

  public Dao<TaxProduct, Integer> getDaoTaxProduct() throws SQLException {
    if (taxProductDAO == null) {
      taxProductDAO = getDao(TaxProduct.class);
    }
    return taxProductDAO;
  }

  @Override
  public void close() {
    super.close();
    sessionDAO = null;
    accountDAO = null;
    productDAO = null;
    storeDAO = null;
    orderDAO = null;
    paymentTypeDAO = null;
    paymentDAO = null;
    customerDAO = null;
    orderItemDAO = null;
    invoiceDAO = null;
    addressDAO = null;
    taxProductDAO = null;
    taxDAO = null;
  }
}
