package com.dinerico.pos.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.dinerico.pos.R;
import com.soundcloud.android.crop.Crop;

/**
 * Created by josephleon on 10/4/14.
 */

public class ProductImageFragment extends DialogFragment {

  private static final int CAMERA_REQUEST = 1888;
  private ProductActivity activity;

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    activity = (ProductActivity) getActivity();
    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
    builder.setTitle(R.string.options_to_image)
            .setItems(R.array.image_options,
                    new DialogInterface.OnClickListener() {
                      public void onClick(DialogInterface dialog, int which) {
                        if (which == 0)
                          Crop.pickImage(activity);
                        if (which == 1) {
                          Intent cameraIntent = new Intent(android.provider
                                  .MediaStore.ACTION_IMAGE_CAPTURE);
                          activity.startActivityForResult(cameraIntent,
                                  CAMERA_REQUEST);
                        }
                      }
                    });
    return builder.create();
  }
}