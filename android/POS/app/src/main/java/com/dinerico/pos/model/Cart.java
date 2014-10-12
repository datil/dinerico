package com.dinerico.pos.model;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by josephleon on 10/8/14.
 */

public class Cart implements Serializable {

  @DatabaseField(generatedId = true)
  private String id;
  @DatabaseField
  private ArrayList<ItemCart> items;

  public Cart(ArrayList<ItemCart> items) {
    this.items = items;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ArrayList<ItemCart> getItems() {
    return items;
  }

  public void setItems(ArrayList<ItemCart> items) {
    this.items = items;
  }

  public float getTotal() {
    float sum = 0;
    for (ItemCart item : items) {
      sum += item.getItemTotal();
    }
    return sum;
  }

}