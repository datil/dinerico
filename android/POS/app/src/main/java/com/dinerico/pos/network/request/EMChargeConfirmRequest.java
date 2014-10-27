package com.dinerico.pos.network.request;

import com.dinerico.pos.model.EMResponse;
import com.dinerico.pos.model.EMPayment;
import com.dinerico.pos.network.config.Router;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import roboguice.util.temp.Ln;

/**
 * Created by josephleon on 10/22/14.
 */

public class EMChargeConfirmRequest extends RetrofitSpiceRequest<EMResponse, Router> {

  private EMPayment emPayment;

  public EMChargeConfirmRequest(EMPayment emPayment) {
    super(EMResponse.class, Router.class);
    this.emPayment = emPayment;
  }

  @Override
  public EMResponse loadDataFromNetwork() {
    Ln.d("Call web service ");
    return getService().chargeElectronicMoneyConfirm(emPayment);
  }

  public String createCacheKey() {
    return "charge.confirm" + emPayment.toString();
  }
}
