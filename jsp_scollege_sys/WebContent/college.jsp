<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>社团简介</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
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
		 <div class="nav">当前位置: 主页 > 社团简介 </div>
		 <div class="list_info">
			<ul>
				<s:iterator value="#attr.colleges" id="college">
				<li>
					[ <strong><s:property value="#college.college_type" /></strong> ] <s:a href="page_queryCollege.action?paramsCollege.college_id=%{#college.college_id}" target="_blank" title="%{#college.college_name}"> 
						<s:property value="#college.college_name.length()>26?#college.college_name.substring(0,26)+'...':#college.college_name"/>
					</s:a>　<span style="color:#666"><s:property value="#college.create_date.substring(0,10)"/></span>
				</li>
				<li class="li_hr">
					<hr />
				</li>
				</s:iterator>
			</ul>
		 </div>
		 <jsp:include page="page.jsp"></jsp:include>
	</div>
	<div id="Picture"></div>
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script language="javascript" type="text/javascript">
	function GoPage()
	{
	  var pagenum=document.getElementById("goPage").value;
	  var patten=/^\d+$/;
	  if(!patten.exec(pagenum))
	  {
	    alert("页码必须为大于0的数字");
	    return false;
	  }
	  window.location.href="page_listColleges.action?pageNo="+pagenum;
	}
	function ChangePage(pagenum)
	{
		window.location.href="page_listColleges.action?pageNo="+pagenum;
	}
</script>
</body>
</html>