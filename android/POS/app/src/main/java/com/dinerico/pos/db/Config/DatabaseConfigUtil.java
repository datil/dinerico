package com.dinerico.pos.db.Config;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

/**
* Created by josephleon on 8/15/14.
*/

/**
* DatabaseConfigUtl writes a configuration file to avoid using Annotation
* processing in runtime. This gains a
* noticable performance improvement. configuration file is written to
* /res/raw/ by default.
* More info at: http://ormlite
* //.com/javadoc/ormlite-core/doc-files/ormlite_4.html#Config-Optimization
        */

public class DatabaseConfigUtil extends OrmLiteConfigUtil {

  public static void main(String[] args) throws SQLException, IOException {
    writeConfigFile("ormlite_config.txt");
  }
}


