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
import com.dinerico.pos.model.Order;
import com.dinerico.pos.util.Utils;

/**
 * Created by josephleon on 10/29/14.
 */
public class CashFragment extends DialogFragment {

  private PaymentTypeActivity act;
  private EditText money;

  public static final String MESSAGE_TITTLE = "Cobro en efectivo";

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    act = (PaymentTypeActivity) getActivity();
    LayoutInflater inflater = act.getLayoutInflater();
    View view = inflater.inflate(R.layout.fragment_cash, null);
    money = (EditText) view.findViewById(R.id.pin);

    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    builder.setTitle(getString(R.string.cashDes));
    builder.setView(view).setPositiveButton(R.string.charge, new ConfirmClick())
            .setNegativeButton(R.string.cancel, new CancelClick());
    return builder.create();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    //Show keyboard
    money.requestFocus();
    getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams
            .SOFT_INPUT_STATE_VISIBLE);
    return super.onCreateView(inflater, container, savedInstanceState);
  }


  final private class ConfirmClick implements DialogInterface.OnClickListener {

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
      double orderTotal = Order.getInstance().getTotal();
      String money = CashFragment.this.money.getText().toString();
      Double d = 0.00;
      if (!Utils.isValidString(money)) {
        act.showMessage("Por favor ingrese un valor", MESSAGE_TITTLE);
      }
//      else if (orderTotal > Double.parseDouble(money)){
//        act.showMessage("El monto ingresado es mayor a la orden",MESSAGE_TITTLE);
//      }
      else{
        d = Double.parseDouble(money);
        double change = d - orderTotal;
        Intent intent = new Intent(getActivity(), SuccessfulCashSaleActivity.class);
        intent.putExtra("change", change);
        startActivity(intent);
      }


    }
  }

  final private class CancelClick implements DialogInterface.OnClickListener {

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
      dialogInterface.cancel();
    }
  }

}
