package com.dinerico.pos.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.dinerico.pos.R;
import com.dinerico.pos.db.ProductDB;
import com.dinerico.pos.model.Product;
import com.dinerico.pos.network.config.ActivityBase;
import com.dinerico.pos.viewmodel.CatalogViewModel;

import rx.android.Events;
import rx.functions.Action1;

public class CatalogActivity extends ActivityBase {

  private CatalogViewModel viewModel;
  private ViewHolder view;

  private ProductsListViewAdapter adapter;

  public final static String EDIT_PRODUCT = "edit_product";
  private static final int CREATE_EDIT_PRODUCT_REQUEST = 101;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_catalog);
    viewModel = new CatalogViewModel(new ProductDB(this));
    view = new ViewHolder();
    showProductList();
  }

  private void showProductList(){
    if(!viewModel.getProductList().isEmpty()){
      view.addImage.setVisibility(View.GONE);
      view.productList.setVisibility(View.VISIBLE);
    }
  }

  private void createProduct() {
    Intent intent = new Intent(this, CreateProductActivity.class);
    startActivityForResult(intent,CREATE_EDIT_PRODUCT_REQUEST);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode,
                                  Intent data) {
    if (resultCode == RESULT_OK && requestCode == CREATE_EDIT_PRODUCT_REQUEST) {
      adapter = new ProductsListViewAdapter(this,viewModel.getProductList());
      view.productList.setAdapter(adapter);
      System.out.println("Entro en result");
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.catalog, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.addProduct:
        createProduct();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  private class ViewHolder implements AdapterView.OnItemClickListener{
    public EditText search;
    public ListView productList;
    public View addImage;

    public ViewHolder() {
      findViews();
      subscribeToViewComponents();
    }

    private void findViews() {
      search = (EditText) findViewById(R.id.search);
      search.clearFocus();
      addImage = findViewById(R.id.addImage);
      productList = (ListView)findViewById(R.id.listView);
      productList.setOnItemClickListener(this);
      adapter = new ProductsListViewAdapter(CatalogActivity.this,viewModel.getProductList());
      productList.setAdapter(adapter);
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
                            int position,long l) {
      Intent intent = new Intent(CatalogActivity.this,
              CreateProductActivity.class);
      Product product = viewModel.getProductList().get(position);
      intent.putExtra(EDIT_PRODUCT,product);
//      startActivity(intent);
      startActivityForResult(intent,CREATE_EDIT_PRODUCT_REQUEST);
    }
  }

}
