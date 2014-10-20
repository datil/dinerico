package com.dinerico.pos.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.dinerico.pos.R;
import com.dinerico.pos.db.AccountDB;
import com.dinerico.pos.db.SessionDB;
import com.dinerico.pos.exception.ValidationError;
import com.dinerico.pos.model.Account;
import com.dinerico.pos.network.config.ActivityBase;
import com.dinerico.pos.network.service.LoginService;
import com.dinerico.pos.viewmodel.LoginViewModel;

import rx.android.Events;
import rx.functions.Action1;

public class LoginActivity extends ActivityBase {

  private LoginViewModel viewModel;
  private ViewHolder viewHolder;

  private final static String MESSAJE_TITTLE = "Inicio de sesión";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    setUpActionBar();
    viewModel = new LoginViewModel(new Account(), new LoginService(),
            new SessionDB(this), new AccountDB(this));
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
        confirmLogin();
      }
    });
    getActionBar().setCustomView(actionBar);
  }

  private void confirmLogin() {
    try {
      if(viewModel.login())
        startWelcomeActivity();
      else
        showMessage(getString(R.string.autenticationFailed), MESSAJE_TITTLE);

    } catch (ValidationError e) {
      showErrorValidation(e);
    }
  }

  private void startWelcomeActivity() {
    Intent intent = new Intent(this,WelcomeActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent
            .FLAG_ACTIVITY_CLEAR_TASK);
    startActivity(intent);
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
