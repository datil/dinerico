package com.dinerico.pos.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.dinerico.pos.R;
import com.dinerico.pos.exception.ValidationError;
import com.dinerico.pos.model.Account;
import com.dinerico.pos.model.Contributor;
import com.dinerico.pos.network.config.ActivityBase;
import com.dinerico.pos.viewmodel.SignUpViewModel;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

import java.util.HashMap;

import rx.android.Events;
import rx.functions.Action1;


public class SignUpActivity extends ActivityBase {

  private SignUpViewModel viewModel;
  private ViewHolder viewHolder;

  private static final String APPLICATION_ID =
          "7KyKS1qISRLaIkZhdsf8zwSlhKNVWE9itWJoDsLu";
  private static final String CLIENT_KEY =
          "vnBFzyguL6HVBdLanSHl7HGowXNiFTkoYQxaUoET";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_up);

    initializeParse();
    viewModel = new SignUpViewModel(new Account(),getSpiceManager(),getIntent());
    viewHolder = new ViewHolder();

  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.sign_up, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    switch (item.getItemId()) {
      case R.id.sign_up:
        signIn();
        return true;
      case android.R.id.home:
        finish();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  private void signIn() {
    try {
      showProgressDialog();
      viewModel.getDetailInfoAccount(new RequestListener<Contributor>() {
        @Override
        public void onRequestFailure(SpiceException spiceException) {
          dismissProgressDialog();
          showMessage(spiceException.getMessage());
        }

        @Override
        public void onRequestSuccess(Contributor contributor) {
//          viewModel.createAccount(contributor);
          dismissProgressDialog();
          showMessage("Request exitoso");
          Log.d(SignUpActivity.class.getSimpleName(),contributor.toString());
        }
      });
    } catch (ValidationError e) {
      Log.d(SignUpActivity.class.getSimpleName(),e.getMessage());
      showExceptionError(e);
    }
  }

  private void initializeParse(){

    // Add your initialization code here
    Parse.initialize(this, APPLICATION_ID, CLIENT_KEY);

    ParseUser.enableAutomaticUser();
    ParseACL defaultACL = new ParseACL();

    // If you would like all objects to be private by default, remove this line.
    defaultACL.setPublicReadAccess(true);

    ParseACL.setDefaultACL(defaultACL, true);
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

  private void showExceptionError(ValidationError e) {
    Log.e(SignUpActivity.class.getSimpleName(), e.getMessage());
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
