package com.dinerico.pos.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dinerico.pos.R;
import com.dinerico.pos.model.Tax;

import java.util.ArrayList;

/**
 * Created by josephleon on 10/21/14.
 */

public class CustomSpinnerAdapter extends ArrayAdapter<Tax> {

  private ArrayList<Tax> data;
  public Resources res;
  LayoutInflater inflater;

  public CustomSpinnerAdapter(
          Activity activity,
          int textViewResourceId,
          ArrayList objects
  ) {
    super(activity, textViewResourceId, objects);
    data = objects;
    inflater = (LayoutInflater) activity.getSystemService(Context
            .LAYOUT_INFLATER_SERVICE);
  }

  @Override
  public View getDropDownView(int position, View convertView,
                              ViewGroup parent) {
    View row = inflater.inflate(R.layout.item_spinner, parent, false);
    row.setPadding(16, 16, 16, 16);
    if (position != data.size()-1) {
      Tax tax = data.get(position + 1);
      TextView description = (TextView) row.findViewById(R.id.description);
      TextView percentage = (TextView) row.findViewById(R.id.percentage);

      // Set values for spinner each row
      description.setText(tax.getDescription());
      percentage.setText(tax.getPercentage());
    }
    return row;

  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View row = inflater.inflate(R.layout.item_spinner, parent, false);

    Tax tax = data.get(position);

    TextView description = (TextView) row.findViewById(R.id.description);
    TextView percentage = (TextView) row.findViewById(R.id.percentage);

    // Set values for spinner each row
    description.setText(tax.getDescription());
    percentage.setText(tax.getPercentage());

    return row;
  }

}
