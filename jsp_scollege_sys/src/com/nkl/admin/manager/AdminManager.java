package com.nkl.admin.manager;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.DateUtil;
import com.nkl.common.util.Md5;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;
import com.nkl.page.dao.ActivityDao;
import com.nkl.page.dao.CollegeDao;
import com.nkl.page.dao.EquipDao;
import com.nkl.page.dao.MemberDao;
import com.nkl.page.dao.NewsDao;
import com.nkl.page.dao.PicnewsDao;
import com.nkl.page.dao.SblogDao;
import com.nkl.page.dao.UserDao;
import com.nkl.page.domain.Activity;
import com.nkl.page.domain.College;
import com.nkl.page.domain.Equip;
import com.nkl.page.domain.Member;
import com.nkl.page.domain.News;
import com.nkl.page.domain.Picnews;
import com.nkl.page.domain.Sblog;
import com.nkl.page.domain.User;

public class AdminManager {

	ActivityDao activityDao = new ActivityDao();
	CollegeDao collegeDao = new CollegeDao();
	EquipDao equipDao = new EquipDao();
	MemberDao memberDao = new MemberDao();
	NewsDao newsDao = new NewsDao();
	PicnewsDao picnewsDao = new PicnewsDao();
	SblogDao sblogDao = new SblogDao();
	UserDao userDao = new UserDao();
	
	/**
	 * @Title: listUsers
	 * @Description: 用户查询
	 * @param user
	 * @return List<User>
	 */
	public List<User>  listUsers(User user,int[] sum){
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = userDao.listUsersCount(user, conn);
		}
		List<User> users = userDao.listUsers(user,conn);
		
