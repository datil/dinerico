package com.dinerico.pos.model;

import android.app.Activity;
import android.graphics.Bitmap;

import com.dinerico.pos.R;
import com.dinerico.pos.db.ProductDB;
import com.dinerico.pos.exception.ValidationError;
import com.dinerico.pos.util.Utils;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by josephleon on 10/3/14.
 */
public class Product implements Serializable {

  @DatabaseField(generatedId = true)
  private int id;
  @DatabaseField
  private String name;
  @DatabaseField
  private String initials;
  @DatabaseField
  private double price;
  @DatabaseField(dataType = DataType.BYTE_ARRAY)
  private byte[] imageByte;
  private Bitmap image;
  @DatabaseField
  private int color;
  @DatabaseField(foreign = true, foreignAutoRefresh = true)
  private Store store;
  @ForeignCollectionField
  private ForeignCollection<TaxProduct> taxes;


  public boolean validate() throws ValidationError {
    if (isValidName() && isValidPrice()) ;
    return true;
  }

  public boolean isValidName() throws ValidationError {
    if (!Utils.isValidString(name)) {
      HashMap<String, Integer> errorData = new HashMap<String, Integer>();
      errorData.put("userMessage", R.string.noValidProductName);
      throw new ValidationError("Product name null or blank", errorData);
    }
    return true;
  }

  public boolean isValidPrice() throws ValidationError {
    if (!Utils.isValidDouble(price)) {
      HashMap<String, Integer> errorData = new HashMap<String, Integer>();
      errorData.put("userMessage", R.string.noValidPrice);
      throw new ValidationError("Product price null or blank", errorData);
    }
    return true;
  }

  public TaxProduct getTaxProduct(String type) {
    TaxProduct taxProduct = new TaxProduct();
    if (taxes != null) {
      CloseableIterator<TaxProduct> iterator = taxes.closeableIterator();
      while (iterator.hasNext()) {
        taxProduct = iterator.next();
        if (taxProduct.getTax().getType().equals(type))
          return taxProduct;
      }
      try {
        iterator.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    } else
      return taxProduct;
    return taxProduct;
  }

  public void refresh(Activity activity) {
    ProductDB productDB = new ProductDB(activity);
    productDB.refresh(this);
  }

  public ForeignCollection<TaxProduct> getTaxes() {
    return taxes;
  }

  public void setTaxes(ForeignCollection<TaxProduct> taxes) {
    this.taxes = taxes;
  }

  public Store getStore() {
    return store;
  }

  public void setStore(Store store) {
    this.store = store;
  }

  public Bitmap getImage() {
    return image;
  }

  public void setImage(Bitmap image) {
    this.image = image;
  }

  public int getColor() {
    return color;
  }

  public void setColor(int color) {
    this.color = color;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getInitials() {
    return initials;
  }

  public void setInitials(String initials) {
    this.initials = initials;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public byte[] getImageByte() {
    return imageByte;
  }

  public void setImageByte(byte[] imageByte) {
    this.imageByte = imageByte;
  }


  @Override
  public String toString() {
    return "Product{ \n" +
            "id=" + id + "'\n" +
            "name='" + name + "'\n" +
            "initials='" + initials + "'\n" +
            "price=" + price + "'\n" +
            "image=" + image + "'\n" +
            "color=" + color + "'\n" +
            "taxes=" + taxes + "'\n" +
            '}';
  }
}
