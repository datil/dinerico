package com.dinerico.pos.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.dinerico.pos.R;
import com.dinerico.pos.db.ProductDB;
import com.dinerico.pos.model.Product;
import com.dinerico.pos.network.config.ActivityBase;
import com.dinerico.pos.viewmodel.CatalogViewModel;

import java.util.ArrayList;

import rx.android.Events;
import rx.functions.Action1;

public class CatalogActivity extends ActivityBase {

  private CatalogViewModel viewModel;
  private ViewHolder view;

  private ProductsListViewAdapter adapter;

  public final static String EDIT_PRODUCT = "edit_product";
  public final static String DELETED_PRODUCT = "product_deleted";
  private static final int CREATE_EDIT_PRODUCT_REQUEST = 101;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_catalog);
    setUpActionBar();
    viewModel = new CatalogViewModel(new ProductDB(this));
    view = new ViewHolder();
    showProductList();
  }

  private void setUpActionBar(){
    hideActionBarComponents();
    View actionBar = getLayoutInflater().inflate(R.layout.action_bar_catalog,
            null);
    View actionContainer = actionBar.findViewById(R.id.actionContainer);
    actionContainer.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        createProduct();
      }
    });
    getActionBar().setCustomView(actionBar);
  }

  private void showProductList() {
    if (!viewModel.getProductList().isEmpty()) {
      view.addImage.setVisibility(View.INVISIBLE);
      view.productList.setVisibility(View.VISIBLE);
      view.headerList.setVisibility(View.VISIBLE);
    }
  }

  private void createProduct() {
    Intent intent = new Intent(this, ProductActivity.class);
    startActivityForResult(intent, CREATE_EDIT_PRODUCT_REQUEST);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode,
                                  Intent data) {
    if (resultCode == RESULT_OK && requestCode == CREATE_EDIT_PRODUCT_REQUEST) {
      ArrayList<Product> catalog = viewModel.getProductList();
      if (catalog.size() > 0) {
        view.addImage.setVisibility(View.INVISIBLE);
        view.productList.setVisibility(View.VISIBLE);
        view.headerList.setVisibility(View.VISIBLE);
        adapter = new ProductsListViewAdapter(this, catalog);
        view.productList.setAdapter(adapter);
      } else {
        view.addImage.setVisibility(View.VISIBLE);
        view.productList.setVisibility(View.INVISIBLE);
        view.headerList.setVisibility(View.GONE);
      }

    }
  }

  private class ViewHolder implements AdapterView.OnItemClickListener {
    public EditText search;
    public ListView productList;
    public View addImage;
    public View headerList;

    public ViewHolder() {
      findViews();
      subscribeToViewComponents();
    }

    private void findViews() {
      search = (EditText) findViewById(R.id.search);
      addImage = findViewById(R.id.addImage);
      productList = (ListView) findViewById(R.id.listView);
      productList.setOnItemClickListener(this);
      adapter = new ProductsListViewAdapter(CatalogActivity.this,
              viewModel.getProductList());
      productList.setAdapter(adapter);
      headerList = findViewById(R.id.headerList);
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
    public void onItemClick(AdapterView<?> adapterView, View view,
                            int position, long l) {
      Intent intent = new Intent(CatalogActivity.this,
              ProductActivity.class);
      Product product = viewModel.getProductList().get(position);
      intent.putExtra(EDIT_PRODUCT, product);
//      startActivity(intent);
      startActivityForResult(intent, CREATE_EDIT_PRODUCT_REQUEST);
    }
  }

}
