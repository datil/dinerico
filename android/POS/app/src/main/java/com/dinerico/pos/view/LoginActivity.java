package com.dinerico.pos.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.dinerico.pos.R;
import com.dinerico.pos.db.SessionDB;
import com.dinerico.pos.exception.ValidationError;
import com.dinerico.pos.model.Login;
import com.dinerico.pos.network.config.ActivityBase;
import com.dinerico.pos.network.service.LoginService;
import com.dinerico.pos.viewmodel.LoginViewModel;

import rx.android.Events;
import rx.functions.Action1;

public class LoginActivity extends ActivityBase {

  private LoginViewModel viewModel;
  private ViewHolder viewHolder;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    viewModel = new LoginViewModel(new Login(), new LoginService(),
            new SessionDB(this));
    viewHolder = new ViewHolder();
  }

  private void confirmLogin() {
    try {
      viewModel.login();
      startWelcomeActivity();
    } catch (ValidationError e) {
      showErrorValidation(e, this);
    }
  }

  private void startWelcomeActivity() {
    Intent intent = new Intent(this,WelcomeActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent
            .FLAG_ACTIVITY_CLEAR_TASK);
    startActivity(intent);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.login, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.actionlogin:
        confirmLogin();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  private class ViewHolder {
    public EditText email;
    public EditText password;

    public ViewHolder() {
      findViews();
      subscribeToViewComponents();
    }

    private void findViews() {
      email = (EditText) findViewById(R.id.email);
      password = (EditText) findViewById(R.id.password);
    }

    private void subscribeToViewComponents() {

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
