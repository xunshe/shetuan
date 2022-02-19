package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;

public class Sblog extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -674161960515333295L;
	private int sblog_id; // 
	private int user_id; // 
	private String sblog_title; // 
	private String sblog_content; // 
	private String sblog_date; // 
	private int sblog_click; // 
	private String sblog_pic; // 
	private int sblog_flag; // 1：待审批 2：审批通过
	
	private String nick_name;
	private String real_name;
	private String user_name;
	private String random; // 
	private String ids; // 

	public String getSblog_flagDesc(){
		switch (sblog_flag) {
		case 1:
			return "待审批";
		case 2:
			return "审批通过";
		default:
			return "";
		}
	}
	
	public void setSblog_id(int sblog_id){
		this.sblog_id=sblog_id;
	}

	public int getSblog_id(){
		return sblog_id;
	}

	public void setUser_id(int user_id){
		this.user_id=user_id;
	}

	public int getUser_id(){
		return user_id;
	}

	public void setSblog_title(String sblog_title){
		this.sblog_title=sblog_title;
	}

	public String getSblog_title(){
		return sblog_title;
	}

	public void setSblog_content(String sblog_content){
		this.sblog_content=sblog_content;
	}

	public String getSblog_content(){
		return sblog_content;
	}

	public void setSblog_date(String sblog_date){
		this.sblog_date=sblog_date;
	}

	public String getSblog_date(){
		return sblog_date;
	}

	public void setSblog_click(int sblog_click){
		this.sblog_click=sblog_click;
	}

	public int getSblog_click(){
		return sblog_click;
	}

	public void setSblog_pic(String sblog_pic){
		this.sblog_pic=sblog_pic;
	}

	public String getSblog_pic(){
		return sblog_pic;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public int getSblog_flag() {
		return sblog_flag;
	}

	public void setSblog_flag(int sblog_flag) {
		this.sblog_flag = sblog_flag;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

}
