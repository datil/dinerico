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
      double subTotal = 0;
      productDB.refresh(item.getProduct());
      double iva = 1 + item.getProduct()
              .getTaxProduct("iva").getTax().getAmountPercentage()/100.0;
      subTotal = item.getAmount() * item.getProduct().getPrice() * iva;
      item.setTotal(Math.round(subTotal*100.0)/100.0);
      total += item.getTotal();
    }
    order.setTotal(Math.round(total*100.0)/100.0);
  }

  public Order getOrder() {
    refreshOrder();
    return order;
  }

  public void setOrder(Order Order) {
    this.order = Order;
  }

}
