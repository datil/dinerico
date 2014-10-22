package com.dinerico.pos.viewmodel;

import android.graphics.Bitmap;

import com.dinerico.pos.db.ProductDB;
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
  private TaxProduct taxProduct;
  private Tax iva;

  private String name;
  private String price;
  private Bitmap image;
  private String initials;

  private ProductDB productDB;
  private TaxProductDB taxProductDB;

  public ProductViewModel(Product product, TaxProduct taxProduct,
                          ProductDB productDB,
                          TaxProductDB taxProductDB) {
    this.taxProductDB = taxProductDB;
    this.taxProduct = taxProduct;
    this.product = product;
    this.productDB = productDB;

    this.taxProduct.setProduct(product);
    this.product.setStore(Account.getInstance().getStore());
  }

  public Tax getTax(String tax) {
    ArrayList<TaxProduct> list = (ArrayList) taxProductDB
            .queryByProductAndTypeTax(product, tax);
    return list.get(0).getTax();
  }

  public Product saveProduct() {
    Product p = productDB.create(product);
    taxProductDB.create(taxProduct);
    return p;
  }

  public void deleteProduct() {
    productDB.delete(product.getId());
  }

  public Tax getIva() {
    return iva;
  }

  public void setIva(Tax iva) {
    taxProduct.setTax(iva);
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


