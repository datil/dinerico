package com.dinerico.pos.network.request;

import com.dinerico.pos.model.MailingInvoice;
import com.dinerico.pos.model.InvoiceResponse;
import com.dinerico.pos.network.config.Router;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import roboguice.util.temp.Ln;

/**
 * Created by josephleon on 10/25/14.
 */

public class MailInvoiceRequest extends RetrofitSpiceRequest<InvoiceResponse, Router> {

  private MailingInvoice mailingInvoice;

  public MailInvoiceRequest(MailingInvoice mailingInvoice) {
    super(InvoiceResponse.class, Router.class);
    this.mailingInvoice = mailingInvoice;
  }

  @Override
  public InvoiceResponse loadDataFromNetwork() {
    Ln.d("Call web service ");
    return getService().mailInvoiceToCustomer(mailingInvoice);
  }

  public String createCacheKey() {
    return "mail.invoice" + mailingInvoice.hashCode();
  }
}
