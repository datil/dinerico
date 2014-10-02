package com.dinerico.pos.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.dinerico.pos.R;
import com.dinerico.pos.exception.ValidationError;
import com.dinerico.pos.model.Account;
import com.dinerico.pos.network.config.ActivityBase;
import com.dinerico.pos.viewmodel.AccountViewModel;

import java.util.HashMap;

import rx.android.Events;
import rx.functions.Action1;


public class AccountActivity extends ActivityBase {

  private AccountViewModel viewModel;
  private ViewHolder viewHolder;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_account);

    viewModel = new AccountViewModel(Account.getInstance(),getSpiceManager());
    viewHolder = new ViewHolder();

//    LayoutInflater inflater = LayoutInflater.from(AccountActivity.this);
//    View customBar = inflater.inflate(R.layout.action_bar,null);
//    TextView tittle = (TextView)customBar.findViewById(R.id.titleText);
//    tittle.setText("Mi negocio propio");
//
//    getActionBar().setDisplayShowHomeEnabled(false);
//    getActionBar().setDisplayShowTitleEnabled(false);
//    getActionBar().setDisplayShowCustomEnabled(true);
//    getActionBar().setCustomView(customBar);

  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.account, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    switch (item.getItemId()) {
      case R.id.account:
        startContributorInfoActivity();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  private void startContributorInfoActivity() {
    try {
      viewModel.getModel().validate();
      Intent intent = new Intent(AccountActivity.this, ContributorActivity.class);
      startActivity(intent);
    } catch (ValidationError e) {
      showExceptionError(e);
    }

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
