package com.dinerico.pos.viewmodel;

import com.dinerico.pos.model.Account;
import com.dinerico.pos.model.Contributor;
import com.dinerico.pos.network.service.ContributorService;
import com.octo.android.robospice.request.listener.RequestListener;

/**
 * Created by josephleon on 9/30/14.
 */
public class AccountViewModel {

  private Account model;
  private ContributorService service;

  private String ruc;
  private String mobilePhone;
  private String email;
  private String password;

  public AccountViewModel(Account model, ContributorService service) {
    this.model = model;
    this.service = service;
  }

  public void getDetailInfoAccount(String ruc,RequestListener<Contributor> listener) {
    service.getInfo(ruc, listener);
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
            "sendReceipt='" + mobilePhone + "'\n" +
            "customerId='" + email + "'\n" +
            "password='" + password + "'\n" +
            '}';
  }

}


