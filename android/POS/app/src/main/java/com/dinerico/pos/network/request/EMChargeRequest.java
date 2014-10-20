package com.dinerico.pos.network.request;

import com.dinerico.pos.model.Payment;
import com.dinerico.pos.network.config.BCERouter;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import roboguice.util.temp.Ln;

/**
 * Created by josephleon on 10/11/14.
 */

public class EMChargeRequest extends RetrofitSpiceRequest<Payment, BCERouter> {

  private Payment emPayment;

  public EMChargeRequest(Payment emPayment) {
    super(Payment.class, BCERouter.class);
    this.emPayment = emPayment;
  }

  @Override
  public Payment loadDataFromNetwork() {
    Ln.d("Call web service ");
    return getService().chargeElectronicMoney(emPayment);
  }

  public String createCacheKey() {
    return "charge." + emPayment.toString();
  }
}
