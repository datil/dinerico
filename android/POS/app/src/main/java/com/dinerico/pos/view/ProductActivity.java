package com.dinerico.pos.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Selection;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dinerico.pos.R;
import com.dinerico.pos.db.ProductDB;
import com.dinerico.pos.exception.ValidationError;
import com.dinerico.pos.model.Product;
import com.dinerico.pos.network.config.FragmentActivityBase;
import com.dinerico.pos.util.ImageHelper;
import com.dinerico.pos.viewmodel.ProductViewModel;
import com.soundcloud.android.crop.Crop;

import java.io.File;
import java.util.Locale;

import rx.android.Events;
import rx.functions.Action1;

public class ProductActivity extends FragmentActivityBase {

  private ProductViewModel viewModel;
  private ViewHolder view;

  private static final int CAMERA_REQUEST = 1888;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product);
    viewModel = new ProductViewModel(new Product(), new ProductDB(this));
    view = new ViewHolder();

    Product product = (Product) getIntent().getSerializableExtra(CatalogActivity
            .EDIT_PRODUCT);
    if (product != null) {
      showProduct(product);
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode,
                                  Intent data) {

    if (resultCode == RESULT_OK) {

      switch (requestCode) {
        case Crop.REQUEST_PICK:
          beginCrop(data.getData());
          break;

        case CAMERA_REQUEST:
          beginCrop(data.getData());
          break;

        case Crop.REQUEST_CROP:
          handleImage(data);
          break;
      }
    } else if (resultCode == Crop.RESULT_ERROR) {
      Toast.makeText(this, Crop.getError(data).getMessage(),
              Toast.LENGTH_SHORT).show();
    }

  }

  private void beginCrop(Uri source) {
    Uri outputUri = Uri.fromFile(new File(getCacheDir(), "cropped"));
    new Crop(source).output(outputUri).asSquare().start(this);
  }

  private void handleImage(Intent result) {
    ImageHelper imageHelper = new ImageHelper(Crop.getOutput(result));
    Bitmap bitmap = imageHelper.getBitmap();
    view.image.setImageBitmap(bitmap);
    viewModel.setImage(bitmap);
    view.initialsImage.setVisibility(View.INVISIBLE);
    view.image.setVisibility(View.VISIBLE);
  }

  private void showProduct(Product product) {
    view.price.setText(String.format(Locale.US, "%.02f",
            product.getPrice()));
    view.name.setText(product.getName());
    if (product.getImageByte() != null) {
      view.initialsImage.setVisibility(View.INVISIBLE);
      view.image.setVisibility(View.VISIBLE);
      view.image.setImageBitmap(ImageHelper.getImageAjustedToDensity(product
              .getImageByte(), 100, 100, getResources().getDisplayMetrics()
              .density));
    } else {
      view.initialsImage.setVisibility(View.VISIBLE);
      view.image.setVisibility(View.INVISIBLE);
      view.initials.setText(product.getInitials());
      if (product.getColor() != 0) {
        view.initials.setBackgroundColor(product.getColor());
      }
    }
    view.delete.setVisibility(View.VISIBLE);
    viewModel.setModel(product);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.product, menu);
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
    public ImageView image;
    public View initialsImage;
    public TextView editImage;

    public ViewHolder() {
      findViews();
      subscribeToViewComponents();
    }

    private void findViews() {
      name = (EditText) findViewById(R.id.name);
      price = (EditText) findViewById(R.id.price);
      price.setText("0.00");
      initials = (TextView) findViewById(R.id.initials);
      editImage = (TextView) findViewById(R.id.editImage);
      delete = (Button) findViewById(R.id.delete);
      image = (ImageView) findViewById(R.id.image);
      delete.setOnClickListener(this);
      image.setOnClickListener(this);
      initialsImage = findViewById(R.id.initialsImage);
      initials.setOnClickListener(this);
      editImage.setOnClickListener(this);
    }

    private void subscribeToViewComponents() {
      Events.text(name).subscribe(new Action1<String>() {
        @Override
        public void call(String string) {
          viewModel.setName(string);
          if (string.length() < 3) {
            viewModel.setInitials(string);
            initials.setText(string);
          }

        }
      });

      Events.text(price).subscribe(new Action1<String>() {
        @Override
        public void call(String s) {
          if (!s.toString().matches("(\\d{1,3}(\\,\\d{3})*|(\\d+))(\\.\\d{2})" +
                  "?$")) {
            String userInput = "" + s.toString().replaceAll("[^\\d]", "");
            StringBuilder cashAmountBuilder = new StringBuilder(userInput);

            while (cashAmountBuilder.length() > 3
                    && cashAmountBuilder.charAt(0) == '0') {
              cashAmountBuilder.deleteCharAt(0);
            }
            while (cashAmountBuilder.length() < 3) {
              cashAmountBuilder.insert(0, '0');
            }
            cashAmountBuilder.insert(cashAmountBuilder.length() - 2, '.');

            price.setText(cashAmountBuilder.toString());
            // keeps the cursor always to the right
            Selection.setSelection(price.getText(), cashAmountBuilder.toString()
                    .length());
            viewModel.setPrice(cashAmountBuilder.toString());
          }
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
      switch (view.getId()) {
        case R.id.delete:
          viewModel.deleteProduct();
          setResult(RESULT_OK);
          finish();
          break;
        case R.id.initials:
          DialogFragment newFragment = new ProductImageFragment();
          newFragment.show(getSupportFragmentManager(), "pickerFragment");
          break;
        case R.id.editImage:
          newFragment = new ProductImageFragment();
          newFragment.show(getSupportFragmentManager(), "pickerFragment");
          break;
        case R.id.image:
          newFragment = new ProductImageFragment();
          newFragment.show(getSupportFragmentManager(), "pickerFragment");
          break;
      }

    }
  }

}