package com.dinerico.pos.network.service;

import com.dinerico.pos.model.EmailBody;
import com.dinerico.pos.model.EmailResponse;
import com.dinerico.pos.network.config.ApiService;
import com.dinerico.pos.network.request.SendEmailRequest;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.request.listener.RequestListener;

public class EmailService {

  private SpiceManager spiceManager;

  public EmailService(SpiceManager spiceManager) {
    this.spiceManager = spiceManager;
  }

  public void send(EmailBody email, RequestListener<EmailResponse> listener) {
    SendEmailRequest request = new SendEmailRequest(email);
    spiceManager.execute(request, request.createCacheKey(), ApiService.CACHE_TIME,
            listener);
  }

}
