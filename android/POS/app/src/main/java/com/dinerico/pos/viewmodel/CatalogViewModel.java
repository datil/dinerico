package com.dinerico.pos.viewmodel;

import com.dinerico.pos.db.ProductDB;
import com.dinerico.pos.model.Account;
import com.dinerico.pos.model.Product;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.ForeignCollection;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by josephleon on 10/3/14.
 */
public class CatalogViewModel {
  private ProductDB productDB;

  private String search;


  public CatalogViewModel(ProductDB productDB) {
    this.productDB = productDB;
  }

  public ArrayList<Product> getProductList() {
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
        return list;
      }
    } else
      return new ArrayList<Product>();
  }

  public String getSearch() {
    return search;
  }

  public void setSearch(String search) {
    this.search = search;
  }
}
