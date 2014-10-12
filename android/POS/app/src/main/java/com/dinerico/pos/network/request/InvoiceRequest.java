package com.dinerico.pos.network.request;

import com.dinerico.pos.model.Invoice;
import com.dinerico.pos.network.config.Router;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import roboguice.util.temp.Ln;

/**
 * Created by josephleon on 10/10/14.
 */

public class InvoiceRequest extends RetrofitSpiceRequest<Invoice, Router> {

  private Invoice invoice;

  public InvoiceRequest(Invoice invoice) {
    super(Invoice.class, Router.class);
    this.invoice = invoice;
  }

  @Override
  public Invoice loadDataFromNetwork() {
    Ln.d("Call web service ");
    return getService().createInvoice(invoice);
  }

  public String createCacheKey() {
    return "create.invoice" + invoice.toString();
  }
}
