package com.dinerico.pos.model;

import java.io.Serializable;

/**
 * Created by josephleon on 9/30/14.
 */
public class Store implements Serializable{
  private String codigo;
  private String nombreComercial;
  private Address direccion;
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
