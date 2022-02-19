package com.nkl.page.manager;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.DateUtil;
import com.nkl.common.util.Md5;
import com.nkl.common.util.StringUtil;
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

public class IndexManager {

	ActivityDao activityDao = new ActivityDao();
	CollegeDao collegeDao = new CollegeDao();
	EquipDao equipDao = new EquipDao();
	MemberDao memberDao = new MemberDao();
	NewsDao newsDao = new NewsDao();
	PicnewsDao picnewsDao = new PicnewsDao();
	SblogDao sblogDao = new SblogDao();
	UserDao userDao = new UserDao();
	
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
	 * @Description: 社团详情查询
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
	 * @Title: createCollege
	 * @Description:申请创建社团
	 * @param college
	 * @return void
	 */
	public void  createCollege(College college){
		Connection conn = BaseDao.getConnection();
		//申请时间
		college.setCreate_date(DateUtil.dateToDateString(new Date(),"yyyy-MM-dd"));
		//审批标志
		college.setCollege_flag(1);
		collegeDao.addCollege(college, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: joinMember
	 * @Description:申请加入社团
	 * @param Member
	 * @return void
	 */
	public void  joinMember(Member member){
		Connection conn = BaseDao.getConnection();
		//申请时间
		member.setReg_date(DateUtil.dateToDateString(new Date()));
		//审批标志
		member.setMember_flag(1);
		memberDao.addMember(member, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listMembers
	 * @Description: 社员加入查询
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
	 * @Title: getMember
	 * @Description: 查询社团成员
	 * @param member
	 * @return Member
	 */
	public Member  getMember(Member member){
		Connection conn = BaseDao.getConnection();
		Member _member = memberDao.getMember(member, conn);
		BaseDao.closeDB(null, null, conn);
		return _member;
	}
	
	/**
	 * @Title: listEquip
	 * @Description: 查询学校器材
	 * @param equip
	 * @return List<Equip>
	 */
	public List<Equip>  listEquip(Equip equip){
		Connection conn = BaseDao.getConnection();
		
		List<Equip> equips = equipDao.listEquips(equip,conn);
		
		BaseDao.closeDB(null, null, conn);
		return equips;
	}
	
	/**
	 * @Title: listPicnews
	 * @Description: 查询图片新闻
	 * @param picnews
	 * @return List<Picnews>
	 */
	public List<Picnews>  listPicnews(Picnews picnews){
		Connection conn = BaseDao.getConnection();
		
		List<Picnews> picnewss = picnewsDao.listPicnewss(picnews,conn);
		
		BaseDao.closeDB(null, null, conn);
		return picnewss;
	}
	
	/**
	 * @Title: getPicnews
	 * @Description: 图片新闻详情
	 * @param picnews
	 * @return Picnews
	 */
	public Picnews  getPicnews(Picnews picnews){
		Connection conn = BaseDao.getConnection();
		
		Picnews _picnews = picnewsDao.getPicnews(picnews,conn);
		
		BaseDao.closeDB(null, null, conn);
		return _picnews;
	}

	
	/**
	 * @Title: listNewss
	 * @Description: 查询1:社团新闻 2：校园新闻
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
	 * @Description: 查询详情  1:社团新闻 2：校园新闻
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
	 * @Title: listActivitys
	 * @Description: 查询活动消息
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
	 * @Description: 查询活动消息详情
	 * @param activity
	 * @return Activity
	 */
	public Activity  getActivity(Activity activity){
		Connection conn = BaseDao.getConnection();
		Activity _activity = activityDao.getActivity(activity,conn);
		BaseDao.closeDB(null, null, conn);
		return _activity;
	}
	
	/**
	 * @Title: listSblogs
	 * @Description: 查询留言信息
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
	 * @Title: addSblog
	 * @Description: 新增留言
	 * @param sblog
	 * @return void
	 */
	public void  addSblog(Sblog sblog){
		Connection conn = BaseDao.getConnection();
		sblog.setSblog_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));//留言时间
		sblog.setSblog_flag(1);//待审核
		sblogDao.addSblog(sblog, conn);
		BaseDao.closeDB(null, null, conn);
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
	
}
