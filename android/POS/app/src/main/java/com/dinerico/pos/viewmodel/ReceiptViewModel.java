package com.dinerico.pos.viewmodel;

import com.dinerico.pos.exception.ValidationError;
import com.dinerico.pos.model.Address;
import com.dinerico.pos.model.Customer;
import com.dinerico.pos.model.Invoice;
import com.dinerico.pos.network.service.InvoiceService;
import com.octo.android.robospice.request.listener.RequestListener;

/**
 * Created by josephleon on 10/10/14.
 */
public class ReceiptViewModel {

  private String email;
  private String names;
  private String address;
  private String telephoneNumber;
  private String customerId;
  private boolean finalConsumer;

  private Invoice invoice;
  private Customer customer;
  private InvoiceService service;

  public ReceiptViewModel(Invoice invoice, Customer customer,
                          InvoiceService service) {
    this.invoice = invoice;
    this.customer = customer;
    this.service = service;
  }

  public boolean validate() throws ValidationError {
    if (finalConsumer)
      return customer.isValidEmail() && customer.isValidCustomerId() && customer
              .isValidName();
    else
      return customer.isValidEmail() && customer.isValidCustomerId() && customer
              .isValidName() && customer.isValidAddress() && customer
              .isValidTelephone();
  }

  public void createInvoice(Invoice invoice, RequestListener listener) {
    service.registerInvoice(invoice, listener);
  }

  public Invoice getInvoice() {
    return invoice;
  }

  public void setInvoice(Invoice invoice) {
    this.invoice = invoice;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    customer.setEmail(email);
    this.email = email;
  }

  public String getNames() {
    return names;
  }

  public void setNames(String names) {
    customer.setName(names);
    this.names = names;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    Address address1 = new Address();
    address1.setCalle(address);
    customer.setAddress(address1);
    this.address = address;
  }

  public String getTelephoneNumber() {
    return telephoneNumber;
  }

  public void setTelephoneNumber(String telephoneNumber) {
    customer.setTelephone(telephoneNumber);
    this.telephoneNumber = telephoneNumber;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    customer.setCustomerId(customerId);
    this.customerId = customerId;
  }

  public boolean isFinalConsumer() {
    return finalConsumer;
  }

  public void setFinalConsumer(boolean finalConsumer) {
    this.finalConsumer = finalConsumer;
  }
}
