package org.github.v2ex.api.protocol;

import com.squareup.okhttp.Request;

/**
 * Created by syxc on 1/6/16.
 */
public interface HttpClientRequest {
  Request createRequest(String url);
  void destroy() throws Exception;
}
