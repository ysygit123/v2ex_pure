package org.github.v2ex.api;

// ------------------------------------------------------------
//
// OkHttpClientFactory
// @via spring-android
//
// ------------------------------------------------------------

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import java.util.concurrent.TimeUnit;
import org.github.v2ex.V2EXConfig;
import org.github.v2ex.api.protocol.HttpClientRequest;
import org.github.v2ex.api.protocol.HttpClientResponse;
import org.springframework.util.Assert;

import static com.squareup.okhttp.logging.HttpLoggingInterceptor.Level;

/**
 * OkHttpClientFactory
 */
final class OkHttpClientFactory implements HttpClientRequest {

  protected final OkHttpClient client;

  private final boolean defaultClient;

  /**
   * Create a factory with a default {@link OkHttpClient} instance.
   */
  public OkHttpClientFactory() {
    this.client = new OkHttpClient();
    this.defaultClient = true;
    if (V2EXConfig.DEBUG) {
      // HttpLoggingInterceptor
      HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
      logging.setLevel(Level.BODY);
      this.client.interceptors().add(logging);
    }
  }

  /**
   * Create a factory with the given {@link OkHttpClient} instance.
   *
   * @param client the client to use
   */
  public OkHttpClientFactory(OkHttpClient client) {
    Assert.notNull(client, "'client' must not be null");
    this.client = client;
    this.defaultClient = false;
  }

  /**
   * Sets the underlying read timeout in milliseconds.
   * A value of 0 specifies an infinite timeout.
   *
   * @see OkHttpClient#setReadTimeout(long, TimeUnit)
   */
  public void setReadTimeout(int readTimeout) {
    this.client.setReadTimeout(readTimeout, TimeUnit.MILLISECONDS);
  }

  /**
   * Sets the underlying write timeout in milliseconds.
   * A value of 0 specifies an infinite timeout.
   *
   * @see OkHttpClient#setWriteTimeout(long, TimeUnit)
   */
  public void setWriteTimeout(int writeTimeout) {
    this.client.setWriteTimeout(writeTimeout, TimeUnit.MILLISECONDS);
  }

  /**
   * Sets the underlying connect timeout in milliseconds.
   * A value of 0 specifies an infinite timeout.
   *
   * @see OkHttpClient#setConnectTimeout(long, TimeUnit)
   */
  public void setConnectTimeout(int connectTimeout) {
    this.client.setConnectTimeout(connectTimeout, TimeUnit.MILLISECONDS);
  }

  @Override public Request createRequest(String url) {
    return createRequestInternal(url);
  }

  private Request createRequestInternal(String url) {
    return new Request.Builder().url(url).build();
  }

  @Override public void destroy() throws Exception {
    if (this.defaultClient) {
      // Clean up the client if we created it in the constructor
      if (this.client.getCache() != null) {
        this.client.getCache().close();
      }
      this.client.getDispatcher().getExecutorService().shutdown();
    }
  }
}