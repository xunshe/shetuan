package com.nkl.page.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.Picnews;

public class PicnewsDao {

	public int addPicnews(Picnews picnews, Connection conn){
		String sql = "INSERT INTO picnews(picnews_id,picnews_title,picnews_picture,picnews_content,picnews_admin,picnews_date,picnews_number) values(null,?,?,?,?,?,?)";
		Object[] params = new Object[] {
			picnews.getPicnews_title(),
			picnews.getPicnews_picture(),
			picnews.getPicnews_content(),
			picnews.getPicnews_admin(),
			picnews.getPicnews_date(),
			picnews.getPicnews_number()

		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delPicnews(String picnews_id, Connection conn){
		String sql = "DELETE FROM picnews WHERE picnews_id=?";

		Object[] params = new Object[] { new Integer(picnews_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delPicnewss(String[] picnews_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <picnews_ids.length; i++) {
			sBuilder.append("?");
			if (i !=picnews_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM picnews WHERE picnews_id IN(" +sBuilder.toString()+")";

		Object[] params = picnews_ids;

		return BaseDao.executeUpdate(sql, params, conn);	}

	public int updatePicnews(Picnews picnews, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE picnews SET picnews_id = " + picnews.getPicnews_id() +" ");
		if (!StringUtil.isEmptyString(picnews.getPicnews_title())) {
			sBuilder.append(",picnews_title = '" + picnews.getPicnews_title() +"' ");
		}
		if (!StringUtil.isEmptyString(picnews.getPicnews_content())) {
			sBuilder.append(",picnews_content = '" + picnews.getPicnews_content() +"' ");
		}
		if (!StringUtil.isEmptyString(picnews.getPicnews_picture())) {
			sBuilder.append(",picnews_picture = '" + picnews.getPicnews_picture() +"' ");
		}
		sBuilder.append(",picnews_admin = '" + picnews.getPicnews_admin() +"' ");

		sBuilder.append(" where picnews_id = " + picnews.getPicnews_id());

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);	}

	public Picnews getPicnews(Picnews picnews, Connection conn){
		Picnews _picnews=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM picnews WHERE picnews_id = "+picnews.getPicnews_id());

		List<Object> list = BaseDao.executeQuery(Picnews.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _picnews = (Picnews)list.get(0);
		}
		return _picnews;
	}

	public List<Picnews>  listPicnewss(Picnews picnews, Connection conn){
		List<Picnews> picnewss = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM picnews WHERE 1=1 order by picnews_date desc");

		List<Object> list = BaseDao.executeQuery(Picnews.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			picnewss = new ArrayList<Picnews>();
			for (Object object : list) {
				picnewss.add((Picnews)object);
			}
		}
		return picnewss;
	}

}
