package com.dinerico.pos.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by josephleon on 8/27/14.
 */
public class ImageHelper {

  private Uri imageUri;
  public static final String FILE_NAME = "logo.jpeg";
  public static final String MIME_TYPE = "image/jpeg";

  public static final int WIDTH = 500;
  public static final int HEIGHT = 500;

  public ImageHelper(Uri imageUri) {
    this.imageUri = imageUri;
  }

  public static byte[] getBytes(Bitmap bmp) {
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
    return stream.toByteArray();
  }

  public Bitmap getBitmap() {
    return getRotated();
  }

  public File getFileImage(Context context) {
    try {

      //create a file to write bitmap data
      File f = new File(context.getCacheDir(), FILE_NAME);
      f.createNewFile();

      Log.d(ImageHelper.class.getSimpleName(), "Image file created on: "
              + context.getCacheDir());

      //Convert bitmap to byte array
      Bitmap bitmap = getBitmap();
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
      byte[] bitmapdata = bos.toByteArray();

      //write the bytes in file
      FileOutputStream fos = new FileOutputStream(f);

      fos.write(bitmapdata);
      fos.flush();
      fos.close();
      return f;

    } catch (IOException e) {
      e.printStackTrace();
      Log.e(ImageHelper.class.getSimpleName(), "Error converting bitmap image" +
              " to file");
      return null;
    }
  }

  private Bitmap getRotated() {
    try {
      File f = new File(imageUri.getPath());
      ExifInterface exif = new ExifInterface(f.getPath());
      int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
              ExifInterface.ORIENTATION_NORMAL);

      int angle = 0;

      if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
        angle = 90;
      } else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
        angle = 180;
      } else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
        angle = 270;
      }

      Matrix mat = new Matrix();
      mat.postRotate(angle);

      Bitmap bmp = BitmapFactory.decodeStream(new FileInputStream(f), null,
              null);
      Bitmap rized = resize(bmp, WIDTH, HEIGHT);
      bmp = null;
      Bitmap correctBmp = Bitmap.createBitmap(rized, 0, 0, rized.getWidth(),
              rized.getHeight(), mat, true);
      rized = null;
      System.gc();
      return correctBmp;
    } catch (IOException e) {
      Log.w(ImageHelper.class.getSimpleName(), "Error IOException in " +
              "setting image");
      e.printStackTrace();
      return null;
    } catch (OutOfMemoryError oom) {
      Log.w(ImageHelper.class.getSimpleName(),
              "Error OutOfMemoryError in setting image");
      oom.printStackTrace();
      return null;
    }
  }


  static public Bitmap resize(Bitmap bitmap, int x, int y) {
    return Bitmap.createScaledBitmap(bitmap, x, y, true);
  }

  private boolean store(String filename, String path) {

    File sdIconStorageDir = new File(path);

    //create storage directories, if they don't exist
    sdIconStorageDir.mkdirs();

    try {
      String filePath = sdIconStorageDir.toString() + filename;
      FileOutputStream fileOutputStream = new FileOutputStream(filePath);

      BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);

      //choose another format if PNG doesn't suit you
      getBitmap().compress(Bitmap.CompressFormat.JPEG, 100, bos);

      bos.flush();
      bos.close();

    } catch (FileNotFoundException e) {
      Log.w("TAG", "Error saving image file: " + e.getMessage());
      return false;
    } catch (IOException e) {
      Log.w("TAG", "Error saving image file: " + e.getMessage());
      return false;
    }

    return true;
  }

  public static Bitmap getImageAjustedToDensity(byte[] res, int bitmapWidthDP,
                                                int bitmapHeightDP,
                                                float density) {
    float width = bitmapWidthDP * density;
    float height = bitmapHeightDP * density;
    return decodeSampledBitmapFromResource(res, Math.round(width),
            Math.round(height));

  }

  private static Bitmap decodeSampledBitmapFromResource(byte[] res,
                                                        int reqWidth,
                                                        int reqHeight) {

    // First decode with inJustDecodeBounds=true to check dimensions
    final BitmapFactory.Options options = new BitmapFactory.Options();
    options.inJustDecodeBounds = true;
    BitmapFactory.decodeByteArray(res, 0, res.length, options);

    // Calculate inSampleSize
    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

    // Decode bitmap with inSampleSize set
    options.inJustDecodeBounds = false;
    return BitmapFactory.decodeByteArray(res, 0, res.length, options);
  }


  private static int calculateInSampleSize(
          BitmapFactory.Options options, int reqWidth, int reqHeight) {
    // Raw height and width of image
    final int height = options.outHeight;
    final int width = options.outWidth;
    int inSampleSize = 1;

    if (height > reqHeight || width > reqWidth) {

      final int halfHeight = height / 2;
      final int halfWidth = width / 2;

      // Calculate the largest inSampleSize value that is a power of 2 and
      // keeps both
      // height and width larger than the requested height and width.
      while ((halfHeight / inSampleSize) > reqHeight
              && (halfWidth / inSampleSize) > reqWidth) {
        inSampleSize *= 2;
      }
    }

    return inSampleSize;
  }


}
