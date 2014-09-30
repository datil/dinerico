package com.dinerico.pos.model;

import java.util.ArrayList;

/**
 * Created by josephleon on 9/30/14.
 */
public class Contributor {

  private String actividadPrincipal;
  private boolean obligadoContabilidad;
  private String tipo;
  private String clase;
  private String estado;
  private String nombreComercial;
  private String razonSocial;
  private ArrayList<Store> establecimientos;

  public String getActividadPrincipal() {
    return actividadPrincipal;
  }

  public void setActividadPrincipal(String actividadPrincipal) {
    this.actividadPrincipal = actividadPrincipal;
  }

  public boolean isObligadoContabilidad() {
    return obligadoContabilidad;
  }

  public void setObligadoContabilidad(boolean obligadoContabilidad) {
    this.obligadoContabilidad = obligadoContabilidad;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public String getClase() {
    return clase;
  }

  public void setClase(String clase) {
    this.clase = clase;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public String getNombreComercial() {
    return nombreComercial;
  }

  public void setNombreComercial(String nombreComercial) {
    this.nombreComercial = nombreComercial;
  }

  public String getRazonSocial() {
    return razonSocial;
  }

  public void setRazonSocial(String razonSocial) {
    this.razonSocial = razonSocial;
  }

  public ArrayList<Store> getEstablecimientos() {
    return establecimientos;
  }

  public void setEstablecimientos(ArrayList<Store> establecimientos) {
    this.establecimientos = establecimientos;
  }


  @Override
  public String toString() {
    return "Contributor{ \n" +
            "actividadPrincipal='" + actividadPrincipal + "'\n" +
            "obligadoContabilidad=" + obligadoContabilidad +
            "tipo='" + tipo + "'\n" +
            "clase='" + clase + "'\n" +
            "estado='" + estado + "'\n" +
            "nombreComercial='" + nombreComercial + "'\n" +
            "razonSocial='" + razonSocial + "'\n" +
            "establecimientos=" + establecimientos +
            '}';
  }
}
