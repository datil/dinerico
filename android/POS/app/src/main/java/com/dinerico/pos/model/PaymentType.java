package com.dinerico.pos.model;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by josephleon on 10/10/14.
 */
public class PaymentType implements Serializable{
  @DatabaseField(generatedId = true)
  private int id;
  @DatabaseField
  private String description;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
