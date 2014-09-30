package com.dinerico.pos.viewmodel;

import android.content.Intent;

import com.dinerico.pos.exception.ValidationError;
import com.dinerico.pos.model.Account;
import com.dinerico.pos.model.Contributor;
import com.dinerico.pos.network.request.DetailAccountRequest;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.request.listener.RequestListener;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;

/**
 * Created by josephleon on 9/30/14.
 */
public class SignUpViewModel {

  private Account model;
  private SpiceManager spiceManager;
  private Intent intent;

  private String ruc;
  private String mobilePhone;
  private String email;
  private String password;

  private static String OBJECT = "Account";
  private static String RUC = "ruc";
  private static String MOBILE_PHONE = "mobilePhone";
  private static String PASSWORD = "password";
  private static String EMAIL = "email";

  public SignUpViewModel(Account model, SpiceManager spiceManager,
                         Intent intent) {
    this.spiceManager = spiceManager;
    this.model = model;
    this.intent = intent;
  }

  public void createAccount(Contributor contributor) {

    ParseAnalytics.trackAppOpened(intent);
    ParseObject Greetings = new ParseObject(OBJECT);
    Greetings.put(RUC, model.getRUC());
    Greetings.put(MOBILE_PHONE, model.getMobilePhone());
    Greetings.put(PASSWORD, model.getPassword());
    Greetings.put(EMAIL, model.getEmail());
    Greetings.saveInBackground();
  }

  public void getDetailInfoAccount(RequestListener<Contributor> listener) throws
          ValidationError {
    model.validate();
    DetailAccountRequest request = new DetailAccountRequest(model.getRUC());
    spiceManager.execute(request, request.createCacheKey(), 5000, listener);
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


