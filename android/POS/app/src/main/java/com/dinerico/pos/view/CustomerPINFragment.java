package com.dinerico.pos.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.dinerico.pos.R;

/**
 * Created by josephleon on 10/20/14.
 */
public class CustomerPINFragment extends DialogFragment {

  CustomerDataEMActivity activity;
  EditText pin;

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    activity = (CustomerDataEMActivity)getActivity();

    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

    LayoutInflater inflater = getActivity().getLayoutInflater();
    View view = inflater.inflate(R.layout.fragment_customer_pin, null);
    pin = (EditText) view.findViewById(R.id.pin);
    builder.setTitle(getString(R.string.enterPin));
    builder.setView(view)
            .setPositiveButton(R.string.accept, new DialogInterface
                    .OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int id) {
                activity.viewModel.setPin(pin.getText().toString());
                Intent intent = new Intent(activity, SuccessfulSaleActivity.class);
                startActivity(intent);
              }
            })
            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int id) {
                CustomerPINFragment.this.getDialog().cancel();
              }
            });
    return builder.create();
  }
}
