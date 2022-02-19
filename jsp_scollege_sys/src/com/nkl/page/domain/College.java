package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;

public class College extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -2271854549272404889L;
	private int college_id; // 
	private String college_name; // 
	private String college_type; // 
	private int user_id; // 
	private String create_date; // 
	private int college_persons; // 
	private double college_money; // 
	private String college_pic; // 
	private String college_note; // 
	private String college_plan; // 
	private int college_flag; // 1：待审批 2：审批通过

	private String real_name; // 
	
	private String ids;
	private String random;
	
	public String getCollege_flagDesc(){
		switch (college_flag) {
		case 1:
			return "待审批";
		case 2:
			return "审批通过";
		default:
			return "";
		}
	}

	public void setCollege_id(int college_id){
		this.college_id=college_id;
	}

	public int getCollege_id(){
		return college_id;
	}

	public void setCollege_name(String college_name){
		this.college_name=college_name;
	}

	public String getCollege_name(){
		return college_name;
	}

	public void setCollege_type(String college_type){
		this.college_type=college_type;
	}

	public String getCollege_type(){
		return college_type;
	}

	public void setUser_id(int user_id){
		this.user_id=user_id;
	}

	public int getUser_id(){
		return user_id;
	}

	public void setCreate_date(String create_date){
		this.create_date=create_date;
	}

	public String getCreate_date(){
		return create_date;
	}

	public void setCollege_persons(int college_persons){
		this.college_persons=college_persons;
	}

	public int getCollege_persons(){
		return college_persons;
	}

	public void setCollege_money(double college_money){
		this.college_money=college_money;
	}

	public double getCollege_money(){
		return college_money;
	}

	public void setCollege_pic(String college_pic){
		this.college_pic=college_pic;
	}

	public String getCollege_pic(){
		return college_pic;
	}

	public void setCollege_note(String college_note){
		this.college_note=college_note;
	}

	public String getCollege_noteShow(){
		if (!StringUtil.isEmptyString(college_note)) {
			return Transcode.htmlDiscode(college_note);
		}
		return college_note;
	}
	
	public String getCollege_note(){
		return college_note;
	}

	public void setCollege_plan(String college_plan){
		this.college_plan=college_plan;
	}

	public String getCollege_plan(){
		if (!StringUtil.isEmptyString(college_plan)) {
			return Transcode.htmlDiscode(college_plan);
		}
		return college_plan;
	}

	public void setCollege_flag(int college_flag){
		this.college_flag=college_flag;
	}

	public int getCollege_flag(){
		return college_flag;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getIds() {
		return ids;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getRandom() {
		return random;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

}
