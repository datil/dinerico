package com.dinerico.pos.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.dinerico.pos.R;
import com.dinerico.pos.exception.ValidationError;
import com.dinerico.pos.model.Account;
import com.dinerico.pos.model.Contributor;
import com.dinerico.pos.network.config.ActivityBase;
import com.dinerico.pos.viewmodel.ContributorViewModel;

import rx.android.Events;
import rx.functions.Action1;

public class ContributorActivity extends ActivityBase {
  private ViewHolder view;
  private ContributorViewModel viewModel;

  public final static String CONTRIBUTOR = "contributor";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_contributor);
    Contributor contributor = getContributor(getIntent());
    viewModel = new ContributorViewModel(new Contributor());
    view = new ViewHolder();
    viewModel.setModel(contributor);
    showContributorInfo(contributor);
  }

  public Contributor getContributor(Intent intent){
    return (Contributor)intent.getSerializableExtra(AccountActivity.CONTRIBUTOR);
  }

  private void confirmContributorInfo() {
    try {
      viewModel.getModel().validate();
      Intent intent = new Intent(ContributorActivity.this, StoreActivity.class);
      intent.putExtra(CONTRIBUTOR, viewModel.getModel());
      startActivity(intent);
    } catch (ValidationError e) {
      showErrorValidation(e,this);
    }

  }

  private void showContributorInfo(Contributor contributor) {
    view.businessName.setText(contributor.getRazonSocial());
    view.commercialName.setText(contributor.getNombreComercial());
    view.economicActivity.setText(contributor.getActividadPrincipal());
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.confirm, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    switch (item.getItemId()) {
      case R.id.confirm:
        confirmContributorInfo();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  private class ViewHolder{
    public TextView businessName;
    public EditText commercialName;
    public TextView economicActivity;

    public ViewHolder(){
      findViews();
      subscribeViewsToViewModel();
    }

    private void subscribeViewsToViewModel(){
      Events.text(commercialName).subscribe(new Action1<String>() {
        @Override
        public void call(String string) {
          viewModel.setCommercialName(string);
          Account.getInstance().setCommercialName(string);
        }
      });
    }

    private void findViews() {
      businessName = (TextView) findViewById(R.id.businessName);
      commercialName = (EditText) findViewById(R.id.commercialName);
      economicActivity = (TextView) findViewById(R.id.economicActivity);
    }
  }

}
