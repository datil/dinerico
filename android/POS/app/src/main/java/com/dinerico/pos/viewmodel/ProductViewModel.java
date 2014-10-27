package com.dinerico.pos.viewmodel;

import android.graphics.Bitmap;

import com.dinerico.pos.db.ProductDB;
import com.dinerico.pos.db.TaxDB;
import com.dinerico.pos.db.TaxProductDB;
import com.dinerico.pos.model.Account;
import com.dinerico.pos.model.Product;
import com.dinerico.pos.model.Tax;
import com.dinerico.pos.model.TaxProduct;
import com.dinerico.pos.util.ImageHelper;
import com.dinerico.pos.util.Utils;

import java.util.ArrayList;

/**
 * Created by josephleon on 10/3/14.
 */
public class ProductViewModel {

  private Product product;
  private Tax iva;
  private TaxProduct taxProduct;

  private String name;
  private String price;
  private Bitmap image;
  private String initials;

  private ProductDB productDB;
  private TaxProductDB taxProductDB;
  private TaxDB taxDB;


  public ProductViewModel(Product product,
                          ProductDB productDB,
                          TaxProductDB taxProductDB, TaxDB taxDB) {
    this.taxProductDB = taxProductDB;
    this.productDB = productDB;
    this.product = productDB.getById(product.getId());
    this.taxProduct = this.product.getTaxProduct("iva");
    this.product.setStore(Account.getInstance().getStore());
    this.taxDB = taxDB;
  }


  public Product saveProduct() {
    Product p = productDB.create(product);
    taxProductDB.create(taxProduct);
    return p;
  }

  public ArrayList<Tax> taxList() {
    return (ArrayList<Tax>) taxDB.getAll();
  }

  public void deleteProduct() {
    productDB.delete(product.getId());
  }

  public Tax getIva() {
    return iva;
  }

  public void setIva(Tax iva) {
    taxProduct.setTax(iva);
    taxProduct.setProduct(product);
    this.iva = iva;
  }

  public void setName(String name) {
    this.name = name;
    product.setName(name);
  }

  public void setPrice(String price) {
    this.price = price;
    if (Utils.isValidString(price))
      product.setPrice(Float.parseFloat(price));
    else
      product.setPrice(Float.parseFloat("0.00"));
  }

  public void setImage(Bitmap image) {
    this.image = image;
    product.setImage(image);
    product.setImageByte(ImageHelper.getBytes(image));
  }

  public void setInitials(String initials) {
    this.initials = initials;
    product.setInitials(initials);
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }
}


