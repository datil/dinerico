package com.dinerico.pos.viewmodel;

import com.dinerico.pos.db.AccountDB;
import com.dinerico.pos.db.AddressDB;
import com.dinerico.pos.db.SessionDB;
import com.dinerico.pos.db.StoreDB;
import com.dinerico.pos.model.Account;
import com.dinerico.pos.model.Session;
import com.dinerico.pos.model.Store;

import java.util.Calendar;

/**
 * Created by josephleon on 10/2/14.
 */
public class SignUpViewModel {

  private AccountDB accountDB;
  private SessionDB sessionDB;
  private StoreDB storeDB;
  private AddressDB addressDB;

  public static AccountViewModel viewModel;

  public SignUpViewModel(AccountDB accountDB,SessionDB sessionDB,
                         StoreDB storeDB,AddressDB addressDB) {
    this.accountDB = accountDB;
    this.sessionDB = sessionDB;
    this.storeDB = storeDB;
    this.addressDB = addressDB;
  }

  public void signUpOnDB(Store store){
    Store globalStore = Account.getInstance().getStore();

    globalStore.setDireccion(store.getDireccion());
    addressDB.create(store.getDireccion());

    globalStore.setEstado(store.getEstado());
    globalStore.setCodigo(store.getCodigo());
    storeDB.create(Account.getInstance().getStore());

    Calendar c = Calendar.getInstance();
    Session sessionFake = new Session();
    sessionFake.setCreated(c.toString());
    sessionFake.setAccount(Account.getInstance());

    //Create session without account reference becasuse is not saved yet and
    // will not relate with session on database
    sessionDB.create(sessionFake);

    Account.getInstance().setSession(sessionFake);

    Account.setInstance(accountDB.create(Account.getInstance()));

    //Updated session with account reference that is saved now
    sessionDB.create(sessionFake);
  }


}


