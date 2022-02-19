package com.nkl.page.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.Activity;

public class ActivityDao {

	public int addActivity(Activity activity, Connection conn){
		String sql = "INSERT INTO activity(activity_id,user_id,activity_title,activity_content,activity_date,activity_address,activity_equip,activity_money,activity_type,activity_flag) values(null,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[] {
			activity.getUser_id(),
			activity.getActivity_title(),
			activity.getActivity_content(),
			activity.getActivity_date(),
			activity.getActivity_address(),
			activity.getActivity_equip(),
			activity.getActivity_money(),
			activity.getActivity_type(),
			activity.getActivity_flag()

		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delActivity(String activity_id, Connection conn){
		String sql = "DELETE FROM activity WHERE activity_id=?";

		Object[] params = new Object[] { new Integer(activity_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delActivitys(String[] activity_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <activity_ids.length; i++) {
			sBuilder.append("?");
			if (i !=activity_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM activity WHERE activity_id IN(" +sBuilder.toString()+")";

		Object[] params = activity_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updateActivity(Activity activity, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE activity SET activity_id = " + activity.getActivity_id() +" ");
		if (!StringUtil.isEmptyString(activity.getActivity_title())) {
			sBuilder.append(" , activity_title ='" + activity.getActivity_title() + "' ");
		}
		if (!StringUtil.isEmptyString(activity.getActivity_content())) {
			sBuilder.append(" , activity_content ='" + activity.getActivity_content() + "' ");
		}
		if (!StringUtil.isEmptyString(activity.getActivity_date())) {
			sBuilder.append(" , activity_date ='" + activity.getActivity_date() + "' ");
		}
		if (!StringUtil.isEmptyString(activity.getActivity_address())) {
			sBuilder.append(" , activity_address ='" + activity.getActivity_address() + "' ");
		}
		if (!StringUtil.isEmptyString(activity.getActivity_equip())) {
			sBuilder.append(" , activity_equip ='" + activity.getActivity_equip() + "' ");
		}
		if (activity.getActivity_money()!=0) {
			sBuilder.append(" , activity_money =" + activity.getActivity_money() + " ");
		}
		if (activity.getActivity_flag()!=0) {
			sBuilder.append(" , activity_flag =" + activity.getActivity_flag() + " ");
		}
		
		sBuilder.append("where activity_id = " + activity.getActivity_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}

	public Activity getActivity(Activity activity, Connection conn){
		Activity _activity=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM activity WHERE 1=1");
		if (activity.getActivity_id()!=0) {
			sBuilder.append(" and activity_id = " + activity.getActivity_id() +" ");
		}

		List<Object> list = BaseDao.executeQuery(Activity.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _activity = (Activity)list.get(0);
		}
		return _activity;
	}

	public List<Activity>  listActivitys(Activity activity, Connection conn){
		List<Activity> activitys = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT * FROM activity WHERE 1=1");

		if (activity.getActivity_id()!=0) {
			sBuilder.append(" and activity_id = " + activity.getActivity_id() +" ");
		}
		if (!StringUtil.isEmptyString(activity.getActivity_title())) {
			sBuilder.append(" and activity_title like '%" + activity.getActivity_title() + "%' ");
		}
		if (!StringUtil.isEmptyString(activity.getActivity_content())) {
			sBuilder.append(" and activity_content like '%" + activity.getActivity_content() + "%' ");
		}
		if (!StringUtil.isEmptyString(activity.getActivity_date())) {
			sBuilder.append(" and activity_date ='" + activity.getActivity_date() + "' ");
		}
		if (activity.getActivity_flag()!=0) {
			sBuilder.append(" and activity_flag =" + activity.getActivity_flag() + " ");
		}
		if (activity.getActivity_type()!=0) {
			sBuilder.append(" and activity_type =" + activity.getActivity_type() + " ");
		}
		if (activity.getUser_id()!=0) {
			sBuilder.append(" and user_id =" + activity.getUser_id() + " ");
		}
		sBuilder.append(" order by activity_date desc,activity_id asc) t");

		if (activity.getStart() != -1) {
			sBuilder.append(" limit " + activity.getStart() + "," + activity.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(Activity.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			activitys = new ArrayList<Activity>();
			for (Object object : list) {
				activitys.add((Activity)object);
			}
		}
		return activitys;
	}

	public int  listActivitysCount(Activity activity, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM activity WHERE 1=1");
		if (activity.getActivity_id()!=0) {
			sBuilder.append(" and activity_id = " + activity.getActivity_id() +" ");
		}
		if (!StringUtil.isEmptyString(activity.getActivity_title())) {
			sBuilder.append(" and activity_title like '%" + activity.getActivity_title() + "%' ");
		}
		if (!StringUtil.isEmptyString(activity.getActivity_content())) {
			sBuilder.append(" and activity_content like '%" + activity.getActivity_content() + "%' ");
		}
		if (!StringUtil.isEmptyString(activity.getActivity_date())) {
			sBuilder.append(" and activity_date ='" + activity.getActivity_date() + "' ");
		}
		if (activity.getActivity_type()!=0) {
			sBuilder.append(" and activity_type =" + activity.getActivity_type() + " ");
		}
		if (activity.getActivity_flag()!=0) {
			sBuilder.append(" and activity_flag =" + activity.getActivity_flag() + " ");
		}
		if (activity.getUser_id()!=0) {
			sBuilder.append(" and user_id =" + activity.getUser_id() + " ");
		}

		if (activity.getActivity_id()!=0) {
			sBuilder.append(" and activity_id = " + activity.getActivity_id() +" ");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}

}
