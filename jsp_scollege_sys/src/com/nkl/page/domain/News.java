package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;

public class News extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -6104183279189257224L;
	private int news_id; // 
	private int user_id; // 
	private String news_title; // 
	private String news_content; // 
	private String news_picture; // 
	private String news_date; // 
	private int news_type; // 1:社团新闻 2：校园新闻
	private int news_flag; // 1：待审批 2：审批通过
	private String ids; // 

	public String getNews_flagDesc(){
		switch (news_flag) {
		case 1:
			return "待审批";
		case 2:
			return "审批通过";
		default:
			return "";
		}
	}
	
	public String getNews_typeDesc(){
		switch (news_type) {
		case 1:
			return "社团新闻";
		case 2:
			return "校园新闻";
		default:
			return "";
		}
	}
	
	public void setNews_id(int news_id){
		this.news_id=news_id;
	}

	public int getNews_id(){
		return news_id;
	}

	public void setNews_title(String news_title){
		this.news_title=news_title;
	}

	public String getNews_title(){
		return news_title;
	}

	public void setNews_content(String news_content){
		this.news_content=news_content;
	}

	public String getNews_contentShow(){
		if (!StringUtil.isEmptyString(news_content)) {
			return Transcode.htmlDiscode(news_content);
		}
		return news_content;
	}
	
	public String getNews_content(){
		return news_content;
	}

	public void setNews_picture(String news_picture){
		this.news_picture=news_picture;
	}

	public String getNews_picture(){
		return news_picture;
	}

	public void setNews_type(int news_type){
		this.news_type=news_type;
	}

	public int getNews_type(){
		return news_type;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getNews_date() {
		return news_date;
	}

	public void setNews_date(String news_date) {
		this.news_date = news_date;
	}

	public int getNews_flag() {
		return news_flag;
	}

	public void setNews_flag(int news_flag) {
		this.news_flag = news_flag;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

}
