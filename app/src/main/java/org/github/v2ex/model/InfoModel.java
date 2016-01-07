package org.github.v2ex.model;

/**
 * 社区基础信息
 * Created by syxc on 1/5/16.
 *
 * @see <p>http://www.v2ex.com/api/site/info.json<p/>
 */
public class InfoModel {

  public String title;
  public String slogan;
  public String description;
  public String domain;

  public InfoModel(String title, String slogan, String description, String domain) {
    this.title = title;
    this.slogan = slogan;
    this.description = description;
    this.domain = domain;
  }
}
