package com.dinerico.pos.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dinerico.pos.R;
import com.dinerico.pos.db.OrderDB;
import com.dinerico.pos.db.OrderItemDB;
import com.dinerico.pos.db.ProductDB;
import com.dinerico.pos.model.Account;
import com.dinerico.pos.model.Order;
import com.dinerico.pos.model.Product;
import com.dinerico.pos.network.config.ActivityBase;
import com.dinerico.pos.viewmodel.ShopViewModel;

import java.util.ArrayList;

import rx.android.Events;
import rx.functions.Action1;

public class ShopActivity extends ActivityBase {

  private ShopViewModel viewModel;
  private ViewHolder view;

  private ProductsListViewAdapter adapter;

  public final static int DELETED_SALE = 101;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    viewModel = new ShopViewModel(Order.getInstance(),new ProductDB(this),
            new OrderItemDB(this), new OrderDB(this));
  }

  private void setUpActionBar() {
    hideActionBarComponents();
    View actionBar = getLayoutInflater().inflate(R.layout.action_bar_shop,
            null);
    View actionContainer = actionBar.findViewById(R.id.actionContainer);
    actionContainer.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startCatalog();
      }
    });
    TextView tittle = (TextView) actionBar.findViewById(R.id.titleText);
    tittle.setText(Account.getInstance().getStore().getNombreComercial());
    getActionBar().setCustomView(actionBar);
  }


  @Override
  protected void onResume() {
    super.onResume();
    setContentView(R.layout.activity_shop);
    setUpActionBar();

    view = new ViewHolder();
    ArrayList<Product> catalog = viewModel.getCatalog();
    if (!catalog.isEmpty()) {
      view.addImage.setVisibility(View.INVISIBLE);
      view.productList.setVisibility(View.VISIBLE);
      view.headerList.setVisibility(View.VISIBLE);
      adapter = new ProductsListViewAdapter(this, viewModel.getCatalog());
      view.productList.setAdapter(adapter);
    } else {
      view.addImage.setVisibility(View.VISIBLE);
      view.productList.setVisibility(View.INVISIBLE);
      view.headerList.setVisibility(View.GONE);
    }

  }

  private void startCatalog() {
    Intent intent = new Intent(this, CatalogActivity.class);
    startActivity(intent);
  }


  private void startCart() {
    System.gc();
    Intent intent = new Intent(this, CartActivity.class);
    System.out.println(viewModel.generateOrder());
    startActivityForResult(intent, DELETED_SALE);
//    startActivity(intent);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode,
                                  Intent data) {
    if (requestCode == DELETED_SALE && resultCode == RESULT_OK) {
      viewModel = new ShopViewModel(Order.getInstance(),new ProductDB(this),
              new OrderItemDB(this), new OrderDB(this));
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.shop, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.logout:
        viewModel.logout(this);
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  private class ViewHolder implements View.OnClickListener,
          AdapterView.OnItemClickListener {
    public EditText search;
    public View actualSalesButton;
    public ListView productList;
    public View addImage;
    public View headerList;
    public TextView counter;

    public ViewHolder() {
      findViews();
      subscribeToViewComponents();
    }

    private void findViews() {
      search = (EditText) findViewById(R.id.search);
      actualSalesButton = findViewById(R.id.actualSalesButton);
      actualSalesButton.setOnClickListener(this);
      addImage = findViewById(R.id.addImage);
      productList = (ListView) findViewById(R.id.listView);
      productList.setOnItemClickListener(this);
      adapter = new ProductsListViewAdapter(ShopActivity.this,
              viewModel.getCatalog());
      productList.setAdapter(adapter);
      headerList = findViewById(R.id.headerList);
      counter = (TextView) findViewById(R.id.counter);
      counter.setText("(" + viewModel.getCounter() + ")");
    }

    private void subscribeToViewComponents() {

      Events.text(search).subscribe(new Action1<String>() {
        @Override
        public void call(String string) {
          viewModel.setSearch(string);
          adapter.filter(string);
        }
      });

    }

    @Override
    public void onClick(View view) {
      startCart();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view,
                            int position, long l) {
      Product product = viewModel.getCatalog().get(position);
      viewModel.addToCart(product);
      Toast.makeText(ShopActivity.this, "Producto agregado",
              Toast.LENGTH_SHORT).show();

      viewModel.setCounter(viewModel.getCounter() + 1);
      counter.setText("(" + viewModel.getCounter() + ")");
    }
  }
}
