package com.dinerico.pos.model;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by josephleon on 10/8/14.
 */
public class OrderItem implements Serializable {
  @DatabaseField(generatedId = true)
  private int id;
  @DatabaseField
  private int amount;
  @DatabaseField
  private float total;
  @DatabaseField(foreign = true, foreignAutoRefresh = true)
  private Product product;
  @DatabaseField(foreign = true, foreignAutoRefresh = true)
  private Order order;

  public OrderItem (){

  }

  public OrderItem(ArrayList<Product> products, Order order) {
    if (products.size() > 0) {
      product = products.get(0);
      amount = products.size();
      total = amount * product.getPrice();
      this.order = order;
    }
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
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

  public float getTotal() {
    return total;
  }

  public void setTotal(float total) {
    this.total = total;
  }


  @Override
  public String toString() {
    return "OrderItem{ \n" +
            "id=" + id + "\n" +
            "amount=" + amount +"\n" +
            "total=" + total +"\n" +
            "product=" + product +"\n" +
            '}';
  }
}