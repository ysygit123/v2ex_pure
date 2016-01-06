package org.github.v2ex.api;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import java.util.concurrent.TimeUnit;
import org.github.v2ex.V2EXConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by syxc on 1/6/16.
 */
public final class OkHttpUtil {

  private static Logger logger = LoggerFactory.getLogger(ApiClient.class);

  private static final OkHttpClient client = new OkHttpClient();

  static {
    if (V2EXConfig.DEBUG) {
      // HttpLoggingInterceptor
      HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
      logging.setLevel(HttpLoggingInterceptor.Level.BODY);
      client.interceptors().add(logging);
    }
  }

  public static OkHttpClient getClient() {
    logger.info("OkHttpClient instance: {}", client);
    return client;
  }

  /**
   * Sets the underlying read timeout in milliseconds.
   * A value of 0 specifies an infinite timeout.
   *
   * @see OkHttpClient#setReadTimeout(long, TimeUnit)
   */
  public static void setReadTimeout(int readTimeout) {
    client.setReadTimeout(readTimeout, TimeUnit.MILLISECONDS);
  }

  /**
   * Sets the underlying write timeout in milliseconds.
   * A value of 0 specifies an infinite timeout.
   *
   * @see OkHttpClient#setWriteTimeout(long, TimeUnit)
   */
  public static void setWriteTimeout(int writeTimeout) {
    client.setWriteTimeout(writeTimeout, TimeUnit.MILLISECONDS);
  }

  /**
   * Sets the underlying connect timeout in milliseconds.
   * A value of 0 specifies an infinite timeout.
   *
   * @see OkHttpClient#setConnectTimeout(long, TimeUnit)
   */
  public static void setConnectTimeout(int connectTimeout) {
    client.setConnectTimeout(connectTimeout, TimeUnit.MILLISECONDS);
  }

  public static Request createRequest(String url) {
    return createRequestInternal(url);
  }

  private static Request createRequestInternal(String url) {
    return new Request.Builder().url(url).build();
  }

  /**
   * OkHttpClient shutdown
   *
   * @throws Exception
   */
  public static void destroy() throws Exception {
    // Clean up the client if we created it in the constructor
    if (client.getCache() != null) {
      client.getCache().close();
    }
    client.getDispatcher().getExecutorService().shutdown();
  }
}
