package com.dinerico.pos.viewmodel;

import com.dinerico.pos.model.Cart;
import com.dinerico.pos.model.ItemCart;
import com.dinerico.pos.model.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by josephleon on 10/9/14.
 */
public class CartViewModel {

  private Cart cart;


  public CartViewModel(Map<Integer, ArrayList<Product>> productList) {
    cart = Cart.getInstance(new ArrayList<ItemCart>());
    initializeCart(cart, productList);
  }

  public Cart getCart() {
    return cart;
  }

  public void setCart(Cart cart) {
    this.cart = cart;
  }

  private Cart initializeCart(Cart cart, Map<Integer, ArrayList<Product>> productList) {
    Iterator it = productList.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry) it.next();
      ArrayList<Product> sameProductList = (ArrayList<Product>) pair.getValue();
      ItemCart itemCart = new ItemCart(sameProductList);
      cart.getItems().add(itemCart);
    }
    return cart;
  }

}
