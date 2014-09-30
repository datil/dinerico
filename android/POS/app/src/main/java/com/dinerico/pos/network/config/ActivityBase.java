package com.dinerico.pos.network.config;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.TextView;

import com.dinerico.pos.R;
import com.octo.android.robospice.SpiceManager;

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
    ((TextView)dialog.findViewById(R.id.dialogText)).setText(message);
    dialog.show();
  }

  public void dismissProgressDialog() {
    if (dialog != null && dialog.isShowing())
      dialog.dismiss();
  }

  public SpiceManager getSpiceManager() {
    return spiceManager;
  }
}
