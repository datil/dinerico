package com.dinerico.pos.model;

import java.io.Serializable;

/**
 * Created by josephleon on 9/30/14.
 */
public class Address implements Serializable{

  private String calle;
  private String canton;
  private String provincia;

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
}
