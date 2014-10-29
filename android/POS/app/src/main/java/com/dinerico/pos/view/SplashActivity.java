package com.dinerico.pos.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.dinerico.pos.R;
import com.dinerico.pos.network.config.ActivityBase;

/**
 * Created by josephleon on 10/28/14.
 */

public class SplashActivity extends ActivityBase {

  public static final int SPLASH_DISPLAY_LENGTH = 1000;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    getActionBar().hide();
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        Intent mainIntent = new Intent(SplashActivity.this, ShopActivity.class);
        startActivity(mainIntent);
        finish();
      }
    }, SPLASH_DISPLAY_LENGTH);
  }

}
