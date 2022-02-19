package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;

public class User extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 935450826788987376L;
	private int user_id; // 
	private String user_name; // 
	private String user_pass; // 
	private String user_mail; // 
	private String nick_name; // 
	private String real_name; // 
	private int user_sex; // 1：男  2：女
	private int user_age; // 
	private String user_dept; // 
	private String reg_date; // 
	private int user_type; // 1：注册用户 2：社团管理员 3：系统管理员
	private String ids; //  
	
	private String random;
	private String user_types;

	public String getUser_typeDesc() {
		switch (user_type) {
		case 1:
			return "注册用户";
		case 2:
			return "社团管理员";
		case 3:
			return "系统管理员";
		default:
			return "";
		}
	}
	
	public String getUser_sexDesc(){
		switch (user_sex) {
		case 1:
			return "男";
		case 2:
			return "女";
		default:
			return "男";
		}
	}
	
	public void setUser_id(int user_id){
		this.user_id=user_id;
	}

	public int getUser_id(){
		return user_id;
	}

	public void setUser_name(String user_name){
		this.user_name=user_name;
	}

	public String getUser_name(){
		return user_name;
	}

	public void setUser_pass(String user_pass){
		this.user_pass=user_pass;
	}

	public String getUser_pass(){
		return user_pass;
	}

	public void setUser_mail(String user_mail){
		this.user_mail=user_mail;
	}

	public String getUser_mail(){
		return user_mail;
	}

	public void setNick_name(String nick_name){
		this.nick_name=nick_name;
	}

	public String getNick_name(){
		return nick_name;
	}

	public void setReal_name(String real_name){
		this.real_name=real_name;
	}

	public String getReal_name(){
		return real_name;
	}

	public void setUser_sex(int user_sex){
		this.user_sex=user_sex;
	}

	public int getUser_sex(){
		return user_sex;
	}

	public void setUser_age(int user_age){
		this.user_age=user_age;
	}

	public int getUser_age(){
		return user_age;
	}

	public void setReg_date(String reg_date){
		this.reg_date=reg_date;
	}

	public String getReg_date(){
		return reg_date;
	}

	public void setUser_type(int user_type){
		this.user_type=user_type;
	}

	public int getUser_type(){
		return user_type;
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

	public String getUser_dept() {
		return user_dept;
	}

	public void setUser_dept(String user_dept) {
		this.user_dept = user_dept;
	}

	public String getUser_types() {
		return user_types;
	}

	public void setUser_types(String user_types) {
		this.user_types = user_types;
	}

}
