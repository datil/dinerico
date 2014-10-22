package com.dinerico.pos.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dinerico.pos.R;
import com.dinerico.pos.db.ProductDB;
import com.dinerico.pos.model.Order;
import com.dinerico.pos.model.OrderItem;
import com.dinerico.pos.network.config.ActivityBase;
import com.dinerico.pos.util.Utils;
import com.dinerico.pos.viewmodel.CartViewModel;

import java.util.ArrayList;
import java.util.Iterator;

public class CartActivity extends ActivityBase {

  private CartViewModel viewModel;
  private ViewHolder view;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cart);
    setUpActionBar();
    viewModel = new CartViewModel(Order.getInstance(), new ProductDB(this));
    view = new ViewHolder();
    showListItems(viewModel.getOrder(), (LinearLayout) view.itemList);
    showTotals(view.charge, view.totalBill, viewModel.getOrder().getTotal());

  }

  private void setUpActionBar() {
    hideActionBarComponents();
    View actionBar = getLayoutInflater().inflate(R.layout.action_bar_catalog,
            null);
    View actionContainer = actionBar.findViewById(R.id.actionContainer);
    actionContainer.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        cleanCart();
      }
    });
    TextView action = (TextView) actionBar.findViewById(R.id.action);
    action.setText(getString(R.string.deleteSale));

    ImageView actionImg = (ImageView) actionBar.findViewById(R.id.actionImg);
    actionImg.setImageResource(R.drawable.delete_sale);

    getActionBar().setCustomView(actionBar);
  }

  private void cleanCart() {
    Order.reset();
    Toast.makeText(this, "Venta eliminada", Toast.LENGTH_SHORT).show();
    setResult(RESULT_OK, null);
    finish();
  }

  private void showTotals(Button charge, TextView totalBill, float total) {
    String value = Utils.currencyFormatter(total);
    totalBill.setText(value);
    String chargeAmount = String.format(getString(R.string.chargeAmount),
            value);
    charge.setText(chargeAmount);
  }

  private void charge() {
    Intent intent = new Intent(this, PaymentTypeActivity.class);
    startActivity(intent);
  }

  private void showListItems(Order cart, LinearLayout list) {

    LayoutInflater inflater = (LayoutInflater) getSystemService(Context
            .LAYOUT_INFLATER_SERVICE);
        ArrayList<OrderItem> items = cart.getItems();
    if (items != null) {
      Iterator<OrderItem> iterator = items.iterator();
      while (iterator.hasNext()) {
        OrderItem item = iterator.next();
        View rowView = inflater.inflate(R.layout.item_bill, null);
        TextView productName = (TextView) rowView.findViewById(R.id.name);
        productName.setText(item.getProduct().getName());
        TextView amount = (TextView) rowView.findViewById(R.id.amount);
        amount.setText("(x" + item.getAmount() + ")");
        TextView price = (TextView) rowView.findViewById(R.id.price);
        price.setText(Utils.currencyFormatter(item.getTotal()));
        list.addView(rowView);
      }
    }

  }

  private class ViewHolder implements View.OnClickListener {
    public Button charge;
    public TextView totalBill;
    public View itemList;

    public ViewHolder() {
      findViews();
    }

    private void findViews() {
      charge = (Button) findViewById(R.id.charge);
      charge.setOnClickListener(this);
      totalBill = (TextView) findViewById(R.id.totalBill);
      itemList = findViewById(R.id.itemList);
    }

    @Override
    public void onClick(View view) {
      switch (view.getId()) {
        case R.id.charge:
          charge();
          break;
      }
    }

  }

}
