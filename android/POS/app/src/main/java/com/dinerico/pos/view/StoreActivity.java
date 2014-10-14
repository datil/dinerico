package com.dinerico.pos.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.dinerico.pos.R;
import com.dinerico.pos.db.AccountDB;
import com.dinerico.pos.db.SessionDB;
import com.dinerico.pos.model.Account;
import com.dinerico.pos.model.Contributor;
import com.dinerico.pos.model.Session;
import com.dinerico.pos.model.Store;
import com.dinerico.pos.network.config.ActivityBase;
import com.dinerico.pos.viewmodel.SignUpViewModel;

import java.util.ArrayList;
import java.util.Calendar;

public class StoreActivity extends ActivityBase {
  private LinearLayout list;
  private ArrayList<Store> storeList;

  private SignUpViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_store);
    setUpActionBar();
    viewModel = new SignUpViewModel(new AccountDB(this), new SessionDB(this));
    Contributor contributor = (Contributor) getIntent().getSerializableExtra
            (ContributorActivity.CONTRIBUTOR);
    storeList = contributor.getEstablecimientos();

    list = (LinearLayout) findViewById(R.id.list);
    showListItems(storeList, list);
  }

  private void setUpActionBar() {
    hideActionBarComponents();
    View actionBar = getLayoutInflater().inflate(R.layout.action_bar_sign_up,
            null);
    View actionContainer = actionBar.findViewById(R.id.actionContainer);
    actionContainer.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        saveAccountAndSession(storeList, list);
      }
    });
    getActionBar().setCustomView(actionBar);
  }

  private void saveAccountAndSession(ArrayList<Store> storeList,
                                     LinearLayout list) {
    Store store = getCheckedRadioButtonId(list);
    Account.getInstance().setLocalNumber(store.getCodigo());
    Account.getInstance().setAddress(store.getDireccion().getCalle());
    Account.getInstance().setBusinessName(store.getNombreComercial());
    viewModel.createAccount(Account.getInstance());
    Calendar c = Calendar.getInstance();
    Session sessionFake = new Session();
    sessionFake.setCreated(c.toString());
    viewModel.createSession(sessionFake);

    Intent intent = new Intent(StoreActivity.this,
            WelcomeActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent
            .FLAG_ACTIVITY_CLEAR_TASK);
    startActivity(intent);
  }

  private Store getCheckedRadioButtonId(LinearLayout list) {
    Store store = new Store();
    for (int i = 0; i < list.getChildCount(); i++) {
      View row = list.getChildAt(i);
      RadioButton radioButton = (RadioButton) row.findViewById(R.id
              .radioButton);
      if (radioButton.isChecked())
        store = (Store) radioButton.getTag();
    }
    return store;
  }

  private void cleanRadioButtons(LinearLayout list){
    for (int i = 0; i < list.getChildCount(); i++) {
      View row = list.getChildAt(i);
      RadioButton radioButton = (RadioButton) row.findViewById(R.id
              .radioButton);
      radioButton.setChecked(false);
    }
  }

  private void showListItems(ArrayList<Store> storeList,
                             final LinearLayout list) {

    LayoutInflater inflater = (LayoutInflater) getSystemService(Context
            .LAYOUT_INFLATER_SERVICE);

    for (int position = 0; position < storeList.size(); position++) {
      View rowView = inflater.inflate(R.layout.item_store, null);
      TextView storeName = (TextView) rowView.findViewById(R.id.businessName);
      storeName.setText(storeList.get(position).getNombreComercial());
      TextView address = (TextView) rowView.findViewById(R.id.address);
      address.setText(getResources().getString(R.string.address) + storeList.get
              (position).getDireccion().getCalle());
      TextView localNumber = (TextView) rowView.findViewById(R.id.localNumber);
      localNumber.setText(storeList.get(position).getCodigo());
      RadioButton radioButton = (RadioButton) rowView.findViewById(R.id
              .radioButton);
      radioButton.setTag(storeList.get(position));
      radioButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          cleanRadioButtons(list);
          RadioButton radioButton1 = (RadioButton)view;
          radioButton1.setChecked(true);
        }
      });
      list.addView(rowView);
    }
  }


}
