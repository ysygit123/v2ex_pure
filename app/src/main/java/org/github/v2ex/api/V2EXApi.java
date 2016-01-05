package org.github.v2ex.api;

import org.github.v2ex.model.InfoModel;

/**
 * Created by syxc on 15/12/15.
 */
public interface V2EXApi {

  /**
   * 获取社区介绍
   *
   * @param url site/info.json
   * @param callback Callback
   * @throws Exception
   */
  void fetchSiteInfo(String url, Callback<InfoModel> callback) throws Exception;
}
