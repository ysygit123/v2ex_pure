package org.github.v2ex.api;

import android.util.Log;
import org.github.v2ex.V2EXConfig;

/**
 * Created by syxc on 15/12/15.
 */
public enum Api {

  /**
   * site/info.json
   * 社区介绍
   */
  SITE_INFO("/site/info.json");

  private final String text;

  Api(final String text) {
    this.text = text;
  }

  public String raw() {
    return this.toString();
  }

  @Override public String toString() {
    String HOST = V2EXConfig.HOST;
    if (V2EXConfig.DEBUG) {
      HOST = V2EXConfig.DEV_HOST;
    }

    Log.i("Api", "Api url: " + HOST + text);

    return HOST + text;
  }
}
