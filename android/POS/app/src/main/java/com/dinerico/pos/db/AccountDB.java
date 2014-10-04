package com.dinerico.pos.db;

import android.app.Activity;
import android.util.Log;

import com.dinerico.pos.db.Config.DataBaseHelper;
import com.dinerico.pos.model.Account;

import java.util.Collections;
import java.util.List;

/**
 * Created by josephleon on 10/1/14.
 */

public class AccountDB {

  private DataBaseHelper dbHelperORMLite;
  private final String LOG_TAG = getClass().getSimpleName();

  public AccountDB(Activity activity) {
    dbHelperORMLite = new DataBaseHelper(activity);
  }

  public Account create(Account account) {
    try {
      dbHelperORMLite.getDaoAccount().createOrUpdate(account);
      List<Account> list = dbHelperORMLite.getDaoAccount().queryForAll();
      Account accountSaved = list.get(list.size() - 1);
      Log.d(LOG_TAG, "Created account: " + accountSaved);
      dbHelperORMLite.close();
      return accountSaved;
    } catch (java.sql.SQLException e) {
      dbHelperORMLite.close();
      Log.e(LOG_TAG, "Error on create account");
      return account;
    }
  }

  public void delete(int id) {
    try {
      dbHelperORMLite.getDaoAccount().deleteById(id);
      dbHelperORMLite.close();
      Log.d(LOG_TAG, "Deleted account id: " + id);
    } catch (java.sql.SQLException e) {
      dbHelperORMLite.close();
      Log.e(LOG_TAG, "Error on delete account");
    }
  }

  public List<Account> getAll() {
    try {
      List<Account> list = dbHelperORMLite.getDaoAccount().queryForAll();
      Log.d(LOG_TAG, "Account list: \n" + list);
      dbHelperORMLite.close();
      return list;
    } catch (java.sql.SQLException e) {
      dbHelperORMLite.close();
      Log.e(LOG_TAG, "Error on get all Account");
      return Collections.EMPTY_LIST;
    }
  }
}