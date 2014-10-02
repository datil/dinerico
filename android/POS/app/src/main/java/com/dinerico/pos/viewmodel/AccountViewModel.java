package com.dinerico.pos.viewmodel;

import com.dinerico.pos.model.Account;
import com.octo.android.robospice.SpiceManager;

/**
 * Created by josephleon on 9/30/14.
 */
public class AccountViewModel {

  private Account model;
  private SpiceManager spiceManager;

  private String ruc;
  private String mobilePhone;
  private String email;
  private String password;

  public static AccountViewModel viewModel;

  public AccountViewModel(Account model, SpiceManager spiceManager) {
    this.spiceManager = spiceManager;
    this.model = model;
  }

  public void setRuc(String ruc) {
    this.ruc = ruc;
    model.setRUC(ruc);
  }

  public void setMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    model.setMobilePhone(mobilePhone);
  }

  public void setEmail(String email) {
    this.email = email;
    model.setEmail(email);
  }

  public void setPassword(String password) {
    this.password = password;
    model.setPassword(password);
  }

  public Account getModel() {
    return model;
  }

  @Override
  public String toString() {
    return "SignUpViewModel{ \n" +
            "model=" + model +
            "ruc='" + ruc + "'\n" +
            "mobilePhone='" + mobilePhone + "'\n" +
            "email='" + email + "'\n" +
            "password='" + password + "'\n" +
            '}';
  }

}


