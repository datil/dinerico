package com.dinerico.pos.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import com.dinerico.pos.R;
import com.dinerico.pos.model.Account;
import com.dinerico.pos.model.EMResponse;
import com.dinerico.pos.model.Order;
import com.dinerico.pos.util.Utils;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

/**
 * Created by josephleon on 10/20/14.
 */
public class CustomerPINFragment extends DialogFragment {

  private EMPaymentActivity act;
  private EditText pin;

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    act = (EMPaymentActivity) getActivity();
    LayoutInflater inflater = act.getLayoutInflater();
    View view = inflater.inflate(R.layout.fragment_customer_pin, null);
    pin = (EditText) view.findViewById(R.id.pin);

    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    builder.setTitle(getString(R.string.enterPin));
    builder.setView(view).setPositiveButton(R.string.accept, new ConfirmClick())
            .setNegativeButton(R.string.cancel, new CancelClick());
    return builder.create();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

    //Show keyboard
    pin.requestFocus();
    getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    return super.onCreateView(inflater, container, savedInstanceState);
  }

  final private class EMPaymentListener implements RequestListener<EMResponse> {

    @Override
    public void onRequestFailure(SpiceException spiceException) {
      act.dismissProgressDialog();
      act.showMessage(getString(R.string.sorrySomethingWasWrong),
              act.MESSAJE_TITTLE);
    }

    @Override
    public void onRequestSuccess(EMResponse emResponse) {

      if (emResponse.getResult() == 1) {
        act.showMessage(emResponse.getText(), act.MESSAJE_TITTLE,
                new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                    act.viewModel.emPaymentConfirm(
                            Account.getInstance().getStore().getNumeroCelular(),
                            Order.getInstance().getTotal(),
                            new ConfirmEMPaymentListener());
                  }
                });
      } else {
        act.dismissProgressDialog();
        act.showMessage(emResponse.getText(),
                act.MESSAJE_TITTLE);
      }


    }
  }

  final private class ConfirmEMPaymentListener implements
          RequestListener<EMResponse> {

    @Override
    public void onRequestFailure(SpiceException spiceException) {
      act.dismissProgressDialog();
      act.showMessage(getString(R.string.sorrySomethingWasWrong),
              act.MESSAJE_TITTLE);

    }

    @Override
    public void onRequestSuccess(EMResponse emResponse) {
      act.dismissProgressDialog();
      if (emResponse.getResult() == 1) {
        Intent intent = new Intent(act, SuccessfulSaleActivity.class);
        intent.putExtra(act.BCE_RESPONSE, emResponse);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent
                .FLAG_ACTIVITY_CLEAR_TASK);
        act.startActivity(intent);
      } else {
        act.showMessage(emResponse.getText(), act.MESSAJE_TITTLE);
      }
    }
  }

  final private class ConfirmClick implements DialogInterface.OnClickListener {

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
      String ping = pin.getText().toString();
      if (Utils.isValidString(ping)) {
        act.showProgressDialog();
        act.viewModel.setPin(ping);
        act.viewModel.emPayment(
                Account.getInstance().getStore().getNumeroCelular(),
                Order.getInstance().getTotal(),
                new EMPaymentListener());

      } else
        act.showMessage(
                getString(R.string.noValidPin),
                getString(R.string.validationErrorTittle));
    }
  }

  final private class CancelClick implements DialogInterface.OnClickListener {

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
      dialogInterface.cancel();
    }
  }

}
