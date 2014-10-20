package com.dinerico.pos.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dinerico.pos.R;
import com.dinerico.pos.exception.ValidationError;
import com.dinerico.pos.model.Account;
import com.dinerico.pos.model.Contributor;
import com.dinerico.pos.network.config.ActivityBase;
import com.dinerico.pos.util.Utils;
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
    setUpActionBar();
    Contributor contributor = getContributor(getIntent());
    viewModel = new ContributorViewModel(Account.getInstance().getStore());
    view = new ViewHolder();
    viewModel.setStoreAndContributor(contributor);
    showContributorInfo(contributor);
  }

  private void setUpActionBar(){
    hideActionBarComponents();
    View actionBar = getLayoutInflater().inflate(R.layout.action_bar_sign_up,
            null);
    View actionContainer = actionBar.findViewById(R.id.actionContainer);
    actionContainer.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        confirmContributorInfo();
      }
    });
    TextView action = (TextView)actionBar.findViewById(R.id.action);
    action.setText(R.string.confirm);

    ImageView actionImg = (ImageView)actionBar.findViewById(R.id.actionImg);
    actionImg.setImageResource(R.drawable.confirm);

    getActionBar().setCustomView(actionBar);
  }

  public Contributor getContributor(Intent intent) {
    return (Contributor) intent.getSerializableExtra(AccountActivity
            .CONTRIBUTOR);
  }

  private void confirmContributorInfo() {
    try {
      viewModel.getStore().validate();
      Intent intent = new Intent(ContributorActivity.this, StoreActivity.class);
      intent.putExtra(CONTRIBUTOR, viewModel.getContributor());
      startActivity(intent);
    } catch (ValidationError e) {
      showErrorValidation(e);
    }

  }

  private void showContributorInfo(Contributor contributor) {
    view.businessName.setText(contributor.getRazonSocial());
    if (Utils.isValidString(contributor.getNombreComercial()))
      view.commercialName.setText(contributor.getNombreComercial());
    else
      view.commercialName.setText(contributor.getRazonSocial());
    view.economicActivity.setText(contributor.getActividadPrincipal());
  }

  private class ViewHolder {
    public TextView businessName;
    public EditText commercialName;
    public TextView economicActivity;

    public ViewHolder() {
      findViews();
      subscribeViewsToViewModel();
    }

    private void subscribeViewsToViewModel() {
      Events.text(commercialName).subscribe(new Action1<String>() {
        @Override
        public void call(String string) {
          viewModel.setCommercialName(string);
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
