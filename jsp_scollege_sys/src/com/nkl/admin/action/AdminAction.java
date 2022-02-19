package com.nkl.admin.action;

import java.util.Date;
import java.util.List;
import com.nkl.admin.manager.AdminManager;
import com.nkl.common.action.BaseAction;
import com.nkl.common.util.DateUtil;
import com.nkl.common.util.Param;
import com.nkl.page.domain.Activity;
import com.nkl.page.domain.College;
import com.nkl.page.domain.Equip;
import com.nkl.page.domain.Member;
import com.nkl.page.domain.News;
import com.nkl.page.domain.Picnews;
import com.nkl.page.domain.Sblog;
import com.nkl.page.domain.User;

public class AdminAction  extends BaseAction {

	private static final long serialVersionUID = 1L;
	AdminManager adminManager = new AdminManager();

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
	
	/**
	 * @Title: saveAdmin
	 * @Description: 保存修改个人信息
	 * @return String
	 */
	public String saveAdmin(){
		try {
			//验证用户会话是否失效
			if (!validateAdmin()) {
				return "loginTip";
			}
			 //保存修改个人信息
			adminManager.updateUser(paramsUser);
			//更新session
			User admin = new User();
			admin.setUser_id(paramsUser.getUser_id());
			admin = adminManager.getUser(admin);
			Param.setSession("admin", admin);
			
		} catch (Exception e) {
			setErrorTip("编辑异常", "modifyInfo.jsp");
		}
		setSuccessTip("编辑成功", "modifyInfo.jsp");
		return "infoTip";
	}
	
	/**
	 * @Title: saveAdminPass
	 * @Description: 保存修改个人密码
	 * @return String
	 */
	public String saveAdminPass(){
		try {
			//验证用户会话是否失效
			if (!validateAdmin()) {
				return "loginTip";
			}
			 //保存修改个人密码
			adminManager.updateUser(paramsUser);
			//更新session
			User admin = (User)Param.getSession("admin");
			if (admin!=null) {
				admin.setUser_pass(paramsUser.getUser_pass());
				Param.setSession("admin", admin);
			}
			
		} catch (Exception e) {
			setErrorTip("修改异常", "modifyPwd.jsp");
		}
		setSuccessTip("修改成功", "modifyPwd.jsp");
		return "infoTip";
	}
	
	/**
	 * @Title: listUsers
	 * @Description: 查询用户
	 * @return String
	 */
	public String listUsers(){
		try {
			if (paramsUser==null) {
				paramsUser = new User();
			}
			//查询注册用户和社团管理员
			paramsUser.setUser_types("1,2");
			//设置分页信息
			setPagination(paramsUser);
			int[] sum={0};
			List<User> users = adminManager.listUsers(paramsUser,sum); 
			
			Param.setAttribute("users", users);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询用户异常", "main.jsp");
			return "infoTip";
		}
		
		return "userShow";
	}
	
	/**
	 * @Title: addUserShow
	 * @Description: 显示添加用户页面
	 * @return String
	 */
	public String addUserShow(){
		return "userEdit";
	}
	
