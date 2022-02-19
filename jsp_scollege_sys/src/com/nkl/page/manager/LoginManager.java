package com.nkl.page.manager;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.DateUtil;
import com.nkl.common.util.Md5;
import com.nkl.common.util.StringUtil;
import com.nkl.page.dao.UserDao;
import com.nkl.page.domain.User;

public class LoginManager {

	UserDao userDao = new UserDao();
	
	/**
	 * @Title: listUsers
	 * @Description: 查询用户集合
	 * @param user
	 * @return List<Picnews>
	 */
	public List<User> listUsers(User user){
		Connection conn = BaseDao.getConnection();
		
		List<User> users = userDao.listUsers(user,conn);
		
		BaseDao.closeDB(null, null, conn);
		return users;
	}
	
	/**
	 * @Title: getUser
	 * @Description: 查询用户
	 * @param user
	 * @return User
	 */
	public User getUser(User user){
		Connection conn = BaseDao.getConnection();
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		User _user = userDao.getUser(user,conn);
		
		BaseDao.closeDB(null, null, conn);
		return _user;
	}
	
	/**
	 * @Title: addUser
	 * @Description: 用户注册
	 * @return void
	 */
	public void addUser(User user) {
		Connection conn = BaseDao.getConnection();
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		user.setReg_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		user.setUser_type(1);
		userDao.addUser(user,conn);
		
		BaseDao.closeDB(null, null, conn);
	}  

}
