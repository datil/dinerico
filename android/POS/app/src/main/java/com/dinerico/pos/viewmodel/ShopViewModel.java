package com.dinerico.pos.viewmodel;

import com.dinerico.pos.db.ProductDB;
import com.dinerico.pos.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by josephleon on 10/3/14.
 */
public class ShopViewModel {

  private ArrayList<Product> catalog;
  private Map<Integer, ArrayList<Product>> cart;
  private String search;
  private int counter;

  private static ShopViewModel shop;

  private ProductDB productDB;

  public static ShopViewModel getInstance(ProductDB productDB) {
    if (shop == null)
      shop = new ShopViewModel(productDB);
    return shop;
  }

  public static void reset() {
    shop = null;
  }

  public ShopViewModel(ProductDB productDB) {
    this.cart = new HashMap<Integer, ArrayList<Product>>();
    this.productDB = productDB;
    this.catalog = (ArrayList) productDB.getAll();
    this.counter = 0;
  }

  public boolean addToCart(Product product) {
    product.setImageByte(null);
    product.setImage(null);
    ArrayList<Product> sameProductList = cart.get(product.getId());
    if (sameProductList != null)
      return sameProductList.add(product);
    else {
      sameProductList = new ArrayList<Product>();
       sameProductList.add(product);
      cart.put(product.getId(),sameProductList);
      return true;
    }
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

  public Map<Integer, ArrayList<Product>> getCart() {
    return cart;
  }

  public void setCart(Map<Integer, ArrayList<Product>> cart) {
    this.cart = cart;
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
