package com.dinerico.pos.viewmodel;

import android.app.Activity;
import android.content.Intent;

import com.dinerico.pos.db.OrderDB;
import com.dinerico.pos.db.OrderItemDB;
import com.dinerico.pos.db.ProductDB;
import com.dinerico.pos.db.SessionDB;
import com.dinerico.pos.model.Account;
import com.dinerico.pos.model.Order;
import com.dinerico.pos.model.OrderItem;
import com.dinerico.pos.model.Product;
import com.dinerico.pos.view.HomeActivity;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.ForeignCollection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by josephleon on 10/3/14.
 */
public class ShopViewModel {

  private Order order;
  private Map<Integer, ArrayList<Product>> cartMap;
  private String search;
  private int counter;

  private OrderDB orderDB;
  private OrderItemDB orderItemDB;
  private ProductDB productDB;

  public ShopViewModel(Order order, ProductDB productDB,
                       OrderItemDB orderItemDB, OrderDB orderDB) {
    this.order = order;
    this.cartMap = new HashMap<Integer, ArrayList<Product>>();
    this.productDB = productDB;
    this.orderDB = orderDB;
    this.orderItemDB = orderItemDB;
    this.counter = 0;
  }

  public boolean logout(Activity activity) {
    SessionDB sessionDB = new SessionDB(activity);
    sessionDB.delete(Account.getInstance().getSession().getRowId());
    Intent intent = new Intent(activity, HomeActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent
            .FLAG_ACTIVITY_CLEAR_TASK);
    activity.startActivity(intent);
    activity.finish();
    Account.reset();
    return true;
  }


  public boolean addToCart(Product product) {
    product.setImageByte(null);
    product.setImage(null);
    ArrayList<Product> sameProductList = cartMap.get(product.getId());
    if (sameProductList != null)
      return sameProductList.add(product);
    else {
      sameProductList = new ArrayList<Product>();
      sameProductList.add(product);
      cartMap.put(product.getId(), sameProductList);
      return true;
    }
  }

  public Order generateOrder() {
    Order.reset();
    order = Order.getInstance();
    Iterator it = cartMap.entrySet().iterator();
    float total = 0;
    order.setCreated(new Date());
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry) it.next();
      ArrayList<Product> sameProductList = (ArrayList<Product>) pair.getValue();
      OrderItem orderItem = new OrderItem(sameProductList, order);
      order.getItems().add(orderItem);
      total += orderItem.getTotal();
    }
    order.setTotal(total);
    return order;

  }

  public String getSearch() {
    return search;
  }

  public void setSearch(String search) {
    this.search = search;
  }

  public ArrayList<Product> getCatalog() {
    ForeignCollection<Product> products = Account.getInstance().getStore()
            .getProducts();
    ArrayList<Product> list = new ArrayList<Product>();
    if (products != null) {
      try {
        CloseableIterator<Product> iterator = products.closeableIterator();
        while (iterator.hasNext())
          list.add(iterator.next());
        iterator.close();
        return list;
      } catch (SQLException e) {
        e.printStackTrace();
        System.out.println(list);
        return list;
      }
    } else
      return list;
  }


  public Map<Integer, ArrayList<Product>> getCartMap() {
    return cartMap;
  }

  public void setCartMap(Map<Integer, ArrayList<Product>> cartMap) {
    this.cartMap = cartMap;
  }

  public ProductDB getProductDB() {
    return productDB;
  }

  public void setProductDB(ProductDB productDB) {
    this.productDB = productDB;
  }

  public int getCounter() {
    return counter;
  }

  public void setCounter(int counter) {
    this.counter = counter;
  }
}
