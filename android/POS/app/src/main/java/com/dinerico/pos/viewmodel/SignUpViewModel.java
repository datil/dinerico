package com.dinerico.pos.viewmodel;

import com.dinerico.pos.db.AccountDB;
import com.dinerico.pos.model.Account;
import com.octo.android.robospice.SpiceManager;

/**
 * Created by josephleon on 10/2/14.
 */
public class SignUpViewModel {

  private SpiceManager spiceManager;
  private AccountDB accountDB;


  public static AccountViewModel viewModel;

  public SignUpViewModel(AccountDB accountDB,SpiceManager spiceManager) {
    this.spiceManager = spiceManager;
    this.accountDB = accountDB;
  }

  public Account createAccount(Account account) {
    return accountDB.create(account);
  }

}


