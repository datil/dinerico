package com.dinerico.pos.viewmodel;

import com.dinerico.pos.exception.ValidationError;
import com.dinerico.pos.model.Customer;
import com.dinerico.pos.model.EMResponse;
import com.dinerico.pos.model.EMPayment;
import com.dinerico.pos.network.service.EMService;
import com.octo.android.robospice.request.listener.RequestListener;

/**
 * Created by josephleon on 10/10/14.
 */
public class EMPaymentViewModel {

  private Customer customer;
  private EMService emService;

  private String customerId;
  private String mobilePhone;
  private String pin;

  public EMPaymentViewModel(Customer customer, EMService emService) {
    this.customer = customer;
    this.emService = emService;
  }

  public void emPayment(String storeMobilePhone, double amount,
                        RequestListener<EMResponse>
                                listener) {
    EMPayment emPayment = new EMPayment();
    emPayment.setBrandId(EMPayment.BRAND_ID);
    emPayment.setCurrency(EMPayment.CURRENCY);
    emPayment.setLanguage(EMPayment.LANGUAGE);
    emPayment.setUtfi(EMPayment.UTFI);
    emPayment.setPin(pin);
    emPayment.setAmount(amount);
    emPayment.setDocument(customer.getIdentificacion());
    emPayment.setMsisdnSrc(customer.getCelular());
    emPayment.setMsisdnTar(storeMobilePhone);
    emService.charge(emPayment, listener);
  }

  public void emPaymentConfirm(String storeMobilePhone, double amount,
                               RequestListener<EMResponse> listener) {
    EMPayment emPayment = new EMPayment();
    emPayment.setBrandId(EMPayment.BRAND_ID);
    emPayment.setCurrency(EMPayment.CURRENCY);
    emPayment.setLanguage(EMPayment.LANGUAGE);
    emPayment.setUtfi(EMPayment.UTFI);
    emPayment.setPin(pin);
    emPayment.setAmount(amount);
    emPayment.setDocument(customer.getIdentificacion());
    emPayment.setMsisdnSrc(customer.getCelular());
    emPayment.setMsisdnTar(storeMobilePhone);
    emService.chargeConfirm(emPayment, listener);
  }

  public boolean validate() throws ValidationError {
    return customer.isValidIdentificacion() && customer.isValidMobilePhone();
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    customer.setIdentificacion(customerId);
    this.customerId = customerId;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public void setMobilePhone(String mobilePhone) {
    customer.setCelular(mobilePhone);
    this.mobilePhone = mobilePhone;
  }

  public String getPin() {
    return pin;
  }

  public void setPin(String pin) {
    this.pin = pin;
  }
}
