package com.dinerico.pos.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dinerico.pos.R;
import com.dinerico.pos.model.Order;
import com.dinerico.pos.network.config.ActivityBase;

public class ReceiptSentActivity extends ActivityBase {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_receipt_sent);
    setUpActionBar();
  }

  private void setUpActionBar(){
    hideActionBarComponents();
    View actionBar = getLayoutInflater().inflate(R.layout.action_bar_simple_button,
            null);
    View actionContainer = actionBar.findViewById(R.id.actionContainer);
    actionContainer.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startNewSale();
      }
    });
    TextView action = (TextView) actionBar.findViewById(R.id.action);
    action.setText(getString(R.string.newSale));
    ImageView actionImg = (ImageView) actionBar.findViewById(R.id.actionImg);
    actionImg.setImageResource(R.drawable.arrow_right_white);
    getActionBar().setCustomView(actionBar);
  }

  private void startNewSale(){
    Intent intent = new Intent(this,ShopActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent
            .FLAG_ACTIVITY_CLEAR_TASK);
    startActivity(intent);
    Order.reset();
    finish();
  }



}
