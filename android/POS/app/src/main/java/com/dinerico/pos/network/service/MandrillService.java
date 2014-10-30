package com.dinerico.pos.network.service;

import com.microtripit.mandrillapp.lutung.MandrillApi;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;

/**
 * Created by josephleon on 10/30/14.
 */
public class MandrillService {

  MandrillApi mandrillApi;

  public MandrillService(){

  }

  public void sendEmail(MandrillMessage message){
    MandrillApi mandrillApi = new MandrillApi("<put ur Mandrill API key here>");
  }
}
