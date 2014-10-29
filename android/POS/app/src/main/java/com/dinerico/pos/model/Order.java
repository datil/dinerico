package com.dinerico.pos.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by josephleon on 10/10/14.
 */
public class Order implements Serializable{

  @DatabaseField(generatedId = true)
  private int id;
  @DatabaseField(foreign = true, foreignAutoRefresh = true)
  private Store store;
  @DatabaseField(foreign = true, foreignAutoRefresh = true)
  private Customer customer;
  @DatabaseField(foreign = true, foreignAutoRefresh = true)
  private Invoice invoice;
  @DatabaseField(foreign = true, foreignAutoRefresh = true)
  private Payment payment;
  @DatabaseField
  private double total;
  private double ivaTax;
  @DatabaseField
  private Date created;
  @ForeignCollectionField
  private ForeignCollection<OrderItem> itemsOnDB;
  private ArrayList<OrderItem> items;

  public static Order order;

  public Order() {
    store = new Store();
    customer = new Customer();
    invoice = new Invoice();
    payment = new Payment();
    created = new Date();
    items = new ArrayList<OrderItem>();
  }

  public static Order getInstance() {
    if (order == null)
      order = new Order();
    return order;
  }

  public static void reset() {
    order = null;
  }

  public ArrayList<OrderItem> getItems() {
    return items;
  }

  public void setItems(ArrayList<OrderItem> items) {
    this.items = items;
  }

  public ForeignCollection<OrderItem> getItemsOnDB() {
    return itemsOnDB;
  }

  public Payment getPayment() {
    return payment;
  }

  public void setPayment(Payment payment) {
    this.payment = payment;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public Store getStore() {
    return store;
  }

  public void setStore(Store store) {
    this.store = store;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Invoice getInvoice() {
    return invoice;
  }

  public void setInvoice(Invoice invoice) {
    this.invoice = invoice;
  }

  public double getIvaTax() {
    return ivaTax;
  }

  public void setIvaTax(double ivaTax) {
    this.ivaTax = ivaTax;
  }

  @Override
  public String toString() {
    return "Order{ \n" +
            "id=" + id + "\n" +
            "store=" + store + "\n" +
            "customer=" + customer +"\n" +
            "invoice=" + invoice +"\n" +
            "payment=" + payment +"\n" +
            "total=" + total +"\n" +
            "created=" + created +"\n" +
            "itemsOnDB=" + itemsOnDB +"\n" +
            "items=" + items +"\n" +
            '}';
  }
}
