package com.nkl.page.action;

import com.nkl.common.action.BaseAction;
import com.nkl.common.util.Param;
import com.nkl.page.domain.User;
import com.nkl.page.manager.LoginManager;

public class LoginAction  extends BaseAction {

	private static final long serialVersionUID = 1L;
	LoginManager loginManager = new LoginManager();
	
	User params;

	/**
	 * @Title: InSystem
	 * @Description: 用户登录
	 * @return String
	 */
	public String InSystem(){
		try {
			//用户登录查询
			params.setUser_types("1,2");
			User user = loginManager.getUser(params);
			if (user!=null) {
				Param.setSession("userFront", user);
				setResult("nick_name", user.getNick_name());
			}else {
				setErrorReason("用户名或密码错误");
				return "error";
			}
			
		} catch (Exception e) {
			setErrorReason("登录异常，请稍后重试");
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
			//用户登录查询
			User user = (User)Param.getSession("userFront");
			if (user!=null) {
				Param.removeSession("userFront");
			}
			
		} catch (Exception e) {
			setErrorReason("退出异常，请稍后重试");
			return "error";
		}
		
		return "success";
	}
	
	/**
	 * @Title: RegSystem
	 * @Description: 用户注册
	 * @return String
	 */
	public String RegSystem(){
		try {
			//查询验证码
			String random = (String)Param.getSession("random");
			if (!random.equals(params.getRandom())) {
				setErrorReason("验证码不正确");
				return "error";
			}
			
			//查询用户名是否被占用
			User user = new User();
			user.setUser_name(params.getUser_name());
			User user_temp = loginManager.getUser(user);
			if (user_temp!=null) {
				setErrorReason("注册失败，用户名已被注册："+params.getUser_name());
				return "error";
			}
			
			//添加用户入库
			loginManager.addUser(params);
			
		} catch (Exception e) {
			setErrorReason("注册异常，请稍后重试");
			return "error";
		}
		
		return "success";
	}

	public User getParams() {
		return params;
	}

	public void setParams(User params) {
		this.params = params;
	}

}
