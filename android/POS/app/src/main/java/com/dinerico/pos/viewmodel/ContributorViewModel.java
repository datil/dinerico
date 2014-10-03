package com.dinerico.pos.viewmodel;

import com.dinerico.pos.model.Contributor;

/**
 * Created by josephleon on 10/2/14.
 */
public class ContributorViewModel {

  private String commercialName;
  private Contributor model;

  public ContributorViewModel(Contributor model) {
    this.model = model;
  }

  public String getCommercialName() {
    return commercialName;
  }

  public void setCommercialName(String commercialName) {
    this.commercialName = commercialName;
    model.setNombreComercial(commercialName);
  }

  public Contributor getModel() {
    return model;
  }

  public void setModel(Contributor model) {
    this.commercialName = model.getNombreComercial();
    this.model = model;
  }
}
