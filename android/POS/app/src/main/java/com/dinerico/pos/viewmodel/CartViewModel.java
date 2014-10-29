package com.dinerico.pos.viewmodel;

import com.dinerico.pos.db.ProductDB;
import com.dinerico.pos.model.Order;

/**
 * Created by josephleon on 10/9/14.
 */
public class CartViewModel {

  private Order order;

  private ProductDB productDB;

  public CartViewModel(Order order, ProductDB productDB) {
    this.productDB = productDB;
    this.order = order;
  }


  public Order getOrder() {
    return order;
  }

  public void setOrder(Order Order) {
    this.order = Order;
  }

}
