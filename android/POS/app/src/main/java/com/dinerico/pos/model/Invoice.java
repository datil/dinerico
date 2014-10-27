package com.dinerico.pos.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by josephleon on 10/10/14.
 */
public class Invoice implements Serializable{

  @DatabaseField (generatedId = true)
  private int id;
  @DatabaseField(foreign = true, foreignAutoRefresh = true)
  private Customer cliente;
  @DatabaseField(foreign = true, foreignAutoRefresh = true)
  private Store store;
  @DatabaseField
  private Date created;
  @DatabaseField(foreign = true, foreignAutoRefresh = true)
  private Order order;
  private ArrayList<InvoiceItem> items;
  @DatabaseField
  private String apikey;
  @DatabaseField
  private String codigoestablecimiento;
  @DatabaseField
  private String codigopuntoventa;
  @DatabaseField
  private String secuencia;
  @DatabaseField
  private String guiaremision;
  @DatabaseField
  private String propina;
  @DatabaseField(dataType = DataType.SERIALIZABLE)
  private ArrayList<String> adicionales;

  public ArrayList<InvoiceItem> getItems() {
    return items;
  }

  public void setItems(ArrayList<InvoiceItem> items) {
    this.items = items;
  }

  public String getApikey() {
    return apikey;
  }

  public void setApikey(String apikey) {
    this.apikey = apikey;
  }

  public String getCodigoestablecimiento() {
    return codigoestablecimiento;
  }

  public void setCodigoestablecimiento(String codigoestablecimiento) {
    this.codigoestablecimiento = codigoestablecimiento;
  }

  public String getCodigopuntoventa() {
    return codigopuntoventa;
  }

  public void setCodigopuntoventa(String codigopuntoventa) {
    this.codigopuntoventa = codigopuntoventa;
  }

  public String getSecuencia() {
    return secuencia;
  }

  public void setSecuencia(String secuencia) {
    this.secuencia = secuencia;
  }

  public String getGuiaremision() {
    return guiaremision;
  }

  public void setGuiaremision(String guiaremision) {
    this.guiaremision = guiaremision;
  }

  public String getPropina() {
    return propina;
  }

  public void setPropina(String propina) {
    this.propina = propina;
  }

  public ArrayList<String> getAdicionales() {
    return adicionales;
  }

  public void setAdicionales(ArrayList<String> adicionales) {
    this.adicionales = adicionales;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Customer getCliente() {
    return cliente;
  }

  public void setCliente(Customer cliente) {
    this.cliente = cliente;
  }

  public Store getStore() {
    return store;
  }

  public void setStore(Store store) {
    this.store = store;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }


  @Override
  public String toString() {
    return "Invoice{ \n" +
            "id=" + id + "'\n" +
            "cliente=" + cliente + "'\n" +
            "store=" + store + "'\n" +
            "created=" + created + "'\n" +
            "order=" + order + "'\n" +
            "items=" + items + "'\n" +
            "apikey='" + apikey + "'\n" +
            "codigoestablecimiento='" + codigoestablecimiento + "'\n" +
            "codigopuntoventa='" + codigopuntoventa + "'\n" +
            "secuencia='" + secuencia + "'\n" +
            "guiaremision='" + guiaremision + "'\n" +
            "propina='" + propina + "'\n" +
            "adicionales=" + adicionales + "'\n" +
            '}';
  }
}
