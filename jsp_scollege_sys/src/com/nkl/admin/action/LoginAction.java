package com.nkl.admin.action;

import com.nkl.admin.manager.LoginManager;
import com.nkl.common.action.BaseAction;
import com.nkl.common.util.Param;
import com.nkl.page.domain.User;

public class LoginAction  extends BaseAction {

	private static final long serialVersionUID = 1L;
	LoginManager loginManager = new LoginManager();
	
	User params;
	String tip;

	/**
	 * @Title: InSystem
	 * @Description: 用户登录
	 * @return String
	 */
	public String InSystem(){
		try {
			//验证码验证
			String random = (String)Param.getSession("random");
			if (!random.equals(params.getRandom())) {
				tip="验证码错误";
				return "error";
			}
			
			//用户登录查询
			params.setUser_types("2,3");
			User admin = loginManager.getUser(params);
			if (admin!=null) {
				Param.setSession("admin", admin);
			}else {
				tip="用户名或密码错误";
				return "error";
			}
			
		} catch (Exception e) {
			tip="登录异常，请稍后重试";
			return "error";
		}
		
		return "success";
	}
	
	/**
	 * @Title: OutSystem
	 * @Description: 退出登录
	 * @return String
	 */
	public String OutSystem(){
		try {
			//用户查询
			User user = (User)Param.getSession("admin");
			if (user!=null) {
				//退出登录
				Param.removeSession("admin");
			}
			
		} catch (Exception e) {
			return "logout";
		}
		
		return "logout";
	}
	

	public User getParams() {
		return params;
	}

	public void setParams(User params) {
		this.params = params;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

}
