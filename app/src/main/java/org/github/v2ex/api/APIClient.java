package org.github.v2ex.api;

import android.util.Log;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;

/**
 * Created by syxc on 15/12/15.
 */
public class APIClient implements V2EXAPI {

  private static final String TAG = "APIClient";

  private static APIClient instance = null;

  private static OkHttpClient client = null;

  protected APIClient() {
    client = httpInstance();
  }

  public static APIClient instance() {
    if (instance == null) {
      synchronized (APIClient.class) {
        if (instance == null) {
          instance = new APIClient();
        }
      }
    }
    return instance;
  }

  /**
   * Get OkHttpClient Instance
   *
   * @return OkHttpClient
   */
  private static OkHttpClient httpInstance() {
    if (client == null) {
      synchronized (OkHttpClient.class) {
        if (client == null) {
          client = new OkHttpClient();
        }
      }
    }
    return client;
  }

  /**
   * 获取社区介绍
   *
   * @param url site/info.json
   * @param callback Callback
   */
  @Override public void fetchSiteInfo(String url, final Callback callback) throws Exception {
    if (url == null) {
      url = API.SITE_INFO.raw();
    }

    Request request = new Request.Builder().url(url).build();

    client.newCall(request).enqueue(new com.squareup.okhttp.Callback() {
      @Override public void onFailure(Request request, IOException e) {
        callback.failure("Request fail");
        e.printStackTrace();
      }

      @Override public void onResponse(Response response) throws IOException {
        if (!response.isSuccessful()) {
          throw new IOException("Unexpected code " + response);
        }
        callback.success(response.body().string());
        Log.i(TAG, "response: " + response.body().string());
      }
    });
  }
}
