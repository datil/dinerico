package com.dinerico.pos.viewmodel;

import com.dinerico.pos.R;
import com.dinerico.pos.db.AccountDB;
import com.dinerico.pos.exception.ValidationError;
import com.dinerico.pos.model.Account;
import com.dinerico.pos.model.Contributor;
import com.dinerico.pos.network.service.ContributorService;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by josephleon on 9/30/14.
 */
public class AccountViewModel {

  private Account account;
  private AccountDB accountDB;
  private ContributorService service;

  private String ruc;
  private String mobilePhone;
  private String email;
  private String password;
  private String invoiceKey;

  public AccountViewModel(Account account,
                          ContributorService service, AccountDB accountDB) {
    this.account = account;
    this.service = service;
    this.accountDB = accountDB;
  }

  public boolean validate() throws ValidationError {
    return account.getStore().isValidRUC() && account.isValidEmail() &&
            !existsAccountWithSameEmail() && account.isValidPassword() &&
            account.getStore().isValidMobilePhone();
  }

  public void getDetailInfoAccount(RequestListener<Contributor>
                                           listener) {
    service.getInfo(ruc, listener);
  }

  public boolean existsAccountWithSameEmail() throws ValidationError {
    ArrayList<Account> list = (ArrayList) accountDB.queryByEmail(email);
    if (list.isEmpty())
      return false;
    else {
      HashMap<String, Integer> errorData = new HashMap<String, Integer>();
      errorData.put("userMessage", R.string.accountWithSameEmail);
      throw new ValidationError("Exists a account with input email", errorData);

    }

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

  public String getInvoiceKey() {
    return invoiceKey;
  }

  public void setInvoiceKey(String invoiceKey) {
    account.getStore().setClaveFacturacionElectronica(invoiceKey);
    this.invoiceKey = invoiceKey;
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


