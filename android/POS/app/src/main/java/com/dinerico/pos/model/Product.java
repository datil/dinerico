package com.dinerico.pos.model;

import android.graphics.Bitmap;

import com.dinerico.pos.R;
import com.dinerico.pos.exception.ValidationError;
import com.dinerico.pos.util.Utils;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by josephleon on 10/3/14.
 */
public class Product implements Serializable{

  @DatabaseField(generatedId = true)
  private int id;
  @DatabaseField
  private String name;
  @DatabaseField
  private String initials;
  @DatabaseField
  private float price;
  @DatabaseField(dataType = DataType.BYTE_ARRAY)
  private byte[] imageByte;
  private Bitmap image;
  @DatabaseField
  private int color;
  @DatabaseField(foreign = true, foreignAutoRefresh = true)
  private Store store;

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
    if (!Utils.isValidFloat(price)) {
      HashMap<String, Integer> errorData = new HashMap<String, Integer>();
      errorData.put("userMessage", R.string.noValidPrice);
      throw new ValidationError("Product price null or blank", errorData);
    }
    return true;
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

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
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
            "id=" + id +  "'\n" +
            "name='" + name + "'\n" +
            "initials='" + initials + "'\n" +
            "price=" + price + "'\n" +
            "image=" + image + "'\n" +
            "color=" + color + "'\n" +
            '}';
  }
}
