package com.dinerico.pos.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.dinerico.pos.R;
import com.dinerico.pos.exception.ValidationError;
import com.dinerico.pos.model.Account;
import com.dinerico.pos.model.Contributor;
import com.dinerico.pos.network.config.ActivityBase;
import com.dinerico.pos.network.service.ContributorService;
import com.dinerico.pos.viewmodel.AccountViewModel;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.HashMap;

import rx.android.Events;
import rx.functions.Action1;


public class AccountActivity extends ActivityBase {

  private AccountViewModel viewModel;
  private ViewHolder viewHolder;

  private final static String LOG_TAG = AccountActivity.class
          .getSimpleName();
  public final static String CONTRIBUTOR = "contributor";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_account);
    setUpActionBar();
    viewModel = new AccountViewModel(Account.getInstance(),
            new ContributorService(getSpiceManager()));
    viewHolder = new ViewHolder();
  }

  private void setUpActionBar(){
    hideActionBarComponents();
    View actionBar = getLayoutInflater().inflate(R.layout.action_bar_sign_up,
            null);
    View actionContainer = actionBar.findViewById(R.id.actionContainer);
    actionContainer.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startContributorInfoActivity();
      }
    });
    getActionBar().setCustomView(actionBar);
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
                Account.getInstance().setSpecialContributor(contributor
                        .getClase());
                Account.getInstance().setForcedAccounting(contributor
                        .isObligadoContabilidad());
                startContributorActivity(contributor);
                Log.d(LOG_TAG, contributor.toString());
              }
            });
  }

  private void startContributorActivity(Contributor contributor) {
    Intent intent = new Intent(this, ContributorActivity.class);
    intent.putExtra(CONTRIBUTOR, contributor);
    startActivity(intent);
  }


  private void startContributorInfoActivity() {
    try {
      viewModel.getModel().validate();
      getContributorInfo();
    } catch (ValidationError e) {
      showExceptionError(e);
    }

  }

  public void showMessage(String message) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle(this.getResources().getString(R.string.signUpDes));
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

  private void showExceptionError(ValidationError e) {
    Log.e(AccountActivity.class.getSimpleName(), e.getMessage());
    HashMap<String, Integer> errorData = e.getMapMessage();
    showMessage(getResources().getString(errorData.get("userMessage")));
  }

  private class ViewHolder {
    public EditText ruc;
    public EditText mobilePhone;
    public EditText email;
    public EditText password;

    public ViewHolder() {
      findViews();
      subscribeToViewComponents();
    }

    private void findViews() {
      ruc = (EditText) findViewById(R.id.ruc);
      mobilePhone = (EditText) findViewById(R.id.mobilePhone);
      email = (EditText) findViewById(R.id.email);
      password = (EditText) findViewById(R.id.password);
    }

    private void subscribeToViewComponents() {

      Events.text(ruc).subscribe(new Action1<String>() {
        @Override
        public void call(String string) {
          viewModel.setRuc(string);
        }
      });

      Events.text(mobilePhone).subscribe(new Action1<String>() {
        @Override
        public void call(String string) {
          viewModel.setMobilePhone(string);
        }
      });

      Events.text(email).subscribe(new Action1<String>() {
        @Override
        public void call(String string) {
          viewModel.setEmail(string);
        }
      });

      Events.text(password).subscribe(new Action1<String>() {
        @Override
        public void call(String string) {
          viewModel.setPassword(string);
        }
      });

    }

  }

}
