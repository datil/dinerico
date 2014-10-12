package com.dinerico.pos.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dinerico.pos.R;
import com.dinerico.pos.network.config.ActivityBase;

public class PaymentTypeActivity extends ActivityBase {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_payment_type);
    ViewHolder view = new ViewHolder();
  }

  private void chargeWithElectronicMoney(){
    Intent intent = new Intent(this, CustomerDataEMActivity.class);
    startActivity(intent);
  }

  private void chargeWithCash(){
    Intent intent = new Intent(this, RecieveMoneyActivity.class);
    startActivity(intent);
  }

  private class ViewHolder implements View.OnClickListener {
    public TextView electronicMoney;
    public TextView cash;

    public ViewHolder() {
      findViews();
    }

    private void findViews() {
      electronicMoney = (TextView) findViewById(R.id.electronicMoney);
      cash = (TextView) findViewById(R.id.cash);

      electronicMoney.setOnClickListener(this);
      cash.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
      switch (view.getId()) {
        case R.id.electronicMoney:
          chargeWithElectronicMoney();
          break;
        case R.id.cash:
          chargeWithCash();
          break;
      }
    }
  }

}
