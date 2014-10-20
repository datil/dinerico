package com.dinerico.pos.viewmodel;

import com.dinerico.pos.model.Account;
import com.dinerico.pos.model.Contributor;
import com.dinerico.pos.network.service.ContributorService;
import com.octo.android.robospice.request.listener.RequestListener;

/**
 * Created by josephleon on 9/30/14.
 */
public class AccountViewModel {

  private Account account;
  private ContributorService service;

  private String ruc;
  private String mobilePhone;
  private String email;
  private String password;

  public AccountViewModel(Account account,
                          ContributorService service) {
    this.account = account;
    this.service = service;
  }

  public void getDetailInfoAccount(String ruc, RequestListener<Contributor>
          listener) {
    service.getInfo(ruc, listener);
  }

  public void setRuc(String ruc) {
    this.ruc = ruc;
    account.getStore().setRUC(ruc);
  }

  public void setMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    account.getStore().setNumeroCelular(mobilePhone);
  }

  public void setEmail(String email) {
    this.email = email;
    account.setEmail(email);
  }

  public void setPassword(String password) {
    this.password = password;
    account.setPassword(password);
  }

  public Account getAccount() {
    return account;
  }


  @Override
  public String toString() {
    return "AccountViewModel{ \n" +
            "account=" + account + "'\n" +
            "service=" + service + "'\n" +
            "ruc='" + ruc + "'\n" +
            "mobilePhone='" + mobilePhone + "'\n" +
            "email='" + email + "'\n" +
            "password='" + password + "'\n" +
            '}';
  }
}


