package com.dinerico.pos.model;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by josephleon on 10/10/14.
 */
public class Invoice implements Serializable{
  @DatabaseField (generatedId = true)
  private int id;
  @DatabaseField(foreign = true, foreignAutoRefresh = true)
  private Customer customer;
  @DatabaseField(foreign = true, foreignAutoRefresh = true)
  private Store store;
  @DatabaseField
  private int invoiceId;
  @DatabaseField
  private Date created;
  @DatabaseField(foreign = true, foreignAutoRefresh = true)
  private Order order;

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Store getStore() {
    return store;
  }

  public void setStore(Store store) {
    this.store = store;
  }

  public int getInvoiceId() {
    return invoiceId;
  }

  public void setInvoiceId(int invoiceId) {
    this.invoiceId = invoiceId;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }
}
