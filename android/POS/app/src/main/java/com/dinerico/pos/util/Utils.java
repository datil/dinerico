package com.dinerico.pos.util;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by josephleon on 9/30/14.
 */
public class Utils {

  public static final Boolean isValidEmail(String target) {
    if (TextUtils.isEmpty(target)) {
      return false;
    } else {
      return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();

    }
  }

  public static boolean isValidString(String s) {
    return (s != null && !s.isEmpty());
  }

  public static boolean isValidFloat(float n) {
    if (n > 0)
      return true;
    return false;
  }

  public static String currencyFormatter(float number){
    return "$" + String.format(Locale.US, "%.02f", number);
  }

  public static String dateFormatter(Date date){
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    return dateFormat.format(date);
  }

}
