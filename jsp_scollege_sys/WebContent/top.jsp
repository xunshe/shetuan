<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<div id="login">
	<div id="infoField" <s:if test="#attr.userFront==null">style="display:none;"</s:if>>
		<s:property value="#attr.userFront.nick_name"/>，欢迎您登录！
		<input type="button" id="selfCenterBtn" class="btnstyle" value="个人中心"/>
		<input type="button" id="loginOutBtn" class="btnstyle" value="退出"/>
	</div>
	<div id="loginField" <s:if test="#attr.Front!=null">style="display:none;"</s:if>>
		用户名：<input type="text" id="user_name" class="inputstyle" name="user_name"  style="width:100px"/>
		密码：<input type="password" id="user_pass" class="inputstyle" name="user_pass"  style="width:100px"/>
		<input type="button" id="loginInBtn" class="btnstyle" value="登录"/>
		<input type="button" id="regBtn" class="btnstyle" value="注册"/>
		<a target="_blank" href="admin/login.jsp">点此进入管理后台登录</a>
	</div>
	
</div>
<div id="top"></div>
<div id="navMenu">
	<ul>
	  <li style="background-image:none"><a href="index.jsp">  首　页</a></li>
	  <li><a href="page_listColleges.action">社团简介</a></li>
	  <li><a href="page_listNewss.action">新闻资讯</a></li>
	  <li><a href="page_listActivitys.action">活动消息</a></li>
	  <li><a href='page_listSblogs.action'>留言板</a></li>
	  <li><a href='page_createCollegeShow.action'>创建社团</a></li>
	  <li><a href='page_reg.action'>用户注册</a></li>
	</ul>
</div>
<script type="text/javascript" src="js/login.js"></script>