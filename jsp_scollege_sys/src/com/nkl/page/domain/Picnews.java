package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;

public class Picnews extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 4589661923891945141L;
	private int picnews_id; // 
	private String picnews_title; // 
	private String picnews_picture; // 
	private String picnews_content; // 
	private String picnews_admin; // 
	private String picnews_date; // 
	private int picnews_number; // 

	public void setPicnews_id(int picnews_id){
		this.picnews_id=picnews_id;
	}

	public int getPicnews_id(){
		return picnews_id;
	}

	public void setPicnews_title(String picnews_title){
		this.picnews_title=picnews_title;
	}

	public String getPicnews_title(){
		return picnews_title;
	}

	public void setPicnews_picture(String picnews_picture){
		this.picnews_picture=picnews_picture;
	}

	public String getPicnews_picture(){
		return picnews_picture;
	}

	public void setPicnews_content(String picnews_content){
		this.picnews_content=picnews_content;
	}

	public String getPicnews_contentShow(){
		if (!StringUtil.isEmptyString(picnews_content)) {
			return Transcode.htmlDiscode(picnews_content);
		}
		return picnews_content;
	}
	
	public String getPicnews_content(){
		return picnews_content;
	}

	public void setPicnews_admin(String picnews_admin){
		this.picnews_admin=picnews_admin;
	}

	public String getPicnews_admin(){
		return picnews_admin;
	}

	public void setPicnews_date(String picnews_date){
		this.picnews_date=picnews_date;
	}

	public String getPicnews_date(){
		return picnews_date;
	}

	public void setPicnews_number(int picnews_number){
		this.picnews_number=picnews_number;
	}

	public int getPicnews_number(){
		return picnews_number;
	}

}
