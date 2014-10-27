package com.dinerico.pos.model;

import com.dinerico.pos.R;
import com.dinerico.pos.exception.ValidationError;
import com.dinerico.pos.util.Utils;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by josephleon on 9/30/14.
 */
public class Store implements Serializable {
  @DatabaseField(generatedId = true)
  private int id;
  @DatabaseField
  private String codigo;
  @DatabaseField
  private String nombreComercial;
  @DatabaseField
  private String actividadPrincipal;
  @DatabaseField(foreign = true, foreignAutoRefresh = true)
  private Address direccion;
  @DatabaseField
  private String estado;
  @DatabaseField
  private String razonSocial;
  @DatabaseField
  private String RUC;
  @DatabaseField
  private String numeroCelular;
  @DatabaseField
  private String contribuidorEspecial;
  @DatabaseField
  private String tipo;
  @DatabaseField
  private boolean obligadoContabilidad;
  @DatabaseField
  private String claveFacturacionElectronica;
  @ForeignCollectionField
  private ForeignCollection<Product> products;

  public static final String COD_PUNTO_VENTA ="001";

  public boolean isValidInvoiceKey() throws ValidationError {
    if (!Utils.isValidString(claveFacturacionElectronica)) {
      HashMap<String, Integer> errorData = new HashMap<String, Integer>();
      errorData.put("userMessage", R.string.noValidInvoiceKey);
      throw new ValidationError(" null or blank", errorData);
    }
    return true;
  }

  public boolean isValidNombreComercial() throws ValidationError {
    if (!Utils.isValidString(nombreComercial)) {
      HashMap<String, Integer> errorData = new HashMap<String, Integer>();
      errorData.put("userMessage", R.string.noValidCommercialName);
      throw new ValidationError("Nombre comercial null or blank", errorData);
    }
    return true;
  }

  public boolean isValidRUC() throws ValidationError {
    if (!Utils.isValidString(RUC) || RUC.length() < 13) {
      HashMap<String, Integer> errorData = new HashMap<String, Integer>();
      errorData.put("userMessage", R.string.noValidRUC);
      throw new ValidationError("RUC null or blank", errorData);
    }
    return true;
  }

  public boolean isValidMobilePhone() throws ValidationError {
    if (!Utils.isValidString(numeroCelular) || numeroCelular.length() < 10) {
      HashMap<String, Integer> errorData = new HashMap<String, Integer>();
      errorData.put("userMessage", R.string.noValidMobilePhone);
      throw new ValidationError("Mobile phone length lower 10, blank or null",
              errorData);
    }
    return true;
  }

  public ForeignCollection<Product> getProducts() {
    return products;
  }

  public void setProducts(ForeignCollection<Product> products) {
    this.products = products;
  }

  public String getActividadPrincipal() {
    return actividadPrincipal;
  }

  public void setActividadPrincipal(String actividadPrincipal) {
    this.actividadPrincipal = actividadPrincipal;
  }

  public String getClaveFacturacionElectronica() {
    return claveFacturacionElectronica;
  }

  public void setClaveFacturacionElectronica(String claveFacturacionElectronica) {
    this.claveFacturacionElectronica = claveFacturacionElectronica;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

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

  public String getRazonSocial() {
    return razonSocial;
  }

  public void setRazonSocial(String razonSocial) {
    this.razonSocial = razonSocial;
  }

  public String getRUC() {
    return RUC;
  }

  public void setRUC(String RUC) {
    this.RUC = RUC;
  }

  public String getNumeroCelular() {
    return numeroCelular;
  }

  public void setNumeroCelular(String numeroCelular) {
    this.numeroCelular = numeroCelular;
  }

  public String getContribuidorEspecial() {
    return contribuidorEspecial;
  }

  public void setContribuidorEspecial(String contribuidorEspecial) {
    this.contribuidorEspecial = contribuidorEspecial;
  }

  public boolean isObligadoContabilidad() {
    return obligadoContabilidad;
  }

  public void setObligadoContabilidad(boolean obligadoContabilidad) {
    this.obligadoContabilidad = obligadoContabilidad;
  }

  @Override
  public String toString() {
    return "Store{ \n" +
            "id=" + id + "'\n" +
            "codigo='" + codigo + "'\n" +
            "nombreComercial='" + nombreComercial + "'\n" +
            "direccion=" + direccion + "'\n" +
            "estado='" + estado + "'\n" +
            "razonSocial='" + razonSocial + "'\n" +
            "RUC='" + RUC + "'\n" +
            "numeroCelular='" + numeroCelular + "'\n" +
            "contribuidorEspecial='" + contribuidorEspecial + "'\n" +
            "obligadoContabilidad=" + obligadoContabilidad + "'\n" +
            "products=" + products + "'\n" +
            '}';
  }
}
