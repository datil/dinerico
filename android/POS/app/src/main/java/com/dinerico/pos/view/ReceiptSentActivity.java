package com.dinerico.pos.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.dinerico.pos.R;
import com.dinerico.pos.viewmodel.ShopViewModel;

public class ReceiptSentActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_receipt_sent);
  }

  private void startNewSale(){
    Intent intent = new Intent(this,ShopActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent
            .FLAG_ACTIVITY_CLEAR_TASK);
    startActivity(intent);
    ShopViewModel.reset();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.new_sale, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    switch (item.getItemId()) {
      case R.id.newSale:
        startNewSale();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }
}
