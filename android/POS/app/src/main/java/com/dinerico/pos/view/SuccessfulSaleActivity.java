package com.dinerico.pos.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dinerico.pos.R;

public class SuccessfulSaleActivity extends Activity {

  ViewHolder view;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_successful_sale);
    view = new ViewHolder();
  }

  private void startSendReceiptActivity(){
    Intent intent = new Intent(this, ReceiptActivity.class);
    startActivity(intent);
  }

  private void startNoThanksActivity(){
    Intent intent = new Intent(this, ThanksActivity.class);
    startActivity(intent);
  }

  private class ViewHolder implements View.OnClickListener{
    public Button sendReceipt;
    public Button noThanks;

    public ViewHolder() {
      findViews();
    }

    private void findViews() {
      sendReceipt = (Button) findViewById(R.id.sendReceipt);
      noThanks = (Button) findViewById(R.id.noThanks);
      sendReceipt.setOnClickListener(this);
      noThanks.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      switch (view.getId()){
        case R.id.sendReceipt:
          startSendReceiptActivity();
          break;
        case R.id.noThanks:
          startNoThanksActivity();
          break;
      }
    }
  }

}
