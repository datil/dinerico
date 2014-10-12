package com.dinerico.pos.network.request;

import com.dinerico.pos.model.EMCharge;
import com.dinerico.pos.model.EMChargeReceipt;
import com.dinerico.pos.network.config.BCERouter;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import roboguice.util.temp.Ln;

/**
 * Created by josephleon on 10/11/14.
 */

public class EMChargeRequest extends RetrofitSpiceRequest<EMChargeReceipt, BCERouter> {

  private EMCharge emCharge;

  public EMChargeRequest(EMCharge emCharge) {
    super(EMChargeReceipt.class, BCERouter.class);
    this.emCharge = emCharge;
  }

  @Override
  public EMChargeReceipt loadDataFromNetwork() {
    Ln.d("Call web service ");
    return getService().chargeElectronicMoney(emCharge);
  }

  public String createCacheKey() {
    return "charge." + emCharge.toString();
  }
}
