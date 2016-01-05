package org.github.v2ex.api;

import com.google.gson.Gson;
import com.squareup.okhttp.Response;
import java.io.IOException;
import org.github.v2ex.model.InfoModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Http request layer
 * Created by syxc on 15/12/15.
 */
public final class ApiClient implements V2EXApi {

  private static Logger logger = LoggerFactory.getLogger(ApiClient.class);

  private static ApiClient instance = null;

  // OkHttpClientFactory
  private final OkHttpClientFactory FACTORY = new OkHttpClientFactory();
  // Gson
  private static final Gson GSON = new Gson();

  protected ApiClient() {
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

  public OkHttpClientFactory getClientFactory() {
    return FACTORY;
  }

  public void destroy() {
    try {
      FACTORY.destroy();
    } catch (Exception e) {
      e.printStackTrace();
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

    RxOkHttp.request(FACTORY.client, FACTORY.createRequest(url))
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<Response>() {
          @Override public void onCompleted() {
            // do nothing
          }

          @Override public void onError(Throwable e) {
            logger.error(e.getMessage());
            callback.failure(e.getLocalizedMessage());
          }

          @Override public void onNext(Response response) {
            try {
              String data = response.body().string();
              logger.info(data);
              callback.success(GSON.fromJson(data, InfoModel.class));
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
