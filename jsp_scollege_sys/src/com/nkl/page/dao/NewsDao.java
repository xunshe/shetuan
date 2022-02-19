package com.nkl.page.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.News;

public class NewsDao {

	public int addNews(News news, Connection conn){
		String sql = "INSERT INTO news(news_id,user_id,news_title,news_content,news_picture,news_date,news_type,news_flag) values(null,?,?,?,?,?,?,?)";
		Object[] params = new Object[] {
			news.getUser_id(),
			news.getNews_title(),
			news.getNews_content(),
			news.getNews_picture(),
			news.getNews_date(),
			news.getNews_type(),
			news.getNews_flag()

		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delNews(String news_id, Connection conn){
		String sql = "DELETE FROM news WHERE news_id=?";

		Object[] params = new Object[] { new Integer(news_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delNewss(String[] news_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <news_ids.length; i++) {
			sBuilder.append("?");
			if (i !=news_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM news WHERE news_id IN(" +sBuilder.toString()+")";

		Object[] params = news_ids;

		return BaseDao.executeUpdate(sql, params, conn);	}

	public int updateNews(News news, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE news SET news_id = " + news.getNews_id() +" ");
		if (!StringUtil.isEmptyString(news.getNews_title())) {
			sBuilder.append(" , news_title ='" + news.getNews_title() + "' ");
		}
		if (!StringUtil.isEmptyString(news.getNews_content())) {
			sBuilder.append(" , news_content ='" + news.getNews_content() + "' ");
		}
		if (!StringUtil.isEmptyString(news.getNews_picture())) {
			sBuilder.append(" , news_picture ='" + news.getNews_picture() + "' ");
		}
		if (news.getNews_flag()!=0) {
			sBuilder.append(" , news_flag =" + news.getNews_flag() + " ");
		}

		sBuilder.append(" where news_id = " + news.getNews_id() );

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);	}

	public News getNews(News news, Connection conn){
		News _news=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM news WHERE news_id = "+ news.getNews_id() );

		List<Object> list = BaseDao.executeQuery(News.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _news = (News)list.get(0);
		}
		return _news;
	}

	public List<News>  listNewss(News news, Connection conn){
		List<News> newss = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT * FROM news  WHERE 1=1");
		
		if (news.getNews_id() !=0) {
			sBuilder.append(" and news_id = " + news.getNews_id());
		}
		if (!StringUtil.isEmptyString(news.getNews_title())) {
			sBuilder.append(" and news_title like '%" + news.getNews_title() + "%'");
		}
		if (news.getNews_type() !=0) {
			sBuilder.append(" and news_type = " + news.getNews_type());
		}
		if (news.getNews_flag()!=0) {
			sBuilder.append(" and news_flag =" + news.getNews_flag() + " ");
		}
		if (news.getUser_id()!=0) {
			sBuilder.append(" and user_id =" + news.getUser_id() + " ");
		}
		
		sBuilder.append(" order by news_date desc,news_id asc) t");
		
		if (news.getStart() != -1) {
			sBuilder.append(" limit " + news.getStart() + "," + news.getLimit());
		}
		
		List<Object> list = BaseDao.executeQuery(News.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			newss = new ArrayList<News>();
			for (Object object : list) {
				newss.add((News)object);
			}
		}
		return newss;
	}
	
	public int listNewssCount(News news, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM news WHERE 1=1");
		
		if (news.getNews_id() !=0) {
			sBuilder.append(" and news_id = " + news.getNews_id());
		}
		if (!StringUtil.isEmptyString(news.getNews_title())) {
			sBuilder.append(" and news_title like '%" + news.getNews_title() + "%'");
		}
		if (news.getNews_type() !=0) {
			sBuilder.append(" and news_type = " + news.getNews_type());
		}
		if (news.getNews_flag()!=0) {
			sBuilder.append(" and news_flag =" + news.getNews_flag() + " ");
		}
		if (news.getUser_id()!=0) {
			sBuilder.append(" and user_id =" + news.getUser_id() + " ");
		}
		
		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		
		return sum;
	}

}
