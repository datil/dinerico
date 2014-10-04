package com.dinerico.pos.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dinerico.pos.R;
import com.dinerico.pos.db.ProductDB;
import com.dinerico.pos.exception.ValidationError;
import com.dinerico.pos.model.Product;
import com.dinerico.pos.network.config.ActivityBase;
import com.dinerico.pos.viewmodel.CreateProductViewModel;

import rx.android.Events;
import rx.functions.Action1;

public class CreateProductActivity extends ActivityBase {

  private CreateProductViewModel viewModel;
  private ViewHolder view;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_product);
    viewModel = new CreateProductViewModel(new Product(), new ProductDB(this));
    view = new ViewHolder();

    Product product = (Product) getIntent().getSerializableExtra(CatalogActivity
            .EDIT_PRODUCT);
    if (product != null) {
      showProduct(product);
    }
  }

  private void showProduct(Product product){
    view.price.setText(String.valueOf(product.getPrice()));
    view.name.setText(product.getName());
    view.initials.setText(product.getInitials());
    viewModel.setModel(product);
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.create_product, menu);
    return true;
  }

  private void saveProduct() {
    try {
      viewModel.getModel().validate();
      viewModel.saveProduct();
      setResult(RESULT_OK);
      finish();
    } catch (ValidationError e) {
      showErrorValidation(e, this);
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.save:
        saveProduct();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  private class ViewHolder implements View.OnClickListener {
    public EditText name;
    public EditText price;
    public TextView initials;
    public Button delete;

    public ViewHolder() {
      findViews();
      subscribeToViewComponents();
    }

    private void findViews() {
      name = (EditText) findViewById(R.id.name);
      price = (EditText) findViewById(R.id.price);
      initials = (TextView) findViewById(R.id.initials);
    }

    private void subscribeToViewComponents() {
      Events.text(name).subscribe(new Action1<String>() {
        @Override
        public void call(String string) {
          viewModel.setName(string);
        }
      });

      Events.text(price).subscribe(new Action1<String>() {
        @Override
        public void call(String string) {
          viewModel.setPrice(string);
        }
      });

      Events.text(initials).subscribe(new Action1<String>() {
        @Override
        public void call(String string) {
          viewModel.setInitials(string);
        }
      });

    }

    @Override
    public void onClick(View view) {
      viewModel.deleteProduct();
      setResult(RESULT_OK);
      finish();
    }
  }

}