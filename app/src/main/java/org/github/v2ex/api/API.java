package org.github.v2ex.api;

import android.util.Log;
import org.github.v2ex.BuildConfig;

/**
 * Created by syxc on 15/12/15.
 */
public enum API {

  /**
   * site/info.json
   * 社区介绍
   */
  SITE_INFO("/site/info.json");

  private final String text;

  API(final String text) {
    this.text = text;
  }

  String raw() {
    return this.toString();
  }

  @Override public String toString() {
    String HOST = "https://www.v2ex.com/api";
    if (BuildConfig.DEBUG) {
      HOST = "http://www.v2ex.com/api";
    }

    Log.i("API", "API url: " + HOST + text);

    return HOST + text;
  }
}
