package com.dinerico.pos.network.config;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.dinerico.pos.R;
import com.dinerico.pos.exception.ValidationError;
import com.dinerico.pos.model.RestError;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;

import java.lang.reflect.Type;
import java.util.HashMap;

import retrofit.RetrofitError;

/**
 * Created by josephleon on 9/30/14.
 */

public abstract class FragmentActivityBase extends FragmentActivity {

  private Dialog dialog;
  private SpiceManager spiceManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    spiceManager = new SpiceManager(ApiService.class);
    getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
    getActionBar().setIcon(R.drawable.logo);
    getActionBar().setDisplayShowTitleEnabled(false);
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
  }

  @Override
  protected void onStart() {
    super.onStart();
    spiceManager.start(this);
  }

  @Override
  protected void onStop() {

    if (spiceManager.isStarted()) {
      spiceManager.shouldStop();
      dismissProgressDialog();
    }
    super.onStop();
  }

  public void showProgressDialog() {
    dialog = new Dialog(this, R.style.CustomDialogProgressTheme);
    dialog.setContentView(R.layout.progress_dialog);
    dialog.setCancelable(false);
    dialog.show();
  }

  public void hideActionBarComponents() {
    getActionBar().setDisplayShowTitleEnabled(false);
    getActionBar().setDisplayShowCustomEnabled(true);
    getActionBar().setDisplayShowHomeEnabled(false);
    getActionBar().setDisplayHomeAsUpEnabled(false);
  }

  public void dismissProgressDialog() {
    if (dialog != null && dialog.isShowing())
      dialog.dismiss();
  }

  public void showErrorValidation(ValidationError e) {
    HashMap<String, Integer> errorData = e.getMapMessage();
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle(this.getResources().getString(R.string
            .validationErrorTittle));
    builder.setMessage(getResources().getString(errorData.get("userMessage")));
    builder.setCancelable(true);
    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int id) {
        dialog.cancel();
      }
    });

    AlertDialog alert = builder.create();
    alert.show();
  }

  public void showMessage(String message, String tittle) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle(tittle);
    builder.setMessage(message);
    builder.setCancelable(true);
    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int id) {
        dialog.cancel();
      }
    });

    AlertDialog alert = builder.create();
    alert.show();
  }

  public void showMessage(String message, String tittle,
                          DialogInterface.OnClickListener okListener) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle(tittle);
    builder.setMessage(message);
    builder.setCancelable(true);
    builder.setPositiveButton("Ok",okListener);
    builder.setNegativeButton("Cancelar",new DialogInterface.OnClickListener
            () {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        dismissProgressDialog();
        dialogInterface.cancel();
      }
    });

    AlertDialog alert = builder.create();
    alert.show();
  }

  public void showNetworkError(SpiceException e, String tittle,
                               Type errorType) {
    String errorMessage = "Lo sentimos, algo no salio bien, " +
            "estamos revisando para corregirlo";
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle(tittle);
    builder.setCancelable(true);
    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int id) {
        dialog.cancel();
      }
    });

    if (e.getCause() instanceof RetrofitError) {
      RetrofitError retrofitError = (RetrofitError) e.getCause();
      Object object = retrofitError.getBodyAs(errorType);
      if (object != null && object instanceof RestError) {
        RestError restError = (RestError) object;
        errorMessage = restError.getError();
      }
    }

    builder.setMessage(errorMessage);
    AlertDialog alert = builder.create();
    alert.show();

  }


  public SpiceManager getSpiceManager() {
    return spiceManager;
  }
}
