package com.dinerico.pos.model;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by josephleon on 9/30/14.
 */
public class Store implements Serializable{
  @DatabaseField(generatedId = true)
  private int id;
  @DatabaseField
  private String codigo;
  @DatabaseField
  private String nombreComercial;
  @DatabaseField
  private Address direccion;
  @DatabaseField
  private String estado;

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getNombreComercial() {
    return nombreComercial;
  }

  public void setNombreComercial(String nombreComercial) {
    this.nombreComercial = nombreComercial;
  }

  public Address getDireccion() {
    return direccion;
  }

  public void setDireccion(Address direccion) {
    this.direccion = direccion;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }
}
