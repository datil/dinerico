package com.dinerico.pos.model;

import java.util.ArrayList;

/**
 * Created by josephleon on 10/29/14.
 */
public class EmailBody {

  public static final String KEY = "R4tGnygeVLD5nsjBWRXwHA";
  public static final String TEMPLATE_NAME = "R4tGnygeVLD5nsjBWRXwHA";

  private String key;
  private String templateName;
  private ArrayList<Tag> templateContent;
  private EmailMessage emailMessage;
  private boolean async;

  public EmailBody(){
    templateContent = new ArrayList<Tag>();
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getTemplateName() {
    return templateName;
  }

  public void setTemplateName(String templateName) {
    this.templateName = templateName;
  }

  public ArrayList<Tag> getTemplateContent() {
    return templateContent;
  }

  public void setTemplateContent(ArrayList<Tag> templateContent) {
    this.templateContent = templateContent;
  }

  public EmailMessage getEmailMessage() {
    return emailMessage;
  }

  public void setEmailMessage(EmailMessage emailMessage) {
    this.emailMessage = emailMessage;
  }

  public boolean isAsync() {
    return async;
  }

  public void setAsync(boolean async) {
    this.async = async;
  }
}
