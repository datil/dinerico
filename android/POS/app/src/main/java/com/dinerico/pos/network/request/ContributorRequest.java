package com.dinerico.pos.network.request;

import com.dinerico.pos.model.Contributor;
import com.dinerico.pos.network.config.Router;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import roboguice.util.temp.Ln;

/**
 * Created by josephleon on 9/30/14.
 */

public class ContributorRequest extends RetrofitSpiceRequest<Contributor, Router> {

  private String RUC;

  public ContributorRequest(String RUC) {
    super(Contributor.class, Router.class);
    this.RUC = RUC;
  }

  @Override
  public Contributor loadDataFromNetwork() {
    Ln.d("Call web service ");
    return getService().createAccount(RUC);
  }

  public String createCacheKey() {
    return "get.detail.account." + RUC;
  }
}
