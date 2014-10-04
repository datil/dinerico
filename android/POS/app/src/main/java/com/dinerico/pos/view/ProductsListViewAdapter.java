package com.dinerico.pos.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dinerico.pos.R;
import com.dinerico.pos.model.Product;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by josephleon on 10/3/14.
 */

public class ProductsListViewAdapter extends ArrayAdapter<Product> {

  private ArrayList<Product> productList;
  private Activity context;
  private ArrayList<Product> allProducts;

  public ProductsListViewAdapter(Activity context,
                                 ArrayList<Product> productList) {
    super(context, 0, productList);
    this.context = context;
    this.productList = productList;
    this.allProducts = new ArrayList<Product>();
    this.allProducts.addAll(productList);
  }

  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) context
            .getSystemService(context.LAYOUT_INFLATER_SERVICE);
    View rowView = inflater.inflate(R.layout.item_product, parent, false);

    TextView name = (TextView) rowView.findViewById(R.id.name);
    TextView price = (TextView) rowView.findViewById(R.id.price);
    TextView initials = (TextView) rowView.findViewById(R.id.initials);
    ImageView image = (ImageView) rowView.findViewById(R.id.image);

    Product product = productList.get(position);

    name.setText(product.getName());
    price.setText("$" + String.valueOf(product.getPrice()));

    if (product.getImage() != null) {
      initials.setVisibility(View.INVISIBLE);
      image.setVisibility(View.VISIBLE);
      image.setImageBitmap(product.getImage());
    } else {
      initials.setText(product.getInitials());
      if (product.getColor() != 0) {
        initials.setBackgroundColor(product.getColor());
      }
    }

    return rowView;
  }

  public void filter(String charText) {
    charText = charText.toLowerCase(Locale.getDefault());
    productList.clear();
    if (charText.length() == 0) {
      productList.addAll(allProducts);
    } else {
      for (Product product : allProducts) {
        if (product.getName().toLowerCase(Locale.getDefault()).contains
                (charText)) {
          productList.add(product);
        } else if (product.getInitials().toLowerCase(Locale.getDefault())
                .contains(charText)) {
          productList.add(product);
        } else if (String.valueOf(product.getPrice()).toLowerCase(Locale
                .getDefault()).contains(charText)) {
          productList.add(product);
        }
      }
    }
    notifyDataSetChanged();
  }

}