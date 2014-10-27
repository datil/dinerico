package com.dinerico.pos.model;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by josephleon on 10/21/14.
 */
public class TaxProduct implements Serializable{

  public static final String PRODUCT_ID_FIELD_NAME = "product_id";
  public static final String TAX_ID_FIELD_NAME = "tax_id";

  @DatabaseField(generatedId = true)
  private int id;
  @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = PRODUCT_ID_FIELD_NAME)
  private Product product;
  @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = TAX_ID_FIELD_NAME)
  private Tax tax;

  public void TaxProduct(){
    product = new Product();
    tax = new Tax();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Tax getTax() {
    return tax;
  }

  public void setTax(Tax tax) {
    this.tax = tax;
  }


  @Override
  public String toString() {
    return "TaxProduct{ \n" +
            "id=" + id + "\n"+
            "product=" + product + "\n"+
            "tax=" + tax + "\n"+
            '}';
  }
}
