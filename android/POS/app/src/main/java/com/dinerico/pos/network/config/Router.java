package com.dinerico.pos.network.config;

import com.dinerico.pos.model.Contributor;
import com.dinerico.pos.model.Invoice;

import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

/**
 * Created by josephleon on 9/30/14.
 */

public interface Router {

  @Headers("Content-Type: application/json")
  @GET("/contribuyentes/{RUC}")
  Contributor createAccount(@Path("RUC") String RUC);

  Invoice createInvoice(Invoice invoice);
}