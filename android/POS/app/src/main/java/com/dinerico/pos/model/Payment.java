package com.dinerico.pos.model;

import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

/**
 * Created by josephleon on 10/11/14.
 */
public class Payment {
  @DatabaseField(generatedId = true)
  private int id;
  @DatabaseField(foreign = true, foreignAutoRefresh = true)
  private Order order;
  @DatabaseField(foreign = true, foreignAutoRefresh = true)
  private Customer customer;
  @DatabaseField(foreign = true, foreignAutoRefresh = true)
  private PaymentType paymentType;
  @DatabaseField
  private Date created;
  @DatabaseField
  private float amount;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public PaymentType getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(PaymentType paymentType) {
    this.paymentType = paymentType;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public float getAmount() {
    return amount;
  }

  public void setAmount(float amount) {
    this.amount = amount;
  }
}