	/**
	 * @Title: addUser
	 * @Description: 添加用户
	 * @return String
	 */
	public String addUser(){
		try {
			 //添加用户
			paramsUser.setUser_type(1);
			paramsUser.setReg_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			adminManager.addUser(paramsUser);
			
		} catch (Exception e) {
			setErrorTip("添加用户异常", "Admin_listUsers.action");
		}
		setSuccessTip("添加用户成功", "Admin_listUsers.action");
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editUser
	 * @Description: 编辑用户
	 * @return String
	 */
	public String editUser(){
		try {
			 //得到用户
			User user = adminManager.getUser(paramsUser);
			Param.setAttribute("user", user);
			
		} catch (Exception e) {
			setErrorTip("查询用户异常", "Admin_listUsers.action");
			return "infoTip";
		}
		
		return "userEdit";
	}
	
	/**
	 * @Title: saveUser
	 * @Description: 保存编辑用户
	 * @return String
	 */
	public String saveUser(){
		try {
			 //保存编辑用户
			adminManager.updateUser(paramsUser);
			
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("user", paramsUser);
			return "userEdit";
		}
		setSuccessTip("编辑用户成功", "Admin_listUsers.action");
		return "infoTip";
	}
	
	/**
	 * @Title: delUsers
	 * @Description: 删除用户
	 * @return String
	 */
	public String delUsers(){
		try {
			 //删除用户
			adminManager.delUsers(paramsUser);
			
		} catch (Exception e) {
			setErrorTip("删除用户异常", "Admin_listUsers.action");
		}
		setSuccessTip("删除用户成功", "Admin_listUsers.action");
		return "infoTip";
	}
	
	/**
	 * @Title: listCollegeApproves
	 * @Description: 查询社团审批
	 * @return String
	 */
	public String listCollegeApproves(){
		try {
			if (paramsCollege==null) {
				paramsCollege = new College();
			}
			//设置分页信息
			setPagination(paramsCollege);
			int[] sum={0};
			List<College> colleges = adminManager.listColleges(paramsCollege,sum); 
			
			Param.setAttribute("colleges", colleges);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询社团审批异常", "main.jsp");
			return "infoTip";
		}
		
		return "collegeApproveShow";
	}
	
	/**
	 * @Title: approveCollege
	 * @Description: 审批创建社团
	 * @return String
	 */
	public String approveCollege(){
		try {
			 //创建社团审批
			adminManager.approveCollege(paramsCollege);
		} catch (Exception e) {
			setErrorTip("审批异常", "Admin_listCollegeApproves.action");
		}
		setSuccessTip("审批成功", "Admin_listCollegeApproves.action");
		return "infoTip";
	}
	
	/**
	 * @Title: listColleges
	 * @Description: 管理查询社团
	 * @return String
	 */
	public String listColleges(){
		try {
			if (paramsCollege==null) {
				paramsCollege = new College();
			}
			//设置分页信息
			setPagination(paramsCollege);
			//社长身份设置
			User admin = (User)Param.getSession("admin");
			paramsCollege.setUser_id(admin.getUser_id());
			int[] sum={0};
			List<College> colleges = adminManager.listColleges(paramsCollege,sum); 
			
			Param.setAttribute("colleges", colleges);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询社团异常", "main.jsp");
			return "infoTip";
		}
		
		return "collegeShow";
	}
	
	 
	/**
	 * @Title: editCollege
	 * @Description: 编辑社团
	 * @return String
	 */
	public String editCollege(){
		try {
			 //得到社团
			College college = adminManager.getCollege(paramsCollege);
			Param.setAttribute("college", college);
			
		} catch (Exception e) {
			setErrorTip("查询社团异常", "Admin_listColleges.action");
			return "infoTip";
		}
		
		return "collegeEdit";
	}
	
	/**
	 * @Title: saveCollege
	 * @Description: 保存编辑社团
	 * @return String
	 */
	public String saveCollege(){
		try {
			 //保存编辑社团
			adminManager.updateCollege(paramsCollege);
			
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("college", paramsCollege);
			return "collegeEdit";
		}
		setSuccessTip("编辑社团成功", "Admin_listColleges.action");
		return "infoTip";
	}
	
	
	/**
	 * @Title: listCollegeNotes
	 * @Description: 查询社团简介
	 * @return String
	 */
	public String listCollegeNotes(){
		try {
			if (paramsCollege==null) {
				paramsCollege = new College();
			}
			//设置分页信息
			setPagination(paramsCollege);
			int[] sum={0};
			List<College> colleges = adminManager.listColleges(paramsCollege,sum); 
			
			Param.setAttribute("colleges", colleges);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询社团简介异常", "main.jsp");
			return "infoTip";
		}
		
		return "collegeNoteShow";
	}
	
	/**
	 * @Title: editCollegeNote
	 * @Description: 编辑社团简介
	 * @return String
	 */
	public String editCollegeNote(){
		try {
			 //得到社团简介
			College college = adminManager.getCollege(paramsCollege);
			Param.setAttribute("college", college);
			
		} catch (Exception e) {
			setErrorTip("查询社团简介异常", "Admin_listCollegeNotes.action");
			return "infoTip";
		}
		
		return "collegeNoteEdit";
	}
	
	/**
	 * @Title: saveCollegeNote
	 * @Description: 保存编辑社团简介
	 * @return String
	 */
	public String saveCollegeNote(){
		try {
			 //保存编辑社团简介
			adminManager.updateCollege(paramsCollege);
			
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("college", paramsCollege);
			return "collegeEdit";
		}
		setSuccessTip("编辑社团简介成功", "Admin_listCollegeNotes.action");
		return "infoTip";
	}
	
	/**
	 * @Title: delCollegeNotes
	 * @Description: 删除社团简介
	 * @return String
	 */
	public String delCollegeNotes(){
		try {
			 //删除社团
			adminManager.delColleges(paramsCollege);
			
		} catch (Exception e) {
			setErrorTip("删除社团异常", "Admin_listCollegeNotes.action");
		}
		setSuccessTip("删除社团成功", "Admin_listCollegeNotes.action");
		return "infoTip";
	}
	
	/**
	 * @Title: listMembers
	 * @Description: 查询社团成员
	 * @return String
	 */
	public String listMembers(){
		try {
			if (paramsMember==null) {
				paramsMember = new Member();
			}
			//设置分页信息
			setPagination(paramsMember);
			//社长身份设置
			User admin = (User)Param.getSession("admin");
			paramsMember.setAdmin_id(admin.getUser_id());
			int[] sum={0};
			List<Member> members = adminManager.listMembers(paramsMember,sum); 
			
			Param.setAttribute("members", members);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询社团成员异常", "main.jsp");
			return "infoTip";
		}
		
		return "memberShow";
	}
	
	/**
	 * @Title: approveMember
	 * @Description: 社团成员审批
	 * @return String
	 */
	public String approveMember(){
		try {
			 //社团成员审批
			adminManager.approveMember(paramsMember);
			
		} catch (Exception e) {
			setErrorTip("审批失败", "Admin_listMembers.action");
		}
		setSuccessTip("审批成功", "Admin_listMembers.action");
		return "infoTip";
	}
	
	/**
	 * @Title: delMembers
	 * @Description: 删除社团成员
	 * @return String
	 */
	public String delMembers(){
		try {
			 //删除社团成员
			adminManager.delMembers(paramsMember);
			
		} catch (Exception e) {
			setErrorTip("删除社团成员异常", "Admin_listMembers.action");
		}
		setSuccessTip("删除社团成员成功", "Admin_listMembers.action");
		return "infoTip";
	}
	
	/**
	 * @Title: listEquips
	 * @Description: 查询学校器材
	 * @return String
	 */
	public String listEquips(){
		try {
			if (paramsEquip==null) {
				paramsEquip = new Equip();
			}
			//设置分页信息
			setPagination(paramsEquip);
			int[] sum={0};
			List<Equip> equips = adminManager.listEquips(paramsEquip,sum); 
			
			Param.setAttribute("equips", equips);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询学校器材异常", "main.jsp");
			return "infoTip";
		}
		
		return "equipShow";
	}
	
	/**
	 * @Title: addEquipShow
	 * @Description: 显示添加学校器材页面
	 * @return String
	 */
	public String addEquipShow(){
		return "equipEdit";
	}
	
	/**
	 * @Title: addEquip
	 * @Description: 添加学校器材
	 * @return String
	 */
	public String addEquip(){
		try {
			 //添加学校器材
			adminManager.addEquip(paramsEquip);
			
		} catch (Exception e) {
			setErrorTip("添加学校器材异常", "Admin_listEquips.action");
		}
		setSuccessTip("添加学校器材成功", "Admin_listEquips.action");
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editEquip
	 * @Description: 编辑学校器材
	 * @return String
	 */
	public String editEquip(){
		try {
			 //得到学校器材
			Equip equip = adminManager.getEquip(paramsEquip);
			Param.setAttribute("equip", equip);
			
		} catch (Exception e) {
			setErrorTip("查询学校器材异常", "Admin_listEquips.action");
			return "infoTip";
		}
		
		return "equipEdit";
	}
	
	/**
	 * @Title: saveEquip
	 * @Description: 保存编辑学校器材
	 * @return String
	 */
	public String saveEquip(){
		try {
			 //保存编辑学校器材
			adminManager.updateEquip(paramsEquip);
			
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("equip", paramsEquip);
			return "equipEdit";
		}
		setSuccessTip("编辑学校器材成功", "Admin_listEquips.action");
		return "infoTip";
	}
	
	/**
	 * @Title: delEquips
	 * @Description: 删除学校器材
	 * @return String
	 */
	public String delEquips(){
		try {
			 //删除学校器材
			adminManager.delEquips(paramsEquip);
			
		} catch (Exception e) {
			setErrorTip("删除学校器材异常", "Admin_listEquips.action");
		}
		setSuccessTip("删除学校器材成功", "Admin_listEquips.action");
		return "infoTip";
	}
	
	/**
	 * @Title: listPicnewss
	 * @Description: 查询图片新闻资讯
	 * @return String
	 */
	public String listPicnewss(){
		try {
			//查询图片新闻资讯
			if (paramsPicnews==null) {
				paramsPicnews = new Picnews();
			}
			//设置分页信息
			setPagination(paramsPicnews);
			int[] sum = {0};
			List<Picnews> picnewss = adminManager.listPicnewss(paramsPicnews,sum); 
			
			Param.setAttribute("picnewss", picnewss);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询图片新闻资讯异常", "main.jsp");
			return "infoTip";
		}
		
		return "picnewsShow";
	}
	
	/**
	 * @Title: editPicnews
	 * @Description: 编辑图片新闻资讯
	 * @return String
	 */
	public String editPicnews(){
		try {
			 //得到图片新闻资讯
			Picnews picnews = adminManager.getPicnews(paramsPicnews);
			Param.setAttribute("picnews", picnews);
			
		} catch (Exception e) {
			setErrorTip("查询图片新闻资讯异常", "Admin_listPicnewss.action");
			return "infoTip";
		}
		
		return "picnewsEdit";
	}
	
	/**
	 * @Title: savePicnews
	 * @Description: 保存编辑图片新闻资讯
	 * @return String
	 */
	public String savePicnews(){
		try {
			 //保存编辑图片新闻资讯
			adminManager.updatePicnews(paramsPicnews);
			
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("picnews", paramsPicnews);
			return "picnewsEdit";
		}
		setSuccessTip("编辑成功", "Admin_listPicnewss.action");
		return "infoTip";
	}
	
	
	/**
	 * @Title: listActivitys
	 * @Description: 查询活动消息
	 * @return String
	 */
	public String listActivitys(){
		try {
			//查询活动消息
			if (paramsActivity==null) {
				paramsActivity = new Activity();
			}
			//设置分页信息
			setPagination(paramsActivity);
			//用户身份判断
			User admin = (User) Param.getSession("admin");
			if (admin.getUser_type()==2) {
				//社团管理员
				paramsActivity.setUser_id(admin.getUser_id());
			}
			int[] sum={0};
			List<Activity> activitys = adminManager.listActivitys(paramsActivity,sum); 
			
			Param.setAttribute("activitys", activitys);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询活动消息异常", "main.jsp");
			return "infoTip";
		}
		
		return "activityShow";
	}
	
	/**
	 * @Title: approveActivity
	 * @Description: 社团活动审批
	 * @return String
	 */
	public String approveActivity(){
		try {
			//社团活动审批
			adminManager.approveActivity(paramsActivity);
			
		} catch (Exception e) {
			setErrorTip("审批异常", "Admin_listActivitys.action");
		}
		setSuccessTip("审批成功", "Admin_listActivitys.action");
		return "infoTip";
	}
	
	/**
	 * @Title: addActivityShow
	 * @Description: 显示添加活动消息页面
	 * @return String
	 */
	public String addActivityShow(){
		//查询学校器材
		Equip equip = new Equip();
		equip.setStart(-1);
		List<Equip> equips = adminManager.listEquips(equip, null);
		StringBuilder sBuilder = new StringBuilder();
		if (equips!=null && equips.size()>0) {
			for (int i = 0; i < equips.size(); i++) {
				sBuilder.append(equips.get(i).getEquip_name());
				if (i!=equips.size()-1) {
					sBuilder.append("，");
				}
			}
		}
		Param.setAttribute("equips", sBuilder.toString());
		
		return "activityEdit";
	}
	
	/**
	 * @Title: addActivity
	 * @Description: 添加活动消息
	 * @return String
	 */
	public String addActivity(){
		try {
			//用户身份判断
			User admin = (User) Param.getSession("admin");
			paramsActivity.setUser_id(admin.getUser_id());
			if (admin.getUser_type()==2) {
				//社团活动需审核
				paramsActivity.setActivity_flag(1);
				//社团活动
				paramsActivity.setActivity_type(1);
			}else {
				//校园活动无需审核
				paramsActivity.setActivity_flag(2);
				//校园活动
				paramsActivity.setActivity_type(2);
			}
			//添加活动消息
			adminManager.addActivity(paramsActivity);
			
		} catch (Exception e) {
			setErrorTip("添加活动消息异常", "Admin_listActivitys.action");
		}
		setSuccessTip("添加成功", "Admin_listActivitys.action");
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editActivity
	 * @Description: 编辑活动消息
	 * @return String
	 */
	public String editActivity(){
		try {
			 //得到活动消息
			Activity activity = adminManager.getActivity(paramsActivity);
			Param.setAttribute("activity", activity);
			
			//查询学校器材
			Equip equip = new Equip();
			equip.setStart(-1);
			List<Equip> equips = adminManager.listEquips(equip, null);
			StringBuilder sBuilder = new StringBuilder();
			if (equips!=null && equips.size()>0) {
				for (int i = 0; i < equips.size(); i++) {
					sBuilder.append(equips.get(i).getEquip_name());
					if (i!=equips.size()-1) {
						sBuilder.append("，");
					}
				}
			}
			Param.setAttribute("equips", sBuilder.toString());
			
		} catch (Exception e) {
			setErrorTip("查询活动消息异常", "Admin_listActivitys.action");
			return "infoTip";
		}
		
		return "activityEdit";
	}
	
	/**
	 * @Title: saveActivity
	 * @Description: 保存编辑活动消息
	 * @return String
	 */
	public String saveActivity(){
		try {
			 //保存编辑活动消息
			adminManager.updateActivity(paramsActivity);
			
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("activity", paramsActivity);
			
			//查询学校器材
			Equip equip = new Equip();
			equip.setStart(-1);
			List<Equip> equips = adminManager.listEquips(equip, null);
			StringBuilder sBuilder = new StringBuilder();
			if (equips!=null && equips.size()>0) {
				for (int i = 0; i < equips.size(); i++) {
					sBuilder.append(equips.get(i).getEquip_name());
					if (i!=equips.size()-1) {
						sBuilder.append("，");
					}
				}
			}
			Param.setAttribute("equips", sBuilder.toString());
			
			return "activityEdit";
		}
		setSuccessTip("编辑成功", "Admin_listActivitys.action");
		return "infoTip";
	}
	
	/**
	 * @Title: delActivitys
	 * @Description: 删除活动消息
	 * @return String
	 */
	public String delActivitys(){
		try {
			 //删除活动消息
			adminManager.delActivitys(paramsActivity);
			
		} catch (Exception e) {
			setErrorTip("删除活动消息异常", "Admin_listActivitys.action");
		}
		setSuccessTip("删除活动消息成功", "Admin_listActivitys.action");
		return "infoTip";
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
			//设置分页信息
			setPagination(paramsNews);
			//用户身份判断
			User admin = (User) Param.getSession("admin");
			if (admin.getUser_type()==2) {
				//社团管理员
				paramsNews.setUser_id(admin.getUser_id());
			}
			int[] sum={0};
			List<News> newss = adminManager.listNewss(paramsNews,sum); 
			Param.setAttribute("newss", newss);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询新闻资讯异常", "main.jsp");
			return "infoTip";
		}
		
		return "newsShow";
	}
	
	/**
	 * @Title: approveNews
	 * @Description: 新闻资讯审批
	 * @return String
	 */
	public String approveNews(){
		try {
			//社团活动审批
			adminManager.approveNews(paramsNews);
			
		} catch (Exception e) {
			setErrorTip("审批异常", "Admin_listNewss.action");
		}
		setSuccessTip("审批成功", "Admin_listNewss.action");
		return "infoTip";
	}
	
	/**
	 * @Title: addNewsShow
	 * @Description: 显示添加新闻资讯页面
	 * @return String
	 */
	public String addNewsShow(){
		return "newsEdit";
	}
	
	/**
	 * @Title: addNews
	 * @Description: 添加新闻资讯
	 * @return String
	 */
	public String addNews(){
		try {
			//用户身份判断
			User admin = (User) Param.getSession("admin");
			paramsNews.setUser_id(admin.getUser_id());
			if (admin.getUser_type()==2) {
				//社团新闻需审核
				paramsNews.setNews_flag(1);
				//社团新闻
				paramsNews.setNews_type(1);
			}else {
				//校园新闻无需审核
				paramsNews.setNews_flag(2);
				//校园新闻
				paramsNews.setNews_type(2);
			}
			 //添加新闻资讯
			adminManager.addNews(paramsNews);
			
		} catch (Exception e) {
			setErrorTip("添加新闻异常", "Admin_listNewss.action");
		}
		setSuccessTip("添加成功", "Admin_listNewss.action");
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editNews
	 * @Description: 编辑新闻资讯
	 * @return String
	 */
	public String editNews(){
		try {
			 //得到新闻资讯
			News news = adminManager.getNews(paramsNews);
			Param.setAttribute("news", news);
			
		} catch (Exception e) {
			setErrorTip("查询新闻异常", "Admin_listNewss.action");
			return "infoTip";
		}
		
		return "newsEdit";
	}
	
	/**
	 * @Title: saveNews
	 * @Description: 保存编辑新闻资讯
	 * @return String
	 */
	public String saveNews(){
		try {
			 //保存编辑新闻资讯
			adminManager.updateNews(paramsNews);
			
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("news", paramsNews);
			return "newsEdit";
		}
		setSuccessTip("编辑成功", "Admin_listNewss.action");
		return "infoTip";
	}
	
	/**
	 * @Title: delNewss
	 * @Description: 删除新闻资讯
	 * @return String
	 */
	public String delNewss(){
		try {
			 //删除新闻资讯
			adminManager.delNewss(paramsNews);
			
		} catch (Exception e) {
			setErrorTip("删除异常", "Admin_listNewss.action");
		}
		setSuccessTip("删除成功", "Admin_listNewss.action");
		return "infoTip";
	}
	
	/**
	 * @Title: listSblogs
	 * @Description: 查询留言
	 * @return String
	 */
	public String listSblogs(){
		try {
			//查询留言
			if (paramsSblog==null) {
				paramsSblog = new Sblog();
			}
			setPagination(paramsSblog);
			int[] sum={0};
			List<Sblog> sblogs = adminManager.listSblogs(paramsSblog,sum); 
			
			Param.setAttribute("sblogs", sblogs);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询异常", "main.jsp");
			return "infoTip";
		}
		
		return "sblogShow";
	}
	
	/**
	 * @Title: approveSblog
	 * @Description: 审批留言
	 * @return String
	 */
	public String approveSblog(){
		try {
			 //删除留言
			adminManager.approveSblog(paramsSblog);
			
		} catch (Exception e) {
			setErrorTip("审批异常", "Admin_listSblogs.action");
		}
		setSuccessTip("审批成功", "Admin_listSblogs.action");
		return "infoTip";
	}
	
	/**
	 * @Title: delSblogs
	 * @Description: 删除留言
	 * @return String
	 */
	public String delSblogs(){
		try {
			 //删除留言
			adminManager.delSblogs(paramsSblog);
			
		} catch (Exception e) {
			setErrorTip("删除异常", "Admin_listSblogs.action");
		}
		setSuccessTip("删除成功", "Admin_listSblogs.action");
		return "infoTip";
	}
	
	
	
	private boolean validateAdmin(){
		User admin = (User)Param.getSession("admin");
		if (admin!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	private void setErrorTip(String tip,String url){
		Param.setAttribute("tipType", "error");
		Param.setAttribute("tip", tip);
		Param.setAttribute("url1", url);
		Param.setAttribute("value1", "确 定");
	}
	
	private void setSuccessTip(String tip,String url){
		Param.setAttribute("tipType", "success");
		Param.setAttribute("tip", tip);
		Param.setAttribute("url1", url);
		Param.setAttribute("value1", "确 定");
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

	public User getParamsUser() {
		return paramsUser;
	}

	public void setParamsUser(User paramsUser) {
		this.paramsUser = paramsUser;
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

}
