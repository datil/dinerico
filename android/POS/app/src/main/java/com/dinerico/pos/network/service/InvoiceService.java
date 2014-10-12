package com.dinerico.pos.network.service;

import com.dinerico.pos.model.Invoice;
import com.dinerico.pos.network.config.ApiService;
import com.dinerico.pos.network.request.InvoiceRequest;
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

  public void registerInvoice(Invoice invoice, RequestListener listener) {
    InvoiceRequest request = new InvoiceRequest(invoice);
    spiceManager.execute(request, request.createCacheKey(), ApiService.CACHE_TIME, listener);
  }

}
