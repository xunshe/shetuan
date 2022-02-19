package com.nkl.page.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.College;

public class CollegeDao {

	public int addCollege(College college, Connection conn){
		String sql = "INSERT INTO college(college_id,college_name,college_type,user_id,create_date,college_persons,college_money,college_pic,college_note,college_plan,college_flag) values(null,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[] {
			college.getCollege_name(),
			college.getCollege_type(),
			college.getUser_id(),
			college.getCreate_date(),
			college.getCollege_persons(),
			college.getCollege_money(),
			college.getCollege_pic(),
			college.getCollege_note(),
			college.getCollege_plan(),
			college.getCollege_flag()

		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delCollege(String college_id, Connection conn){
		String sql = "DELETE FROM college WHERE college_id=?";

		Object[] params = new Object[] { new Integer(college_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delColleges(String[] college_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <college_ids.length; i++) {
			sBuilder.append("?");
			if (i !=college_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM college WHERE college_id IN(" +sBuilder.toString()+")";

		Object[] params = college_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updateCollege(College college, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE college SET college_id = " + college.getCollege_id() +" ");
		if (!StringUtil.isEmptyString(college.getCollege_name())) {
			sBuilder.append(" , college_name ='" + college.getCollege_name() + "' ");
		}
		if (!StringUtil.isEmptyString(college.getCollege_type())) {
			sBuilder.append(" , college_type ='" + college.getCollege_type() + "' ");
		}
		if (college.getCollege_persons()!=0) {
			sBuilder.append(" , college_persons =" + college.getCollege_persons() + " ");
		}
		if (college.getCollege_money()!=0) {
			sBuilder.append(" , college_money =" + college.getCollege_money() + " ");
		}
		if (!StringUtil.isEmptyString(college.getCollege_pic())) {
			sBuilder.append(" , college_pic ='" + college.getCollege_pic() + "' ");
		}
		if (!StringUtil.isEmptyString(college.getCollege_note())) {
			sBuilder.append(" , college_note ='" + college.getCollege_note() + "' ");
		}
		if (!StringUtil.isEmptyString(college.getCollege_plan())) {
			sBuilder.append(" , college_plan ='" + college.getCollege_plan() + "' ");
		}
		if (college.getCollege_flag()!=0) {
			sBuilder.append(" , college_flag =" + college.getCollege_flag() + " ");
		}
		sBuilder.append("where college_id = " + college.getCollege_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}

	public College getCollege(College college, Connection conn){
		College _college=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT c.*,u.real_name FROM college c join user u on c.user_id=u.user_id WHERE 1=1");
		if (college.getCollege_id()!=0) {
			sBuilder.append(" and college_id = " + college.getCollege_id() +" ");
		}
		if (!StringUtil.isEmptyString(college.getCollege_name())) {
			sBuilder.append(" and college_name ='" + college.getCollege_name() + "' ");
		}

		List<Object> list = BaseDao.executeQuery(College.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _college = (College)list.get(0);
		}
		return _college;
	}

	public List<College>  listColleges(College college, Connection conn){
		List<College> colleges = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT c.*,u.real_name FROM college c join user u on c.user_id=u.user_id WHERE 1=1");

		if (college.getCollege_id()!=0) {
			sBuilder.append(" and college_id = " + college.getCollege_id() +" ");
		}
		if (!StringUtil.isEmptyString(college.getCollege_name())) {
			sBuilder.append(" and college_name like '%" + college.getCollege_name() + "%' ");
		}
		if (!StringUtil.isEmptyString(college.getCollege_type())) {
			sBuilder.append(" and college_type like '%" + college.getCollege_type() + "%' ");
		}
		if (college.getCollege_flag()!=0) {
			sBuilder.append(" and college_flag =" + college.getCollege_flag() + " ");
		}
		if (college.getUser_id()!=0) {
			sBuilder.append(" and c.user_id =" + college.getUser_id() + " ");
		}
		if (!StringUtil.isEmptyString(college.getReal_name())) {
			sBuilder.append(" and u.real_name like '%" + college.getReal_name() + "%' ");
		}
		sBuilder.append(" order by create_date desc,college_id asc) t");

		if (college.getStart() != -1) {
			sBuilder.append(" limit " + college.getStart() + "," + college.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(College.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			colleges = new ArrayList<College>();
			for (Object object : list) {
				colleges.add((College)object);
			}
		}
		return colleges;
	}

	public int  listCollegesCount(College college, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM college c join user u on c.user_id=u.user_id WHERE 1=1");

		if (college.getCollege_id()!=0) {
			sBuilder.append(" and college_id = " + college.getCollege_id() +" ");
		}
		if (!StringUtil.isEmptyString(college.getCollege_name())) {
			sBuilder.append(" and college_name like '%" + college.getCollege_name() + "%' ");
		}
		if (!StringUtil.isEmptyString(college.getCollege_type())) {
			sBuilder.append(" and college_type like '%" + college.getCollege_type() + "%' ");
		}
		if (college.getCollege_flag()!=0) {
			sBuilder.append(" and college_flag =" + college.getCollege_flag() + " ");
		}
		if (college.getUser_id()!=0) {
			sBuilder.append(" and c.user_id =" + college.getUser_id() + " ");
		}
		if (!StringUtil.isEmptyString(college.getReal_name())) {
			sBuilder.append(" and u.real_name like '%" + college.getReal_name() + "%' ");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}

}
