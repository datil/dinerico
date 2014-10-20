package com.dinerico.pos.viewmodel;

import com.dinerico.pos.db.ProductDB;
import com.dinerico.pos.model.Order;
import com.dinerico.pos.model.OrderItem;

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

  public void refreshOrder() {
    float total = 0;
    for (OrderItem item : order.getItems()) {
      float subTotal = 0;
      productDB.refresh(item.getProduct());
      subTotal = item.getAmount() * item.getProduct().getPrice();
      item.setTotal(subTotal);
      total += item.getTotal();
    }
    order.setTotal(total);
  }

  public Order getOrder() {
    refreshOrder();
    return order;
  }

  public void setOrder(Order Order) {
    this.order = Order;
  }

}
