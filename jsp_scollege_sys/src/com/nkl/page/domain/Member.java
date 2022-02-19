package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;

public class Member extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -5525705569313895701L;
	private int member_id; // 
	private int college_id; // 
	private int user_id; // 
	private String reg_date; // 
	private String member_reason; // 
	private String member_hobby; // 
	private int member_flag; // 1：待审批 2：审批通过

	private String college_name; // 
	private String real_name; // 
	private int admin_id; // 
	
	private String ids;
	private String random;

	public String getMember_flagDesc(){
		switch (member_flag) {
		case 1:
			return "待审批";
		case 2:
			return "审批通过";
		default:
			return "";
		}
	}
	
	public void setMember_id(int member_id){
		this.member_id=member_id;
	}

	public int getMember_id(){
		return member_id;
	}

	public void setCollege_id(int college_id){
		this.college_id=college_id;
	}

	public int getCollege_id(){
		return college_id;
	}

	public void setUser_id(int user_id){
		this.user_id=user_id;
	}

	public int getUser_id(){
		return user_id;
	}

	public void setReg_date(String reg_date){
		this.reg_date=reg_date;
	}

	public String getReg_date(){
		return reg_date;
	}

	public void setMember_reason(String member_reason){
		this.member_reason=member_reason;
	}

	public String getMember_reason(){
		return member_reason;
	}

	public void setMember_hobby(String member_hobby){
		this.member_hobby=member_hobby;
	}

	public String getMember_hobby(){
		return member_hobby;
	}

	public void setMember_flag(int member_flag){
		this.member_flag=member_flag;
	}

	public int getMember_flag(){
		return member_flag;
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

	public String getCollege_name() {
		return college_name;
	}

	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	public void setCollege_name(String college_name) {
		this.college_name = college_name;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

}
