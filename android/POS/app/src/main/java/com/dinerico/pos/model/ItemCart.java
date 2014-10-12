package com.dinerico.pos.model;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by josephleon on 10/8/14.
 */
public class ItemCart extends Product implements Serializable {
  @DatabaseField(generatedId = true)
  private int id;
  @DatabaseField
  private int amount;
  @DatabaseField
  private float itemTotal;
  @DatabaseField
  private ArrayList<Product> productList;
  @DatabaseField
  private Product product;

  public ItemCart(ArrayList<Product> productList) {
    this.productList = productList;
    amount = productList.size();
    product = productList.get(0);

    for (Product product : productList) {
      itemTotal += product.getPrice();
    }
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public float getItemTotal() {
    return itemTotal;
  }

  public void setItemTotal(float itemTotal) {
    this.itemTotal = itemTotal;
  }

  public ArrayList<Product> getProductList() {
    return productList;
  }

  public void setProductList(ArrayList<Product> productList) {
    this.productList = productList;
  }
}