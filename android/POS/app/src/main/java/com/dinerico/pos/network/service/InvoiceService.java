package com.dinerico.pos.network.service;

import com.dinerico.pos.model.Invoice;
import com.dinerico.pos.model.SigningInvoice;
import com.dinerico.pos.network.config.FactoraApiService;
import com.dinerico.pos.network.request.CreateInvoiceRequest;
import com.dinerico.pos.network.request.SigningInvoiceRequest;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.request.listener.RequestListener;

/**
 * Created by josephleon on 10/10/14.
 */

public class InvoiceService {

  private SpiceManager spiceManager;

  public InvoiceService(SpiceManager spiceManager) {
    this.spiceManager = spiceManager;
  }

  public void register(Invoice invoice, RequestListener listener) {
    CreateInvoiceRequest request = new CreateInvoiceRequest(invoice);
    spiceManager.execute(request, request.createCacheKey(),
            FactoraApiService.CACHE_TIME, listener);
  }

  public void sign(SigningInvoice invoice, RequestListener listener) {
    SigningInvoiceRequest request = new SigningInvoiceRequest(invoice);
    spiceManager.execute(request, request.createCacheKey(), FactoraApiService.CACHE_TIME, listener);
  }

}
