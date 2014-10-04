package com.dinerico.pos.viewmodel;

import com.dinerico.pos.db.ProductDB;
import com.dinerico.pos.model.Product;

import java.util.ArrayList;

/**
 * Created by josephleon on 10/3/14.
 */
public class ShopViewModel {

  private ArrayList<Product> catalog;
  private ArrayList<Product> cart;
  private String search;

  private ProductDB productDB;

  public ShopViewModel(ProductDB productDB) {
    this.cart = new ArrayList<Product>();
    this.productDB = productDB;
    this.catalog = (ArrayList) productDB.getAll();
  }

  private boolean addToCart(Product product) {
    return cart.add(product);
  }

  public String getSearch() {
    return search;
  }

  public void setSearch(String search) {
    this.search = search;
  }

  public ArrayList<Product> getCatalog() {
    catalog = (ArrayList) productDB.getAll();
    return catalog;
  }

  public void setCatalog(ArrayList<Product> catalog) {
    this.catalog = catalog;
  }

  public ArrayList<Product> getCart() {
    return cart;
  }

  public void setCart(ArrayList<Product> cart) {
    this.cart = cart;
  }
}
