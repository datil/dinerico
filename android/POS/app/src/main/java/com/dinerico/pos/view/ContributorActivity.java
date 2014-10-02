package com.dinerico.pos.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.dinerico.pos.R;
import com.dinerico.pos.model.Account;
import com.dinerico.pos.model.Address;
import com.dinerico.pos.model.Contributor;
import com.dinerico.pos.model.Store;
import com.dinerico.pos.network.config.ActivityBase;
import com.dinerico.pos.viewmodel.ContributorViewModel;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.ArrayList;

public class ContributorActivity extends ActivityBase {
  private ViewHolder view;
  private ContributorViewModel viewModel;

  private final static String LOG_TAG = ContributorActivity.class
          .getSimpleName();
  public final static String CONTRIBUTOR = "contributor";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_contributor);

    viewModel = new ContributorViewModel(getSpiceManager());
    view = new ViewHolder();

    viewModel.setModel(getContributor());
    Account.getInstance().setSpecialContributor(getContributor().getClase());
    Account.getInstance().setForcedAccounting(getContributor().isObligadoContabilidad());
    showContributorInfo(getContributor());

  }

  private void getContributorInfo() {
    showProgressDialog();
    viewModel.getDetailInfoAccount(Account.getInstance().getRUC(),
            new RequestListener<Contributor>() {
              @Override
              public void onRequestFailure(SpiceException spiceException) {
                dismissProgressDialog();
                showMessage(spiceException.getMessage());
              }

              @Override
              public void onRequestSuccess(Contributor contributor) {
                dismissProgressDialog();
                viewModel.setModel(contributor);
                Account.getInstance().setSpecialContributor(contributor.getClase());
                Account.getInstance().setForcedAccounting(contributor.isObligadoContabilidad());
                showContributorInfo(contributor);
                Log.d(LOG_TAG, contributor.toString());
              }
            });
  }


  public void showMessage(String message) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle(this.getResources().getString(R.string.sign_up_des));
    builder.setMessage(message);
    builder.setCancelable(true);
    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int id) {
        dialog.cancel();
      }
    });

    AlertDialog alert = builder.create();
    alert.show();
  }

  private void confirmContributorInfo() {
    Intent intent = new Intent(ContributorActivity.this, StoreActivity.class);
    intent.putExtra(CONTRIBUTOR,viewModel.getModel());
    startActivity(intent);
  }

  private void showContributorInfo(Contributor contributor) {
    view.businessName.setText(contributor.getRazonSocial());
    view.commercialName.setText(contributor.getNombreComercial());
    view.economicActivity.setText(contributor.getActividadPrincipal());
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.contributor, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    switch (item.getItemId()) {
      case R.id.actionContinue:
        confirmContributorInfo();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  private Contributor getContributor() {
    Contributor contributorFake = new Contributor();
    contributorFake.setNombreComercial("DATILMEDIA S.A.");
    contributorFake.setObligadoContabilidad(true);
    contributorFake.setRazonSocial("DATILMEDIA S.A.");
    contributorFake.setClase("Especial");
    contributorFake.setActividadPrincipal("Desarrollo de software social");
    Store store1 = new Store();
    Address addressFake = new Address();
    addressFake.setCalle("Victor Emilio Estrada Solar 112 Y Av.Circunvalación Norte");
    store1.setDireccion(addressFake);
    store1.setNombreComercial("SuperDatile's");
    store1.setCodigo("001");
    Store store2 = new Store();
    store2.setDireccion(addressFake);
    store2.setNombreComercial("HyperDatil's");
    store2.setCodigo("002");
    Store store3 = new Store();
    store3.setDireccion(addressFake);
    store3.setNombreComercial("MegaDatil's");
    store3.setCodigo("003");
    ArrayList<Store> listStoreFake = new ArrayList<Store>();
    listStoreFake.add(store1);
    listStoreFake.add(store2);
    listStoreFake.add(store3);
    contributorFake.setEstablecimientos(listStoreFake);
    return contributorFake;
  }

  private class ViewHolder {
    public TextView businessName;
    public TextView commercialName;
    public TextView economicActivity;

    public ViewHolder() {
      findViews();
    }

    private void findViews() {
      businessName = (TextView) findViewById(R.id.businessName);
      commercialName = (TextView) findViewById(R.id.commercialName);
      economicActivity = (TextView) findViewById(R.id.economicActivity);
    }
  }

}
