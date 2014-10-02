package com.dinerico.pos.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dinerico.pos.R;
import com.dinerico.pos.db.AccountDB;
import com.dinerico.pos.model.Account;
import com.dinerico.pos.model.Contributor;
import com.dinerico.pos.model.Store;
import com.dinerico.pos.network.config.ActivityBase;
import com.dinerico.pos.viewmodel.SignUpViewModel;

import java.util.ArrayList;

public class StoreActivity extends ActivityBase {
  private ListView listView;
  private ArrayList<Store> storeList;

  private SignUpViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_store);

    viewModel = new SignUpViewModel(new AccountDB(this),getSpiceManager());

    Contributor contributor = (Contributor) getIntent().getSerializableExtra
            (ContributorActivity.CONTRIBUTOR);
    storeList = contributor.getEstablecimientos();

    listView = (ListView) findViewById(R.id.listView);
    listView.setAdapter(new StoreListAdapter(StoreActivity.this, storeList));
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position,
                              long id) {
        Store store = storeList.get(position);
        Account.getInstance().setAddress(store.getDireccion().getCalle());
        Account.getInstance().setBusinessName(store.getNombreComercial());
        viewModel.createAccount(Account.getInstance());
      }
    });
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.store, menu);
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

  private class StoreListAdapter extends ArrayAdapter<Store> {
    private final Context context;
    private final ArrayList<Store> values;

    public StoreListAdapter(Context context, ArrayList<Store> values) {
      super(context, R.layout.item_store, values);
      this.context = context;
      this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      LayoutInflater inflater = (LayoutInflater) context
              .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      View rowView = inflater.inflate(R.layout.item_store, parent, false);
      TextView storeName = (TextView) rowView.findViewById(R.id.storeName);
      storeName.setText(values.get(position).getNombreComercial());
      TextView address = (TextView) rowView.findViewById(R.id.address);
      address.setText(values.get(position).getDireccion().getCalle());
      TextView local = (TextView) rowView.findViewById(R.id.local);
      local.setText(values.get(position).getCodigo());
      return rowView;
    }
  }

}
