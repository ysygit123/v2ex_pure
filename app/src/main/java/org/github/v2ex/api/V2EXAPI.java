package org.github.v2ex.api;

/**
 * Created by syxc on 15/12/15.
 */
public interface V2EXAPI {

  /**
   * 获取社区介绍
   *
   * @param url site/info.json
   * @param callback Callback
   * @throws Exception
   */
  void fetchSiteInfo(String url, Callback callback) throws Exception;
}
