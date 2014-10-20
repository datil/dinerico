package com.dinerico.pos.viewmodel;

import com.dinerico.pos.exception.ValidationError;
import com.dinerico.pos.model.Customer;
import com.dinerico.pos.network.service.EMService;

/**
 * Created by josephleon on 10/10/14.
 */
public class CustomerViewModel {

  private Customer model;
  private EMService emService;

  private String customerId;
  private String mobilePhone;
  private String pin;

  public CustomerViewModel(Customer model, EMService emService) {
    this.model = model;
    this.emService = emService;
  }

  public boolean validate() throws ValidationError {
    return model.isValidCustomerId() && model.isValidMobilePhone();
  }

  public Customer getModel() {
    return model;
  }

  public void setModel(Customer model) {
    this.model = model;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    model.setCustomerId(customerId);
    this.customerId = customerId;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public void setMobilePhone(String mobilePhone) {
    model.setMobilePhone(mobilePhone);
    this.mobilePhone = mobilePhone;
  }

  public EMService getEmService() {
    return emService;
  }

  public void setEmService(EMService emService) {
    this.emService = emService;
  }

  public String getPin() {
    return pin;
  }

  public void setPin(String pin) {
    this.pin = pin;
  }
}
