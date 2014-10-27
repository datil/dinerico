package com.dinerico.pos.network.request;

import com.dinerico.pos.model.Invoice;
import com.dinerico.pos.model.InvoiceResponse;
import com.dinerico.pos.network.config.Router;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import roboguice.util.temp.Ln;

/**
 * Created by josephleon on 10/10/14.
 */

public class CreateInvoiceRequest extends RetrofitSpiceRequest<InvoiceResponse, Router> {

  private Invoice invoice;

  public CreateInvoiceRequest(Invoice invoice) {
    super(InvoiceResponse.class, Router.class);
    this.invoice = invoice;
  }

  @Override
  public InvoiceResponse loadDataFromNetwork() {
    Ln.d("Call web service ");
    return getService().createInvoice(invoice);
  }

  public String createCacheKey() {
    return "create.invoice" + invoice.hashCode();
  }
}
