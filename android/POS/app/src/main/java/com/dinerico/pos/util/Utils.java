package com.dinerico.pos.util;

import android.app.Activity;
import android.app.Service;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

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

  public static boolean isValidDouble(double n) {
    if (n > 0)
      return true;
    return false;
  }

  public static String currencyFormatter(double number){
    return "$" + String.format(Locale.US, "%.02f", number);
  }

  public static String dateFormatter(Date date){
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    return dateFormat.format(date);
  }

  public static void visibleSoftKeyboard(boolean visible, View view,
                                         Activity activity){
    InputMethodManager imm = (InputMethodManager)activity.getSystemService
            (Service.INPUT_METHOD_SERVICE);
    if(visible)
      imm.showSoftInput(view, 0);
    else
      imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
  }

}