		BaseDao.closeDB(null, null, conn);
		return users;
	}
	
	/**
	 * @Title: getUser
	 * @Description: 用户查询
	 * @param user
	 * @return User
	 */
	public User  getUser(User user){
		Connection conn = BaseDao.getConnection();
		User _user = userDao.getUser(user, conn);
		BaseDao.closeDB(null, null, conn);
		return _user;
	}
	 
	/**
	 * @Title: addUser
	 * @Description: 添加用户
	 * @param user
	 * @return void
	 */
	public void  addUser(User user){
		Connection conn = BaseDao.getConnection();
		userDao.addUser(user, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: updateUser
	 * @Description: 更新用户信息
	 * @param user
	 * @return void
	 */
	public void  updateUser(User user){
		Connection conn = BaseDao.getConnection();
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		userDao.updateUser(user, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: delUsers
	 * @Description: 删除用户信息
	 * @param user
	 * @return void
	 */
	public void  delUsers(User user){
		Connection conn = BaseDao.getConnection();
		userDao.delUsers(user.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listColleges
	 * @Description: 社团查询
	 * @param college
	 * @return List<College>
	 */
	public List<College>  listColleges(College college,int[] sum){
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = collegeDao.listCollegesCount(college, conn);
		}
		List<College> colleges = collegeDao.listColleges(college,conn);
		
		BaseDao.closeDB(null, null, conn);
		return colleges;
	}
	
	/**
	 * @Title: getCollege
	 * @Description: 社团查询
	 * @param college
	 * @return College
	 */
	public College  getCollege(College college){
		Connection conn = BaseDao.getConnection();
		College _college = collegeDao.getCollege(college, conn);
		BaseDao.closeDB(null, null, conn);
		return _college;
	}
	 
	/**
	 * @Title: approveCollege
	 * @Description:创建社团审批
	 * @param college
	 * @return void
	 */
	public void  approveCollege(College college){
		Connection conn = BaseDao.getConnection();
		//审批通过
		college.setCollege_flag(2);
		collegeDao.updateCollege(college, conn);
		//设置申请人为社团管理员
		User user = new User();
		user.setUser_id(college.getUser_id());
		user.setUser_type(2);
		userDao.updateUserType(user, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: updateCollege
	 * @Description: 更新社团信息
	 * @param college
	 * @return void
	 */
	public void  updateCollege(College college){
		Connection conn = BaseDao.getConnection();
		//简介内容编码
		if (!StringUtil.isEmptyString(college.getCollege_note())) {
			college.setCollege_note(Transcode.htmlEncode(college.getCollege_note()));
		}
		collegeDao.updateCollege(college, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: delColleges
	 * @Description: 删除社团信息
	 * @param college
	 * @return void
	 */
	public void  delColleges(College college){
		Connection conn = BaseDao.getConnection();
		collegeDao.delColleges(college.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listMembers
	 * @Description: 社团成员查询
	 * @param member
	 * @return List<Member>
	 */
	public List<Member>  listMembers(Member member,int[] sum){
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = memberDao.listMembersCount(member, conn);
		}
		List<Member> members = memberDao.listMembers(member,conn);
		
		BaseDao.closeDB(null, null, conn);
		return members;
	}
	
	/**
	 * @Title: approveMember
	 * @Description: 社团成员加入审批
	 * @param member
	 * @return void
	 */
	public void  approveMember(Member member){
		Connection conn = BaseDao.getConnection();
		member.setMember_flag(2);//审批通过
		memberDao.updateMember(member, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: delMembers
	 * @Description: 删除社团成员信息
	 * @param member
	 * @return void
	 */
	public void  delMembers(Member member){
		Connection conn = BaseDao.getConnection();
		memberDao.delMembers(member.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listEquips
	 * @Description: 学校器材查询
	 * @param equip
	 * @return List<Equip>
	 */
	public List<Equip>  listEquips(Equip equip,int[] sum){
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = equipDao.listEquipsCount(equip, conn);
		}
		List<Equip> equips = equipDao.listEquips(equip,conn);
		
		BaseDao.closeDB(null, null, conn);
		return equips;
	}
	
	/**
	 * @Title: getEquip
	 * @Description: 学校器材查询
	 * @param equip
	 * @return Equip
	 */
	public Equip  getEquip(Equip equip){
		Connection conn = BaseDao.getConnection();
		Equip _equip = equipDao.getEquip(equip, conn);
		BaseDao.closeDB(null, null, conn);
		return _equip;
	}
	 
	/**
	 * @Title: addEquip
	 * @Description: 添加学校器材
	 * @param equip
	 * @return void
	 */
	public void  addEquip(Equip equip){
		Connection conn = BaseDao.getConnection();
		equipDao.addEquip(equip, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: updateEquip
	 * @Description: 更新学校器材信息
	 * @param equip
	 * @return void
	 */
	public void  updateEquip(Equip equip){
		Connection conn = BaseDao.getConnection();
		equipDao.updateEquip(equip, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: delEquips
	 * @Description: 删除学校器材信息
	 * @param equip
	 * @return void
	 */
	public void  delEquips(Equip equip){
		Connection conn = BaseDao.getConnection();
		equipDao.delEquips(equip.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listPicnewss
	 * @Description: 查询图片新闻
	 * @param picnews
	 * @param sum
	 * @return List<Picnews>
	 */
	public List<Picnews>  listPicnewss(Picnews picnews,int[] sum){
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = 4;
		}
		List<Picnews> picnewss = picnewsDao.listPicnewss(picnews,conn);
		
		BaseDao.closeDB(null, null, conn);
		return picnewss;
	}
	
	/**
	 * @Title: getPicnews
	 * @Description: 查询图片新闻
	 * @param picnews
	 * @return Picnews
	 */
	public Picnews  getPicnews(Picnews picnews){
		Connection conn = BaseDao.getConnection();
		Picnews _picnews = picnewsDao.getPicnews(picnews, conn);
		BaseDao.closeDB(null, null, conn);
		return _picnews;
	}
	
	/**
	 * @Title: updatePicnews
	 * @Description: 更新图片新闻
	 * @param user
	 * @return void
	 */
	public void  updatePicnews(Picnews picnews){
		Connection conn = BaseDao.getConnection();
		//图片新闻内容编码
		picnews.setPicnews_content(Transcode.htmlEncode(picnews.getPicnews_content()));
		picnewsDao.updatePicnews(picnews,conn);
		
		BaseDao.closeDB(null, null, conn);
	}
	
	
	/**
	 * @Title: listActivitys
	 * @Description: 活动消息查询
	 * @param activity
	 * @return List<Activity>
	 */
	public List<Activity>  listActivitys(Activity activity,int[] sum){
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = activityDao.listActivitysCount(activity, conn);
		}
		List<Activity> activitys = activityDao.listActivitys(activity,conn);
		
		BaseDao.closeDB(null, null, conn);
		return activitys;
	}
	
	/**
	 * @Title: getActivity
	 * @Description: 活动消息查询
	 * @param activity
	 * @return Activity
	 */
	public Activity  getActivity(Activity activity){
		Connection conn = BaseDao.getConnection();
		Activity _activity = activityDao.getActivity(activity, conn);
		BaseDao.closeDB(null, null, conn);
		return _activity;
	}
	 
	/**
	 * @Title: addActivity
	 * @Description: 添加活动消息
	 * @param activity
	 * @return void
	 */
	public void  addActivity(Activity activity){
		Connection conn = BaseDao.getConnection();
		//活动消息内容编码后保存
		if (!StringUtil.isEmptyString(activity.getActivity_content())) {
			activity.setActivity_content(Transcode.htmlEncode(activity.getActivity_content()));
		}
		activityDao.addActivity(activity, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: approveActivity
	 * @Description: 审批活动消息
	 * @param activity
	 * @return void
	 */
	public void  approveActivity(Activity activity){
		Connection conn = BaseDao.getConnection();
		activity.setActivity_flag(2);
		activityDao.updateActivity(activity, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: updateActivity
	 * @Description: 更新活动消息信息
	 * @param activity
	 * @return void
	 */
	public void  updateActivity(Activity activity){
		Connection conn = BaseDao.getConnection();
		//活动消息内容编码后保存
		if (!StringUtil.isEmptyString(activity.getActivity_content())) {
			activity.setActivity_content(Transcode.htmlEncode(activity.getActivity_content()));
		}
		activityDao.updateActivity(activity, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: delActivitys
	 * @Description: 删除活动消息信息
	 * @param activity
	 * @return void
	 */
	public void  delActivitys(Activity activity){
		Connection conn = BaseDao.getConnection();
		activityDao.delActivitys(activity.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listNewss
	 * @Description: 新闻查询
	 * @param news
	 * @return List<News>
	 */
	public List<News>  listNewss(News news,int[] sum){
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = newsDao.listNewssCount(news, conn);
		}
		List<News> newss = newsDao.listNewss(news,conn);
		
		BaseDao.closeDB(null, null, conn);
		return newss;
	}
	
	/**
	 * @Title: getNews
	 * @Description: 新闻查询
	 * @param news
	 * @return News
	 */
	public News  getNews(News news){
		Connection conn = BaseDao.getConnection();
		News _news = newsDao.getNews(news, conn);
		BaseDao.closeDB(null, null, conn);
		return _news;
	}
	 
	/**
	 * @Title: addNews
	 * @Description: 添加新闻
	 * @param news
	 * @return void
	 */
	public void  addNews(News news){
		Connection conn = BaseDao.getConnection();
		news.setNews_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		//新闻内容编码后保存
		if (!StringUtil.isEmptyString(news.getNews_content())) {
			news.setNews_content(Transcode.htmlEncode(news.getNews_content()));
		}
		newsDao.addNews(news, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: updateNews
	 * @Description: 更新新闻信息
	 * @param news
	 * @return void
	 */
	public void  updateNews(News news){
		Connection conn = BaseDao.getConnection();
		//新闻内容编码后保存
		if (!StringUtil.isEmptyString(news.getNews_content())) {
			news.setNews_content(Transcode.htmlEncode(news.getNews_content()));
		}
		newsDao.updateNews(news, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: approveNews
	 * @Description: 审批新闻信息
	 * @param news
	 * @return void
	 */
	public void  approveNews(News news){
		Connection conn = BaseDao.getConnection();
		news.setNews_flag(2);
		newsDao.updateNews(news, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: delNewss
	 * @Description: 删除新闻信息
	 * @param news
	 * @return void
	 */
	public void  delNewss(News news){
		Connection conn = BaseDao.getConnection();
		newsDao.delNewss(news.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listSblogs
	 * @Description: 留言查询
	 * @param sblog
	 * @return List<Sblog>
	 */
	public List<Sblog>  listSblogs(Sblog sblog,int[] sum){
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = sblogDao.listSblogsCount(sblog, conn);
		}
		List<Sblog> sblogs = sblogDao.listSblogs(sblog,conn);
		
		BaseDao.closeDB(null, null, conn);
		return sblogs;
	}
	
	/**
	 * @Title: approveSblog
	 * @Description: 留言审核
	 * @param Sblog
	 * @return void
	 */
	public void  approveSblog(Sblog sblog){
		Connection conn = BaseDao.getConnection();
		sblog.setSblog_flag(2);
		sblogDao.updateSblog(sblog, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: delSblogs
	 * @Description: 删除留言
	 * @param sblog
	 * @return void
	 */
	public void  delSblogs(Sblog sblog){
		Connection conn = BaseDao.getConnection();
		sblogDao.delSblogs(sblog.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
}
