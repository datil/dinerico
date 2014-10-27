package com.dinerico.pos.network.service;

import com.dinerico.pos.model.EMResponse;
import com.dinerico.pos.model.EMPayment;
import com.dinerico.pos.network.config.ApiService;
import com.dinerico.pos.network.request.EMChargeConfirmRequest;
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

  public void charge(EMPayment emPayment, RequestListener<EMResponse> listener) {
    EMChargeRequest request = new EMChargeRequest(emPayment);
    spiceManager.execute(request, request.createCacheKey(), ApiService.CACHE_TIME,
            listener);
  }

  public void chargeConfirm(EMPayment emPayment, RequestListener<EMResponse>
          listener) {
    EMChargeConfirmRequest request = new EMChargeConfirmRequest(emPayment);
    spiceManager.execute(request, request.createCacheKey(), ApiService.CACHE_TIME,
            listener);
  }

}
