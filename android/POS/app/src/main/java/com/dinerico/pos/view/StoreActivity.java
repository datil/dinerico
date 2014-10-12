package com.dinerico.pos.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
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

    viewModel = new SignUpViewModel(new AccountDB(this), new SessionDB(this));

    Contributor contributor = (Contributor) getIntent().getSerializableExtra
            (ContributorActivity.CONTRIBUTOR);
    storeList = contributor.getEstablecimientos();

    list = (LinearLayout) findViewById(R.id.list);
    showListItems(storeList,list);
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
//    getMenuInflater().inflate(R.menu.store, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    switch (item.getItemId()) {
      case R.id.action_continue:
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  private void showListItems(ArrayList<Store> storeList, LinearLayout list) {

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
      rowView.setTag(storeList.get(position));

      rowView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Store store = (Store) view.getTag();
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
      });
      list.addView(rowView);
    }
  }

}
