package com.nkl.page.action;

import java.util.List;

import com.nkl.common.action.BaseAction;
import com.nkl.common.util.Param;
import com.nkl.page.domain.Activity;
import com.nkl.page.domain.College;
import com.nkl.page.domain.Equip;
import com.nkl.page.domain.Member;
import com.nkl.page.domain.News;
import com.nkl.page.domain.Picnews;
import com.nkl.page.domain.Sblog;
import com.nkl.page.domain.User;
import com.nkl.page.manager.IndexManager;

public class IndexAction  extends BaseAction {

	private static final long serialVersionUID = 1L;
	IndexManager indexManager = new IndexManager();

	//抓取页面参数
	Activity paramsActivity;
	College paramsCollege;
	Equip paramsEquip;
	Member paramsMember;
	News paramsNews;
	Picnews paramsPicnews;
	Sblog paramsSblog;
	User paramsUser;
	String tip;
	
	public String index(){
		try {
			//查询图片新闻
			List<Picnews> picnewss = indexManager.listPicnews(new Picnews());
			Param.setAttribute("picnewss", picnewss);
			
			//查询社团新闻资讯前9条
			News news = new News();
			news.setStart(0);
			news.setLimit(9);
			//news.setNews_type(1);
			news.setNews_flag(2);
			List<News> newss1 = indexManager.listNewss(news,null); 
			Param.setAttribute("newss1", newss1);
			
			//查询社团简介
			College college = new College();
			college.setStart(0);
			college.setLimit(10);
			college.setCollege_flag(2);
			List<College> colleges = indexManager.listColleges(college,null);
			Param.setAttribute("colleges", colleges);
			
			//查询社团活动
			Activity activity = new Activity();
			activity.setStart(0);
			activity.setLimit(5);
			activity.setActivity_type(1);
			activity.setActivity_flag(2);
			List<Activity> activitys1 = indexManager.listActivitys(activity,null);
			Param.setAttribute("activitys1", activitys1);
			
			//查询校园活动
			activity.setActivity_type(2);
			List<Activity> activitys2 = indexManager.listActivitys(activity,null);
			Param.setAttribute("activitys2", activitys2);
			
			//查询社团新闻
			news.setNews_type(1);
			news.setStart(0);
			news.setLimit(5);
			List<News> newss2 = indexManager.listNewss(news,null); 
			Param.setAttribute("newss2", newss2);
			
			//查询校园新闻
			news.setNews_type(2);
			news.setStart(0);
			news.setLimit(5);
			List<News> newss3 = indexManager.listNewss(news,null); 
			Param.setAttribute("newss3", newss3);
			
			//查询留言信息
			Sblog sblog = new Sblog();
			sblog.setStart(0);
			sblog.setLimit(5);
			sblog.setSblog_flag(2);
			List<Sblog> sblogs = indexManager.listSblogs(sblog,null); 
			Param.setAttribute("sblogs", sblogs);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "index";
	}
	
	/**
	 * @Title: queryPicnews
	 * @Description: 查询图片新闻
	 * @return String
	 */
	public String queryPicnews(){
		try {
			//查询图片新闻
			Picnews picnews = indexManager.getPicnews(paramsPicnews); 
			
			Param.setAttribute("picnews", picnews);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "tpxw";
	}
	
	/**
	 * @Title: listNewss
	 * @Description: 查询新闻资讯
	 * @return String
	 */
	public String listNewss(){
		try {
			//查询新闻资讯
			if (paramsNews==null) {
				paramsNews = new News();
			}
			paramsNews.setNews_flag(2);
			setPagination(paramsNews);
			int[] sum={0};
			List<News> newss = indexManager.listNewss(paramsNews,sum); 
			
			Param.setAttribute("newss", newss);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "news";
	}
	
	/**
	 * @Title: queryNews
	 * @Description: 查询新闻资讯详情
	 * @return String
	 */
	public String queryNews(){
		try {
			//查询新闻资讯详情
			News news = indexManager.getNews(paramsNews); 
			
			Param.setAttribute("news", news);
			Param.setAttribute("news_type", paramsNews.getNews_type());
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "newsDetail";
	}

	/**
	 * @Title: listActivitys
	 * @Description: 查询活动公告
	 * @return String
	 */
	public String listActivitys(){
		try {
			//查询活动公告
			Activity activity = new Activity();
			activity.setActivity_flag(2);
			setPagination(activity);
			int[] sum={0};
			List<Activity> activitys = indexManager.listActivitys(activity,sum); 
			
			Param.setAttribute("activitys", activitys);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "activity";
	}
	
	/**
	 * @Title: queryActivity
	 * @Description: 查询活动公告详情
	 * @return String
	 */
	public String queryActivity(){
		try {
			//查询活动公告
			Activity activity = indexManager.getActivity(paramsActivity); 
			Param.setAttribute("activity", activity);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "activityDetail";
	}
	
	/**
	 * @Title: listColleges
	 * @Description: 查询社团简介
	 * @return String
	 */
	public String listColleges(){
		try {
			//查询社团简介
			if (paramsCollege==null) {
				paramsCollege = new College();
			}
			paramsCollege.setCollege_flag(2);
			setPagination(paramsCollege);
			int[] sum={0};
			List<College> colleges = indexManager.listColleges(paramsCollege,sum); 
			
			Param.setAttribute("colleges", colleges);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "college";
	}
	
	/**
	 * @Title: queryCollege
	 * @Description: 查询社团简介详情
	 * @return String
	 */
	public String queryCollege(){
		try {
			//查询社团简介详情
			College college = indexManager.getCollege(paramsCollege); 
			
			Param.setAttribute("college", college);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "collegeDetail";
	}
	
	/**
	 * @Title: createCollegeShow
	 * @Description: 申请创建社团页面
	 * @return String
	 */
	public String createCollegeShow(){
		return "collegeCreate";
	}
	
	/**
	 * @Title: createCollege
	 * @Description: 申请创建社团
	 * @return String
	 */
	public String createCollege(){
		try {
			//检查社团名字是否已经存在
			College college = indexManager.getCollege(paramsCollege); 
			if (college!=null) {
				setErrorReason("创建失败！该社团名字已经存在！");
				return "error2";
			}
			//创建社团
			indexManager.createCollege(paramsCollege);
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorReason("创建失败！后台服务器繁忙！");
			return "error2";
		}
		return "success";
	}
	
	/**
	 * @Title: joinMemberShow
	 * @Description: 申请创建社团页面
	 * @return String
	 */
	public String joinMemberShow(){
		College college = indexManager.getCollege(paramsCollege); 
		Param.setAttribute("college", college);
		
		return "memberJoin";
	}
	
	/**
	 * @Title: joinMember
	 * @Description: 申请加入社团
	 * @return String
	 */
	public String joinMember(){
		try {
			//检查社团是否已经加入
			Member member = indexManager.getMember(paramsMember); 
			if (member!=null) {
				if (member.getMember_flag()==1) {
					setErrorReason("您的申请正在审批中，请等待！");
					return "error2";
				}else {
					setErrorReason("您已经是该社团成员，无需再次申请！");
					return "error2";
				}
			}
			//申请加入社团
			indexManager.joinMember(paramsMember);
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorReason("申请失败！后台服务器繁忙！");
			return "error2";
		}
		return "success";
	}
	
	/**
	 * @Title: myCollegeApply
	 * @Description: 查询创建社团申请
	 * @return String
	 */
	public String myCollegeApply(){
		try {
			//查询社团申请
			if (paramsCollege==null) {
				paramsCollege = new College();
			}
			//设置身份为当前用户
			User userFront = (User) Param.getSession("userFront");
			paramsCollege.setUser_id(userFront.getUser_id());
			//设置分页信息
			setPagination(paramsCollege);
			int[] sum={0};
			List<College> colleges = indexManager.listColleges(paramsCollege,sum); 
			
			Param.setAttribute("colleges", colleges);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "myCollegeApply";
	}
	
	/**
	 * @Title: myMemberApply
	 * @Description: 查询加入社团申请
	 * @return String
	 */
	public String myMemberApply(){
		try {
			//查询加入社团申请
			if (paramsMember==null) {
				paramsMember = new Member();
			}
			//设置身份为当前用户
			User userFront = (User) Param.getSession("userFront");
			paramsMember.setUser_id(userFront.getUser_id());
			//设置分页信息
			setPagination(paramsMember);
			int[] sum={0};
			List<Member> members = indexManager.listMembers(paramsMember,sum); 
			
			Param.setAttribute("members", members);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "myMemberApply";
	}
	
	/**
	 * @Title: sblogs
	 * @Description: 留言板
	 * @return String
	 */
	public String listSblogs(){
		try {
			//查询留言板
			if (paramsSblog==null) {
				paramsSblog = new Sblog();
			}
			paramsSblog.setSblog_flag(2);
			setPagination(paramsSblog);
			int[] sum={0};
			List<Sblog> sblogs = indexManager.listSblogs(paramsSblog,sum); 
			
			Param.setAttribute("sblogs", sblogs);
			setTotalCount(sum[0]);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "sblog";
	}
	
	/**
	 * @Title: addSblog
	 * @Description: 新增留言
	 * @return String
	 */
	public String addSblog(){
		try {
			//验证码验证
			String random = (String)Param.getSession("random");
			if (!random.equals(paramsSblog.getRandom())) {
				setErrorReason("验证码错误！");
				return "error2";
			}
			
			//新增留言
			indexManager.addSblog(paramsSblog);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "success";
	}
	
	/**
	 * @Title: saveUserFront
	 * @Description: 保存修改个人信息
	 * @return String
	 */
	public String saveUserFront(){
		try {
			 //保存修改个人信息
			indexManager.updateUser(paramsUser);
			//更新session
			User userFront = new User();
			userFront.setUser_id(paramsUser.getUser_id());
			userFront = indexManager.getUser(userFront);
			Param.setSession("userFront", userFront);
			
		} catch (Exception e) {
			e.printStackTrace();
			tip = "修改失败";
			return "userInfo";
		}
		tip = "修改成功";
		return "userInfo";
	}
	
	/**
	 * @Title: saveUserFrontPass
	 * @Description: 保存修改个人密码
	 * @return String
	 */
	public String saveUserFrontPass(){
		try {
			 //保存修改个人密码
			indexManager.updateUser(paramsUser);
			//更新session
			User UserFront = (User)Param.getSession("UserFront");
			if (UserFront!=null) {
				UserFront.setUser_pass(paramsUser.getUser_pass());
				Param.setSession("UserFront", UserFront);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			tip = "修改失败";
			return "userPwd";
		}
		tip = "修改成功";
		return "userPwd";
	}
	
	/**
	 * @Title: reg
	 * @Description: 跳转注册页面
	 * @return String
	 */
	public String reg(){
		return "reg";
	}
	
	/**
	 * @Title: myInfo
	 * @Description: 跳转个人信息页面
	 * @return String
	 */
	public String myInfo(){
		return "userInfo";
	}
	
	/**
	 * @Title: myPwd
	 * @Description: 跳转个人密码页面
	 * @return String
	 */
	public String myPwd(){
		return "userPwd";
	}
	
	public News getParamsNews() {
		return paramsNews;
	}

	public void setParamsNews(News paramsNews) {
		this.paramsNews = paramsNews;
	}

	public Sblog getParamsSblog() {
		return paramsSblog;
	}

	public void setParamsSblog(Sblog paramsSblog) {
		this.paramsSblog = paramsSblog;
	}

	public Picnews getParamsPicnews() {
		return paramsPicnews;
	}

	public void setParamsPicnews(Picnews paramsPicnews) {
		this.paramsPicnews = paramsPicnews;
	}

	public Activity getParamsActivity() {
		return paramsActivity;
	}

	public void setParamsActivity(Activity paramsActivity) {
		this.paramsActivity = paramsActivity;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public College getParamsCollege() {
		return paramsCollege;
	}

	public void setParamsCollege(College paramsCollege) {
		this.paramsCollege = paramsCollege;
	}

	public Equip getParamsEquip() {
		return paramsEquip;
	}

	public void setParamsEquip(Equip paramsEquip) {
		this.paramsEquip = paramsEquip;
	}

	public Member getParamsMember() {
		return paramsMember;
	}

	public void setParamsMember(Member paramsMember) {
		this.paramsMember = paramsMember;
	}

	public User getParamsUser() {
		return paramsUser;
	}

	public void setParamsUser(User paramsUser) {
		this.paramsUser = paramsUser;
	}

}
