package com.dinerico.pos.viewmodel;

import android.graphics.Bitmap;

import com.dinerico.pos.db.ProductDB;
import com.dinerico.pos.model.Product;
import com.dinerico.pos.util.ImageHelper;
import com.dinerico.pos.util.Utils;

/**
 * Created by josephleon on 10/3/14.
 */
public class ProductViewModel {

  private Product model;

  private String name;
  private String price;
  private Bitmap image;
  private String initials;

  private ProductDB productDB;

  public ProductViewModel(Product model, ProductDB productDB) {
    this.model = model;
    this.productDB = productDB;
  }

  public Product saveProduct() {
    return productDB.create(model);
  }

  public void deleteProduct() {
    productDB.delete(model.getId());
  }

  public void setName(String name) {
    this.name = name;
    model.setName(name);
  }

  public void setPrice(String price) {
    this.price = price;
    if(Utils.isValidString(price))
      model.setPrice(Float.parseFloat(price));
    else
      model.setPrice(Float.parseFloat("0.00"));
  }

  public void setImage(Bitmap image) {
    this.image = image;
    model.setImage(image);
    model.setImageByte(ImageHelper.getBytes(image));
  }

  public void setInitials(String initials) {
    this.initials = initials;
    model.setInitials(initials);
  }

  public Product getModel() {
    return model;
  }

  public void setModel(Product model) {
    this.model = model;
  }
}


