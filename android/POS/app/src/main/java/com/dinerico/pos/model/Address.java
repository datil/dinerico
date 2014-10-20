package com.dinerico.pos.model;

import com.dinerico.pos.R;
import com.dinerico.pos.exception.ValidationError;
import com.dinerico.pos.util.Utils;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by josephleon on 9/30/14.
 */
public class Address implements Serializable{

  @DatabaseField(generatedId = true)
  private int id;
  @DatabaseField
  private String calle;
  @DatabaseField
  private String canton;
  @DatabaseField
  private String provincia;

  public boolean isValidCalle() throws ValidationError {
    if (!Utils.isValidString(calle)) {
      HashMap<String, Integer> errorData = new HashMap<String, Integer>();
      errorData.put("userMessage", R.string.noValidAddress);
      throw new ValidationError("Calle null or blank", errorData);
    }
    return true;
  }

  public String getCalle() {
    return calle;
  }

  public void setCalle(String calle) {
    this.calle = calle;
  }

  public String getCanton() {
    return canton;
  }

  public void setCanton(String canton) {
    this.canton = canton;
  }

  public String getProvincia() {
    return provincia;
  }

  public void setProvincia(String provincia) {
    this.provincia = provincia;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  @Override
  public String toString() {
    return "Address{ \n" +
            "id=" + id + "'\n" +
            "calle='" + calle + "'\n" +
            "canton='" + canton + "'\n" +
            "provincia='" + provincia + "'\n" +
            '}';
  }
}
