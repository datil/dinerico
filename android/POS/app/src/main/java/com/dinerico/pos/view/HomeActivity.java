package com.dinerico.pos.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dinerico.pos.R;
import com.dinerico.pos.db.AccountDB;
import com.dinerico.pos.db.SessionDB;
import com.dinerico.pos.model.Session;
import com.dinerico.pos.network.config.ActivityBase;
import com.dinerico.pos.viewmodel.HomeViewModel;

public class HomeActivity extends ActivityBase implements View.OnClickListener{

  private HomeViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getActionBar().hide();
    setContentView(R.layout.activity_home);
    findViews();
    viewModel = new HomeViewModel(new SessionDB(this),new AccountDB(this));
    if(hasSession()){
      startActivity(new Intent(this, SplashActivity.class));
      finish();
    }
  }

  private boolean hasSession(){
    Session session = viewModel.getCurrentSession();
    if(session==null)
      return false;
    else
      return true;
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()){
      case R.id.login:
        startLoginActivity();
        break;
      case R.id.signUp:
        startSignUpActivity();
        break;
    }
  }

  private void findViews(){
    Button login = (Button)findViewById(R.id.login);
    Button signUp = (Button)findViewById(R.id.signUp);
    login.setOnClickListener(this);
    signUp.setOnClickListener(this);
  }
  private void startLoginActivity(){
    Intent intent = new Intent(this,LoginActivity.class);
    startActivity(intent);
  }

  private void startSignUpActivity(){
    Intent intent = new Intent(this,AccountActivity.class);
    startActivity(intent);
  }

}
