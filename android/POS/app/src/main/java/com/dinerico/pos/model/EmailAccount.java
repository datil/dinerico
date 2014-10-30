package com.dinerico.pos.model;

/**
 * Created by josephleon on 10/29/14.
 */
public class EmailAccount {
  public static final String TYPE = "to";

  private String email;
  private String name;
  private String type;

  public EmailAccount(String email, String name, String type) {
    this.email = email;
    this.name = name;
    this.type = type;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
