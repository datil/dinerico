package com.dinerico.pos.model;

import com.dinerico.pos.R;
import com.dinerico.pos.exception.ValidationError;
import com.dinerico.pos.util.Utils;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by josephleon on 10/10/14.
 */
public class Customer implements Serializable {

  public static final String CEDULA = "CEDULA";
  public static final String RUC = "RUC";
  public static final String CONSUMIDOR_FINAL = "CONSUMIDORFINAL";

  @DatabaseField(generatedId = true)
  private int id;
  @DatabaseField
  private String tipoidentificacion;
  @DatabaseField
  private String identificacion;
  @DatabaseField
  private String razonsocial;
  @DatabaseField
  private String correo;
  @DatabaseField
  private String direccion;
  @DatabaseField
  private String telefono;
  @DatabaseField
  private String celular;
  @DatabaseField(foreign = true, foreignAutoRefresh = true)
  private Address localizacion;
  

  public Customer() {
  }

  public boolean isValidIdentificacion() throws ValidationError {
    if (!Utils.isValidString(identificacion) || identificacion.length() < 10) {
      HashMap<String, Integer> errorData = new HashMap<String, Integer>();
      errorData.put("userMessage", R.string.noValidCustomerIdRUC);
      throw new ValidationError("CustomerId null, blank or incorrect size",
              errorData);
    }
    return true;
  }

  public boolean isValidMobilePhone() throws ValidationError {
    if (!Utils.isValidString(celular) || celular.length() < 10) {
      HashMap<String, Integer> errorData = new HashMap<String, Integer>();
      errorData.put("userMessage", R.string.noValidMobilePhone);
      throw new ValidationError("Mobile phone length lower 10, blank or null",
              errorData);
    }
    return true;
  }

  public boolean isValidName() throws ValidationError {
    if (!Utils.isValidString(razonsocial)) {
      HashMap<String, Integer> errorData = new HashMap<String, Integer>();
      errorData.put("userMessage", R.string.noValidCustomerName);
      throw new ValidationError("Customer razonsocial null or blank",
              errorData);
    }
    return true;
  }

  public boolean isValidEmail() throws ValidationError {
    if (!Utils.isValidString(correo) || !Utils.isValidEmail(correo)) {
      HashMap<String, Integer> errorData = new HashMap<String, Integer>();
      errorData.put("userMessage", R.string.noValidEmail);
      throw new ValidationError("Email null or blank", errorData);
    }
    return true;
  }

  public boolean isValidTelephone() throws ValidationError {
    if (!Utils.isValidString(telefono)) {
      HashMap<String, Integer> errorData = new HashMap<String, Integer>();
      errorData.put("userMessage", R.string.noValidTelephone);
      throw new ValidationError("Telephone null or blank", errorData);
    }
    return true;
  }

  public boolean isValidAddress() throws ValidationError {
    return localizacion.isValidCalle();
  }

  public String getRazonsocial() {
    return razonsocial;
  }

  public void setRazonsocial(String razonsocial) {
    this.razonsocial = razonsocial;
  }

  public Address getLocalizacion() {
    return localizacion;
  }

  public void setLocalizacion(Address localizacion) {
    this.localizacion = localizacion;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTipoidentificacion() {
    return tipoidentificacion;
  }

  public void setTipoidentificacion(String tipoidentificacion) {
    this.tipoidentificacion = tipoidentificacion;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public String getIdentificacion() {
    return identificacion;
  }

  public void setIdentificacion(String identificacion) {
    if (identificacion.length() > 10)
      tipoidentificacion = RUC;
    else
      tipoidentificacion = CEDULA;
    this.identificacion = identificacion;
  }

  public String getCelular() {
    return celular;
  }

  public void setCelular(String celular) {
    this.celular = celular;
  }


  @Override
  public String toString() {
    return "Customer{ \n" +
            "id=" + id + "'\n" +
            "identificacion='" + identificacion + "'\n" +
            "sendReceipt='" + celular + "'\n" +
            '}';
  }
}
