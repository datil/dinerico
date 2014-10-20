package com.dinerico.pos.network.service;

import com.dinerico.pos.model.Payment;
import com.dinerico.pos.network.config.BCEApiService;
import com.dinerico.pos.network.request.EMChargeRequest;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.request.listener.RequestListener;

/**
 * Created by josephleon on 10/11/14.
 */

public class EMService {

  private SpiceManager spiceManager;

  public EMService(SpiceManager spiceManager) {
    this.spiceManager = spiceManager;
  }

  public void charge(Payment emPayment, RequestListener<Payment> listener) {
    EMChargeRequest request = new EMChargeRequest(emPayment);
    spiceManager.execute(request, request.createCacheKey(), BCEApiService.CACHE_TIME,
            listener);
  }

}
