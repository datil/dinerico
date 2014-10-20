package com.dinerico.pos.view;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dinerico.pos.R;
import com.dinerico.pos.exception.ValidationError;
import com.dinerico.pos.model.Customer;
import com.dinerico.pos.model.Order;
import com.dinerico.pos.network.config.ElectronicMoneyActivity;
import com.dinerico.pos.network.service.EMService;
import com.dinerico.pos.util.Utils;
import com.dinerico.pos.viewmodel.CustomerViewModel;

import java.util.HashMap;

import rx.android.Events;
import rx.functions.Action1;

public class CustomerDataEMActivity extends ElectronicMoneyActivity {

  public CustomerViewModel viewModel;

  private final static String LOG_TAG = CustomerDataEMActivity.class
          .getSimpleName();
  private final static String MESSAJE_TITTLE = "Datos del cliente";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_customer_data_em);
    setUpActionBar();
    viewModel = new CustomerViewModel(new Customer(),
            new EMService(getSpiceManager()));
    ViewHolder view = new ViewHolder();
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

  private void charge(){
    try {
      viewModel.validate();
      DialogFragment newFragment = new CustomerPINFragment();
      newFragment.show(getSupportFragmentManager(), "pickerFragment");
    } catch (ValidationError e) {
      showExceptionError(e);
    }

  }

  private void showExceptionError(ValidationError e) {
    Log.e(LOG_TAG, e.getMessage());
    HashMap<String, Integer> errorData = e.getMapMessage();
    showMessage(getString(errorData.get("userMessage")), MESSAJE_TITTLE);
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
