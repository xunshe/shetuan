<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>图片新闻内容</title>
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
		 <div class="nav">当前位置: 主页 > 图片新闻>  </div>
		 <div class="article_title"><s:property value="#attr.picnews.picnews_title" /></div>
		 <div class="article_time">发布时间：<s:property value="#attr.picnews.picnews_date.substring(0,19)" />　发布人：<s:property value="#attr.picnews.picnews_admin" /></div>
		 <div class="article_pic">
			<img src='images/hdtp/<s:property value="#attr.picnews.picnews_picture" />'/>
		 </div>
		 <div class="article_con"><s:property value="#attr.picnews.picnews_contentShow" escape="false" /></div>
	</div>
</div>
<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>