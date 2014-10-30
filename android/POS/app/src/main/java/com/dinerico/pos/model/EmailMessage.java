package com.dinerico.pos.model;

import java.util.ArrayList;

/**
 * Created by josephleon on 10/29/14.
 */
public class EmailMessage {

  private static final String BCC_ADDRESS = "juanantonioplaza@datilmedia.com";
  private static final boolean MERGE = true;
  private static final String MERGE_LANGUAGE = "mailchimp";

  private String subject;
  private ArrayList<EmailAccount> to;
  private boolean important;
  private String bccAddress;
  private boolean merge;
  private String mergeLanguage;
  private ArrayList<Tag> globalMergeVars;

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public ArrayList<EmailAccount> getTo() {
    return to;
  }

  public void setTo(ArrayList<EmailAccount> to) {
    this.to = to;
  }

  public boolean isImportant() {
    return important;
  }

  public void setImportant(boolean important) {
    this.important = important;
  }

  public String getBccAddress() {
    return bccAddress;
  }

  public void setBccAddress(String bccAddress) {
    this.bccAddress = bccAddress;
  }

  public boolean isMerge() {
    return merge;
  }

  public void setMerge(boolean merge) {
    this.merge = merge;
  }

  public String getMergeLanguage() {
    return mergeLanguage;
  }

  public void setMergeLanguage(String mergeLanguage) {
    this.mergeLanguage = mergeLanguage;
  }

  public ArrayList<Tag> getGlobalMergeVars() {
    return globalMergeVars;
  }

  public void setGlobalMergeVars(ArrayList<Tag> globalMergeVars) {
    this.globalMergeVars = globalMergeVars;
  }
}
