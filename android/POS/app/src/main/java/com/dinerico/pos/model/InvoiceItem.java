package com.dinerico.pos.model;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by josephleon on 10/25/14.
 */
public class InvoiceItem implements Serializable{
  @DatabaseField(generatedId = true)
  private int id;
  @DatabaseField
  private int cantidad;
  @DatabaseField
  private String codigoprincipal;
  @DatabaseField
  private String codigoauxiliar;
  @DatabaseField
  private String nombre;
  @DatabaseField
  private String precio;
  @DatabaseField
  private String descuento;
  @DatabaseField
  private String codigoiva;
  @DatabaseField
  private String codigoice;

  public InvoiceItem(){

  }

  public InvoiceItem(int cantidad, String codigoprincipal,
                     String codigoauxiliar, String nombre, String precio,
                     String descuento, String codigoiva, String codigoice) {
    this.cantidad = cantidad;
    this.codigoprincipal = codigoprincipal;
    this.codigoauxiliar = codigoauxiliar;
    this.nombre = nombre;
    this.precio = precio;
    this.descuento = descuento;
    this.codigoiva = codigoiva;
    this.codigoice = codigoice;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getCantidad() {
    return cantidad;
  }

  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }

  public String getCodigoprincipal() {
    return codigoprincipal;
  }

  public void setCodigoprincipal(String codigoprincipal) {
    this.codigoprincipal = codigoprincipal;
  }

  public String getCodigoauxiliar() {
    return codigoauxiliar;
  }

  public void setCodigoauxiliar(String codigoauxiliar) {
    this.codigoauxiliar = codigoauxiliar;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getPrecio() {
    return precio;
  }

  public void setPrecio(String precio) {
    this.precio = precio;
  }

  public String getDescuento() {
    return descuento;
  }

  public void setDescuento(String descuento) {
    this.descuento = descuento;
  }

  public String getCodigoiva() {
    return codigoiva;
  }

  public void setCodigoiva(String codigoiva) {
    this.codigoiva = codigoiva;
  }

  public String getCodigoice() {
    return codigoice;
  }

  public void setCodigoice(String codigoice) {
    this.codigoice = codigoice;
  }
}
