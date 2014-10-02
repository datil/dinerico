package com.dinerico.pos.viewmodel;

import com.dinerico.pos.model.Contributor;
import com.dinerico.pos.network.config.ApiService;
import com.dinerico.pos.network.request.ContributorRequest;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.request.listener.RequestListener;

/**
 * Created by josephleon on 10/2/14.
 */
public class ContributorViewModel {

  private Contributor model;

  private SpiceManager spiceManager;

  public ContributorViewModel(SpiceManager spiceManager) {
    this.spiceManager = spiceManager;
  }

  public void getDetailInfoAccount(String ruc,
                                   RequestListener<Contributor> listener) {
    ContributorRequest request = new ContributorRequest(ruc);
    spiceManager.execute(request, request.createCacheKey(), ApiService.CACHE_TIME, listener);
  }

  public Contributor getModel() {
    return model;
  }

  public void setModel(Contributor model) {
    this.model = model;
  }
}
