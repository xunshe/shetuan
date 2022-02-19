<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>申请加入社团</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/reg.css">
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
		 <div class="nav">当前位置: 主页 > 申请加入社团 </div>
		 <div class="list_info">
		 	 <form name="info" id="info" style="width:100%;height:300px" action="page_createCollege.action" method="post">
		 	 <input type="hidden" name="paramsMember.user_id" id="paramsMember.user_id" value='${userFront.user_id}'/>
		     <input type="hidden" name="paramsMember.college_id" id="paramsMember.college_id" value='<s:property value="#attr.college.college_id"/>'/>
			 <table class="regTable">
				<tr>
					<td class="theme" colspan="2">申请加入社团</td>
				</tr>
				<tr>
					<td align="right" width="20%"><span style="color:red">*</span> 社团名称：</td>
					<td align="left" width="80%">
						<s:property value="#attr.college.college_name"/>
					</td>
				</tr>
				<tr>
					<td align="right"><span style="color:red">*</span> 申请原因：</td>
					<td align="left">
						<textarea style="width:300px;height:80px" name="paramsMember.member_reason"  id="paramsMember.member_reason"></textarea>
					</td>
				</tr>
			    <tr> 
		          <td align="right"><font color="red">*</font> 个人爱好：</td>
		          <td>
		            <textarea style="width:300px;height:80px" name="paramsMember.member_hobby"  id="paramsMember.member_hobby"></textarea>
		          </td>
		        </tr> 
				<tr>
					<td align="center" colspan="2">
						<input type="button" id="addBtn" class="btnstyle" value="提交申请"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="reset"  class="btnstyle" value="清空"/>
					</td>
				</tr>
		 	 </table>
		 	 </form>
		 </div>
		 
	</div>
	<div id="Picture"></div>
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	var user_id = '${userFront.user_id}';
	$("#addBtn").bind("click",function(){
		if(user_id==null || user_id==""){
			alert("申请加入社团请先登录！");
			return;
		}
		if($("#paramsMember\\.member_reason").val()==''){
			alert("申请原因不能为空！");
			return;
		}
		if($("#paramsMember\\.member_hobby").val()==''){
			alert("个人爱好不能为空！");
			return;
		}
		var aQuery = $("#info").serialize();  
		$.post('page_joinMember.action',aQuery,
			function(responseObj) {
					if(responseObj.success) {	
						 alert('申请已成功提交，请等待审批！');
						 window.location.href="page_myMemberApply.action";
					}else  if(responseObj.err.msg){
						 alert('失败：'+responseObj.err.msg);
					}else{
						 alert('失败：服务器异常！');
					}	
		 },'json');
	});
});
</script>
</body>
</html>