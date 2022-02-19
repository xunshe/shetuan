package com.nkl.page.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.User;

public class UserDao {

	public int addUser(User user, Connection conn){
		String sql = "INSERT INTO user(user_id,user_name,user_pass,user_mail,nick_name,real_name,user_sex,user_age,user_dept,reg_date,user_type) values(null,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[] {
			user.getUser_name(),
			user.getUser_pass(),
			user.getUser_mail(),
			user.getNick_name(),
			user.getReal_name(),
			user.getUser_sex(),
			user.getUser_age(),
			user.getUser_dept(),
			user.getReg_date(),
			user.getUser_type()

		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delUser(String user_id, Connection conn){
		String sql = "DELETE FROM user WHERE user_id=?";

		Object[] params = new Object[] { new Integer(user_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delUsers(String[] user_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <user_ids.length; i++) {
			sBuilder.append("?");
			if (i !=user_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM user WHERE user_id IN(" +sBuilder.toString()+")";

		Object[] params = user_ids;

		return BaseDao.executeUpdate(sql, params, conn);	}

	public int updateUser(User user, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE user SET user_id = " + user.getUser_id() +" ");
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			sBuilder.append(" ,user_pass ='" + user.getUser_pass() +"' ");
		}
		if (!StringUtil.isEmptyString(user.getUser_mail())) {
			sBuilder.append(" ,user_mail ='" + user.getUser_mail() +"' ");
		}
		if (!StringUtil.isEmptyString(user.getNick_name())) {
			sBuilder.append(" ,nick_name ='" + user.getNick_name() +"' ");
		}
		if (!StringUtil.isEmptyString(user.getReal_name())) {
			sBuilder.append(" ,real_name ='" + user.getReal_name() +"' ");
		}
		if (user.getUser_sex()!=0) {
			sBuilder.append(" ,user_sex =" + user.getUser_sex() +" ");
		}
		if (user.getUser_age()!=0) {
			sBuilder.append(" ,user_age =" + user.getUser_age() +" ");
		}
		sBuilder.append(" ,user_dept ='" + user.getUser_dept() +"' ");

		sBuilder.append(" where user_id = " + user.getUser_id());

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);	
	}
	
	public int updateUserType(User user, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE user SET user_id = " + user.getUser_id() +" ");
		if (user.getUser_type()!=0) {
			sBuilder.append(" ,user_type =" + user.getUser_type() +" ");
		}

		sBuilder.append(" where user_id = " + user.getUser_id());

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);	
	}

	public User getUser(User user, Connection conn){
		User _user=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM user WHERE 1=1");
		if (user.getUser_id()!=0) {
			sBuilder.append(" and user_id =" + user.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			sBuilder.append(" and user_pass ='" + user.getUser_pass() +"' ");
		}
		if (!StringUtil.isEmptyString(user.getUser_name())) {
			sBuilder.append(" and user_name ='" + user.getUser_name() +"' ");
		}
		if (!StringUtil.isEmptyString(user.getUser_types())) {
			sBuilder.append(" and user_type in (" + user.getUser_types() +") ");
		}
		if (user.getUser_type()!=0) {
			sBuilder.append(" and user_type =" + user.getUser_type() +" ");
		}
		
		List<Object> list = BaseDao.executeQuery(User.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _user = (User)list.get(0);
		}
		return _user;
	}

	public List<User>  listUsers(User user, Connection conn){
		List<User> users = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT * FROM user WHERE 1=1");
		if (user.getUser_id()!=0) {
			sBuilder.append(" and user_id =" + user.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(user.getUser_name())) {
			sBuilder.append(" and user_name like '%" + user.getUser_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(user.getNick_name())) {
			sBuilder.append(" and nick_name like '%" + user.getNick_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(user.getReal_name())) {
			sBuilder.append(" and real_name like '%" + user.getReal_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(user.getUser_dept())) {
			sBuilder.append(" and user_dept like '%" + user.getUser_dept() +"%' ");
		}
		if (user.getUser_type()!=0) {
			sBuilder.append(" and user_type =" + user.getUser_type() +" ");
		}
		if (!StringUtil.isEmptyString(user.getUser_types())) {
			sBuilder.append(" and user_type in (" + user.getUser_types() +") ");
		}
		
		sBuilder.append(" order by user_id asc) t");
		
		if (user.getStart() != -1) {
			sBuilder.append(" limit " + user.getStart() + "," + user.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(User.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			users = new ArrayList<User>();
			for (Object object : list) {
				users.add((User)object);
			}
		}
		return users;
	}
	
	public int listUsersCount(User user, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM user WHERE 1=1");
		if (user.getUser_id()!=0) {
			sBuilder.append(" and user_id =" + user.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(user.getUser_name())) {
			sBuilder.append(" and user_name like '%" + user.getUser_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(user.getNick_name())) {
			sBuilder.append(" and nick_name like '%" + user.getNick_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(user.getReal_name())) {
			sBuilder.append(" and real_name like '%" + user.getReal_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(user.getUser_dept())) {
			sBuilder.append(" and user_dept like '%" + user.getUser_dept() +"%' ");
		}
		if (user.getUser_type()!=0) {
			sBuilder.append(" and user_type =" + user.getUser_type() +" ");
		}
		if (!StringUtil.isEmptyString(user.getUser_types())) {
			sBuilder.append(" and user_type in (" + user.getUser_types() +") ");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}

}
