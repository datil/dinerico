package com.dinerico.pos.network.config;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.dinerico.pos.R;
import com.dinerico.pos.exception.ValidationError;
import com.octo.android.robospice.SpiceManager;

import java.util.HashMap;

/**
 * Created by josephleon on 9/30/14.
 */

public abstract class ActivityBase extends Activity {

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

  public void showProgressDialog(String message) {
    dialog = new Dialog(this, R.style.CustomDialogProgressTheme);
    dialog.setContentView(R.layout.progress_dialog);
    ((TextView) dialog.findViewById(R.id.dialogText)).setText(message);
    dialog.show();
  }

  public void hideActionBarComponents() {
    getActionBar().setDisplayShowHomeEnabled(false);
    getActionBar().setDisplayShowTitleEnabled(false);
    getActionBar().setDisplayShowCustomEnabled(true);
  }

  public void dismissProgressDialog() {
    if (dialog != null && dialog.isShowing())
      dialog.dismiss();
  }

  public void showErrorValidation(ValidationError e) {
    HashMap<String, Integer> errorData = e.getMapMessage();
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle(this.getResources().getString(R.string.validationErrorTittle));
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

  public SpiceManager getSpiceManager() {
    return spiceManager;
  }
}
