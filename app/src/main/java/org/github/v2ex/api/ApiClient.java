package org.github.v2ex.api;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
import org.github.v2ex.V2EXConfig;
import org.github.v2ex.model.InfoModel;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Http request layer
 * Created by syxc on 15/12/15.
 */
public final class ApiClient implements V2EXApi {

  private static ApiClient instance = null;

  // Moshi
  private static final Moshi moshi = new Moshi.Builder().build();

  private ApiClient() {
  }

  public static ApiClient instance() {
    if (instance == null) {
      synchronized (ApiClient.class) {
        if (instance == null) {
          instance = new ApiClient();
        }
      }
    }
    return instance;
  }

  OkHttpClient getClient() {
    return OkHttpUtil.getClient();
  }

  Request getRequest(String url) {
    return OkHttpUtil.createRequest(url);
  }

  public void destroy() {
    try {
      OkHttpUtil.destroy();
    } catch (Exception e) {
      if (V2EXConfig.DEBUG) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 获取社区介绍
   *
   * @param url site/info.json
   * @param callback Callback
   */
  @Override public void fetchSiteInfo(String url, final Callback<InfoModel> callback)
      throws Exception {
    if (url == null) {
      url = Api.SITE_INFO.raw();
    }

    RxOkHttp.request(getClient(), getRequest(url))
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<Response>() {
          @Override public void onCompleted() {
            // do nothing
          }

          @Override public void onError(Throwable e) {
            Timber.e(e.getMessage());
            callback.failure(e.getLocalizedMessage());
          }

          @Override public void onNext(Response response) {
            try {
              String data = response.body().string();
              Timber.i(data);
              JsonAdapter<InfoModel> jsonAdapter = moshi.adapter(InfoModel.class);
              callback.success(jsonAdapter.fromJson(data));
            } catch (IOException e) {
              e.printStackTrace();
            } finally {
              try {
                response.body().close();
              } catch (IOException e) {
                // ignore
              }
            }
          }
        });
  }
}
