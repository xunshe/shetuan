<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>社团简介内容</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/info.css">
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
	  
	
</script>
<style type="text/css">
 body,td,div
 {
   font-size:12px;
 }
</style>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<div id="middle">
	<div id="list">
		 <div class="nav">当前位置: 主页 > 社团简介 >  </div>
		 <div class="article_title"><s:property value="#attr.college.college_name" /></div>
		 <div class="article_time">
		 	创建日期：<s:property value="#attr.college.create_date.substring(0,10)" />　社团类型：<s:property value="#attr.college.college_type" />　社团社长：<s:property value="#attr.college.real_name" />&nbsp;
		 	<a style="font-size:14px" href="javascript:void(0)" id="college_<s:property value='#attr.college.college_id' />" >&lt;&lt;申请加入&gt;&gt;</a>
		 </div>
		 <s:if test="#attr.college.college_pic!=null && #attr.college.college_pic!=''">
		 <div class="article_pic">
			<img src='images/sttp/<s:property value="#attr.college.college_pic" />'/>
		 </div>
		 </s:if>
		 <div class="article_con"><s:property value="#attr.college.college_noteShow" escape="false"  /></div>
	</div>
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	var user_id = '${userFront.user_id}';
	$("a[id^='college']").bind('click',function(){
		var college_id = $(this).attr('id').split('_')[1];
		if(user_id==null || user_id==""){
			alert("申请加入社团请先登录！");
			return;
		}
		window.location.href="page_joinMemberShow.action?paramsCollege.college_id="+college_id;
	});
});
</script>
</body>
</html>