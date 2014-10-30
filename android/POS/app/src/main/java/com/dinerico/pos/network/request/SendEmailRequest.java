package com.dinerico.pos.network.request;

import com.dinerico.pos.model.EmailBody;
import com.dinerico.pos.model.EmailResponse;
import com.dinerico.pos.network.config.Router;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import roboguice.util.temp.Ln;

/**
 * Created by josephleon on 10/29/14.
 */

public class SendEmailRequest extends RetrofitSpiceRequest<EmailResponse, Router> {

  private EmailBody email;

  public SendEmailRequest(EmailBody email) {
    super(EmailResponse.class, Router.class);
    this.email = email;
  }

  @Override
  public EmailResponse loadDataFromNetwork() {
    Ln.d("Call web service ");
    return getService().sendEmail(email);
  }

  public String createCacheKey() {
    return "send.mail." + email.hashCode();
  }
}
