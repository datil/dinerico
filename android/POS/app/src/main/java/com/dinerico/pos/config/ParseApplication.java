package com.dinerico.pos.config;

import android.app.Application;

public class ParseApplication extends Application {

  private static final String APPLICATION_ID =
          "7KyKS1qISRLaIkZhdsf8zwSlhKNVWE9itWJoDsLu";
  private static final String CLIENT_KEY =
          "vnBFzyguL6HVBdLanSHl7HGowXNiFTkoYQxaUoET";

  @Override
  public void onCreate() {
    super.onCreate();

//    // Add your initialization code here
//    Parse.initialize(this, APPLICATION_ID,CLIENT_KEY);
//
//    ParseUser.enableAutomaticUser();
//    ParseACL defaultACL = new ParseACL();
//
//    // If you would like all objects to be private by default, remove this line.
//    defaultACL.setPublicReadAccess(true);
//
//    ParseACL.setDefaultACL(defaultACL, true);
  }

}
