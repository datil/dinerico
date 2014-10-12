package com.dinerico.pos.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.dinerico.pos.R;
import com.dinerico.pos.model.Invoice;
import com.dinerico.pos.network.config.ActivityBase;
import com.dinerico.pos.network.service.InvoiceService;
import com.dinerico.pos.viewmodel.ReceiptViewModel;

import rx.android.Events;
import rx.functions.Action1;


public class ReceiptActivity extends ActivityBase {

  private String emailString;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_receipt);
    ReceiptViewModel viewModel = new ReceiptViewModel(new Invoice(),new InvoiceService(getSpiceManager()));
    ViewHolder view = new ViewHolder();
  }

  private void sendReceiptByEmail(){
    Intent intent = new Intent(this, ReceiptSentActivity.class);
    startActivity(intent);
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.new_sale, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    switch (item.getItemId()) {
      case R.id.newSale:
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  private class ViewHolder implements View.OnClickListener{
    public EditText email;
    private ImageView sendEmail;

    public ViewHolder() {
      findViews();
      subscribeToViewComponents();
    }

    private void findViews() {
      email = (EditText) findViewById(R.id.email);
      sendEmail = (ImageView) findViewById(R.id.sendEmail);
      sendEmail.setOnClickListener(this);
    }

    private void subscribeToViewComponents() {

      Events.text(email).subscribe(new Action1<String>() {
        @Override
        public void call(String string) {
          emailString = string;
        }
      });

    }

    @Override
    public void onClick(View view) {
      sendReceiptByEmail();
    }
  }
}
