package com.dinerico.pos.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dinerico.pos.R;
import com.dinerico.pos.model.Order;
import com.dinerico.pos.network.config.ActivityBase;
import com.dinerico.pos.util.Utils;

public class PaymentTypeActivity extends ActivityBase {

  ViewHolder view;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_payment_type);
    view = new ViewHolder();
    setUpActionBar();
  }

  private void setUpActionBar(){
    hideActionBarComponents();
    View actionBar = getLayoutInflater().inflate(R.layout.action_bar_single_tittle,
            null);
    TextView totalAmount = (TextView)actionBar.findViewById(R.id.tittle);
    String total = Utils.currencyFormatter(Order.getInstance().getTotal());
    totalAmount.setText(total);
    getActionBar().setCustomView(actionBar);
  }

  private void chargeWithElectronicMoney(){
    Intent intent = new Intent(this, EMPaymentActivity.class);
    startActivity(intent);
  }

  private void chargeWithCash(){
//    Intent intent = new Intent(this, RecieveMoneyActivity.class);
//    startActivity(intent);
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
