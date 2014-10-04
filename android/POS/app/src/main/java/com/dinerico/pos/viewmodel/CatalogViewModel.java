package com.dinerico.pos.viewmodel;

import com.dinerico.pos.db.ProductDB;
import com.dinerico.pos.model.Product;

import java.util.ArrayList;

/**
 * Created by josephleon on 10/3/14.
 */
public class CatalogViewModel {
  private ProductDB productDB;

  private String search;

  private ArrayList<Product> productList;

  public CatalogViewModel(ProductDB productDB) {
    this.productDB = productDB;
    productList = (ArrayList<Product>)productDB.getAll();
  }

  public ArrayList<Product> getProductList() {
    productList = (ArrayList<Product>)productDB.getAll();
    return productList;
  }

  public void setProductList(ArrayList<Product> productList) {
    this.productList = productList;
  }

  public String getSearch() {
    return search;
  }

  public void setSearch(String search) {
    this.search = search;
  }
}
