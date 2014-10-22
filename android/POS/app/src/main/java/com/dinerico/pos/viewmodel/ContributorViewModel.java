package com.dinerico.pos.viewmodel;

import com.dinerico.pos.exception.ValidationError;
import com.dinerico.pos.model.Contributor;
import com.dinerico.pos.model.Store;

/**
 * Created by josephleon on 10/2/14.
 */
public class ContributorViewModel {

  private String commercialName;
  private Contributor contributor;
  private Store store;

  public ContributorViewModel(Store model) {
    this.store = model;
  }

  public String getCommercialName() {
    return commercialName;
  }

  public void setCommercialName(String commercialName) {
    this.commercialName = commercialName;
    store.setNombreComercial(commercialName);
  }

  public boolean validate () throws ValidationError {
    return store.isValidNombreComercial();
  }

  public Store getStore() {
    return store;
  }

  public void setStoreAndContributor(Contributor contributor) {
    this.contributor = contributor;
    this.commercialName = contributor.getNombreComercial();
    store.setActividadPrincipal(contributor.getActividadPrincipal());
    store.setNombreComercial(contributor.getNombreComercial());
    store.setRazonSocial(contributor.getRazonSocial());
    store.setObligadoContabilidad(contributor.isObligadoContabilidad());
    store.setEstado(contributor.getEstado());
    store.setContribuidorEspecial(contributor.getClase());
    store.setTipo(contributor.getTipo());
  }

  public Contributor getContributor() {
    return contributor;
  }

}
