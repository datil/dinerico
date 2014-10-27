package com.dinerico.pos.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by josephleon on 10/21/14.
 */
public class Tax {
  @DatabaseField(generatedId = true)
  private int id;
  @DatabaseField
  private String description;
  @DatabaseField
  private int amountPercentage;
  @DatabaseField
  private String percentage;
  @DatabaseField
  private String typeId;
  @DatabaseField
  private String type;

  public Tax(String type, String typeId, String percentage,
             int amountPercentage, String description) {
    this.type = type;
    this.typeId = typeId;
    this.percentage = percentage;
    this.amountPercentage = amountPercentage;
    this.description = description;
  }

  public Tax(){

  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getTypeId() {
    return typeId;
  }

  public void setTypeId(String typeId) {
    this.typeId = typeId;
  }

  public String getPercentage() {
    return percentage;
  }

  public void setPercentage(String percentage) {
    this.percentage = percentage;
  }

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

  public int getAmountPercentage() {
    return amountPercentage;
  }

  public void setAmountPercentage(int amountPercentage) {
    this.amountPercentage = amountPercentage;
  }


  @Override
  public String toString() {
    return "Tax{ \n" +
            "id=" + id + "'\n" +
            "description='" + description + "'\n" +
            "amountPercentage=" + amountPercentage + "'\n" +
            "percentage='" + percentage + "'\n" +
            "typeId='" + typeId + "'\n" +
            "type='" + type + "'\n" +
            '}';
  }
}
