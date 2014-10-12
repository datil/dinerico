package com.dinerico.pos.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

import com.dinerico.pos.R;
import com.dinerico.pos.network.config.ActivityBase;


public class WelcomeActivity extends ActivityBase {

  private final int SPLASH_DISPLAY_LENGTH = 2000;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_welcome);

    new Handler().postDelayed(new Runnable(){
      @Override
      public void run() {
        Intent mainIntent = new Intent(WelcomeActivity.this,
                ShopActivity.class);
        startActivity(mainIntent);
        finish();
      }
    }, SPLASH_DISPLAY_LENGTH);
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
//    getMenuInflater().inflate(R.menu.welcome, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.welcome:
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }
}
