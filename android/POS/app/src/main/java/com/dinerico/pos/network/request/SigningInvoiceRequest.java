package com.dinerico.pos.network.request;

import com.dinerico.pos.model.SigningInvoice;
import com.dinerico.pos.model.InvoiceResponse;
import com.dinerico.pos.network.config.Router;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import roboguice.util.temp.Ln;

/**
 * Created by josephleon on 10/25/14.
 */

public class SigningInvoiceRequest extends RetrofitSpiceRequest<InvoiceResponse, Router> {

  private SigningInvoice signingInvoice;

  public SigningInvoiceRequest(SigningInvoice signingInvoice) {
    super(InvoiceResponse.class, Router.class);
    this.signingInvoice = signingInvoice;
  }

  @Override
  public InvoiceResponse loadDataFromNetwork() {
    Ln.d("Call web service ");
    return getService().signInvoice(signingInvoice);
  }

  public String createCacheKey() {
    return "signing.invoice" + signingInvoice.hashCode();
  }
}
