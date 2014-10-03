package com.dinerico.pos.network.service;

import com.dinerico.pos.model.Contributor;
import com.dinerico.pos.network.config.ApiService;
import com.dinerico.pos.network.request.ContributorRequest;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.request.listener.RequestListener;

/**
 * Created by josephleon on 10/2/14.
 */

public class ContributorService {

  private SpiceManager spiceManager;

  public ContributorService(SpiceManager spiceManager) {
    this.spiceManager = spiceManager;
  }

  public void getInfo(String ruc, RequestListener<Contributor> listener) {
    ContributorRequest request = new ContributorRequest(ruc);
    spiceManager.execute(request, request.createCacheKey(), ApiService.CACHE_TIME, listener);
  }

}
