package com.dinerico.pos.network.config;

import com.dinerico.pos.model.Contributor;
import com.dinerico.pos.model.EMResponse;
import com.dinerico.pos.model.EMPayment;
import com.dinerico.pos.model.Invoice;
import com.dinerico.pos.model.SigningInvoice;
import com.dinerico.pos.model.InvoiceResponse;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by josephleon on 9/30/14.
 */

public interface Router {

  @Headers("Content-Type: application/json")
  @GET("/datum/contribuyentes/{RUC}")
  Contributor createAccount(@Path("RUC") String RUC);

  @Headers("Content-Type: application/json")
  @POST("/dinerico-api/remit-pre")
  EMResponse chargeElectronicMoney(@Body EMPayment emPayment);

  @Headers("Content-Type: application/json")
  @POST("/dinerico-api/remit-confirm")
  EMResponse chargeElectronicMoneyConfirm(@Body EMPayment emPayment);

  @Headers("Content-Type: application/json")
  @POST("/facturar")
  InvoiceResponse createInvoice(@Body Invoice invoice);

  @Headers("Content-Type: application/json")
  @POST("/firmar")
  InvoiceResponse signInvoice(@Body SigningInvoice signingInvoice);
}