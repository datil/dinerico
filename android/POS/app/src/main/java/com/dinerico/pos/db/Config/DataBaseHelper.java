package com.dinerico.pos.db.Config;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dinerico.pos.R;
import com.dinerico.pos.model.Account;
import com.dinerico.pos.model.Session;
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
  private static final int DATABASE_VERSION = 4;

  private Dao<Session, Integer> sessionDAO = null;
  private Dao<Account, Integer> accountDAO = null;

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

  @Override
  public void close() {
    super.close();
    sessionDAO = null;
    accountDAO = null;
  }

}
