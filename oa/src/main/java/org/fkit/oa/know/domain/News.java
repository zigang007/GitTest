package org.fkit.oa.know.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="oa_id_news")
public class News implements Serializable {

	/**
	 * 新闻实体
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long news_id;
	private String news_title;
	private Date create_time;
	private String news_contents;
	public Long getNews_id() {
		return news_id;
	}
	public void setNews_id(Long news_id) {
		this.news_id = news_id;
	}
	public String getNews_title() {
		return news_title;
	}
	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getNews_contents() {
		return news_contents;
	}
	public void setNews_contents(String news_contents) {
		this.news_contents = news_contents;
	}
	
}
