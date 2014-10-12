package com.dinerico.pos.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dinerico.pos.R;
import com.dinerico.pos.model.Customer;
import com.dinerico.pos.network.config.ElectronicMoneyActivity;
import com.dinerico.pos.network.service.EMService;
import com.dinerico.pos.viewmodel.CustomerViewModel;

import rx.android.Events;
import rx.functions.Action1;

public class CustomerDataEMActivity extends ElectronicMoneyActivity {

  private CustomerViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_customer_data_em);
    viewModel = new CustomerViewModel(new Customer(),
            new EMService(getSpiceManager()));
    ViewHolder view = new ViewHolder();
  }

  private void charge(){
    Intent intent = new Intent(this, SuccessfulSaleActivity.class);
    startActivity(intent);
  }

  private class ViewHolder implements View.OnClickListener{
    public EditText mobilePhone;
    public EditText customerId;
    public Button charge;

    public ViewHolder() {
      findViews();
      subscribeToViewComponents();
    }

    private void findViews() {
      mobilePhone = (EditText) findViewById(R.id.mobilePhone);
      customerId = (EditText) findViewById(R.id.customerId);
      charge = (Button) findViewById(R.id.charge);
      charge.setOnClickListener(this);
    }

    private void subscribeToViewComponents() {

      Events.text(mobilePhone).subscribe(new Action1<String>() {
        @Override
        public void call(String string) {
          viewModel.setMobilePhone(string);
        }
      });

      Events.text(customerId).subscribe(new Action1<String>() {
        @Override
        public void call(String string) {
          viewModel.setCustomerId(string);
        }
      });
    }


    @Override
    public void onClick(View view) {
      switch (view.getId()){
        case R.id.charge:
          charge();
          break;
      }
    }
  }

}
