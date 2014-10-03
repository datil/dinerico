package com.dinerico.pos.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.dinerico.pos.R;
import com.dinerico.pos.db.SessionDB;
import com.dinerico.pos.model.Session;
import com.dinerico.pos.network.config.ActivityBase;
import com.dinerico.pos.viewmodel.HomeViewModel;

public class HomeActivity extends ActivityBase implements View.OnClickListener{

  private HomeViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    setCustomBar();
    viewModel = new HomeViewModel(new SessionDB(this));
    if(hasSession()){
      startActivity(new Intent(this,WelcomeActivity.class));
      finish();
    }
  }

  private boolean hasSession(){
    Session session = viewModel.getCreatedSession();
    if(session==null)
      return false;
    else
      return true;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.home, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    switch (item.getItemId()) {
      case R.id.action_settings:
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()){
      case R.id.login:
        startLoginActivity();
        break;
      case R.id.signUp:
        startSignUpActivity();
    }
  }

  private void setCustomBar(){
    LayoutInflater inflater = LayoutInflater.from(this);
    View customBar = inflater.inflate(R.layout.action_bar_home,null);
    View login = customBar.findViewById(R.id.login);
    View signUp = customBar.findViewById(R.id.signUp);
    login.setOnClickListener(this);
    signUp.setOnClickListener(this);
    hideActionBar();
    getActionBar().setCustomView(customBar);
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
