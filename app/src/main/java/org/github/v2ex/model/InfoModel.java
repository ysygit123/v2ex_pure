package org.github.v2ex.model;

/**
 * 社区基础信息
 * Created by syxc on 1/5/16.
 *
 * @see <p>http://www.v2ex.com/api/site/info.json<p/>
 */
public final class InfoModel {

  public final String title;
  public final String slogan;
  public final String description;
  public final String domain;

  public InfoModel(String title, String slogan, String description, String domain) {
    this.title = title;
    this.slogan = slogan;
    this.description = description;
    this.domain = domain;
  }

  @Override public String toString() {
    return String.format("title:%s,slogan:%s,description:%s,domain:%s", title, slogan, description,
        domain);
  }
}
