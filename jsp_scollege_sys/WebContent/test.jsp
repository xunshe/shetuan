<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="" />
<script language="javascript" type="text/javascript" src=""></script>
<script language="javascript" type="text/javascript"> 

</script>
<style type="text/css">
 body,td,div
 {
   font-size:14px;
 }
</style>
</head>
<body>
<s:iterator id="user" value="#{'user1':'aaa','user2':'bbb'}">
	${user.key}:${user.value}<br/>
</s:iterator>
${market.market_title}<br/>
<s:if test="#attr.market.market_title.length() > 2">
	<s:property value="#attr.market.market_title.substring(0,2)+'...'" /><br/>
</s:if>
<s:else>
	<s:property value="#attr.market.market_title" /><br/>
</s:else>
<s:iterator id="picnews" value="#attr.picnewss">
	<s:textfield value="%{#picnews.picnews_title}" /><br/>
</s:iterator>
</body>
</html>