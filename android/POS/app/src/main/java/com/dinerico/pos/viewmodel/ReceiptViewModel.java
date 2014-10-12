package com.dinerico.pos.viewmodel;

import com.dinerico.pos.model.Invoice;
import com.dinerico.pos.network.service.InvoiceService;
import com.octo.android.robospice.request.listener.RequestListener;

/**
 * Created by josephleon on 10/10/14.
 */
public class ReceiptViewModel {

  private Invoice invoice;
  private InvoiceService service;

  private String email;

  public ReceiptViewModel(Invoice invoice, InvoiceService service) {
    this.invoice = invoice;
    this.service = service;
  }

  public void createInvoice(Invoice invoice, RequestListener listener){
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
    this.email = email;
  }
}
