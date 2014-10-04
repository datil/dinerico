package com.dinerico.pos.util;

import android.text.TextUtils;

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

}
