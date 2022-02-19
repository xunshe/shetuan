<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>申请创建社团</title>
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
		 <div class="nav">当前位置: 主页 > 申请创建社团 </div>
		 <div class="list_info">
		 	 <form name="info" id="info" style="width:100%;height:500px" action="page_createCollege.action" method="post">
		 	 <input type="hidden" name="paramsCollege.user_id" id="paramsCollege.user_id" value='${userFront.user_id}'/>
		     <input type="hidden" name="paramsCollege.college_pic" id="paramsCollege.college_pic" value=''/>
			 <table class="regTable">
				<tr>
					<td class="theme" colspan="2">申请创建社团</td>
				</tr>
				<tr>
					<td align="right" width="20%"><span style="color:red">*</span> 社团名称：</td>
					<td align="left" width="80%">
						<input type="text" name="paramsCollege.college_name" id="paramsCollege.college_name" style="width:200px;" class="inputstyle"/>
					</td>
				</tr>
				<tr>
					<td align="right"><span style="color:red">*</span> 社团类型：</td>
					<td align="left">
						<input type="text" name="paramsCollege.college_type" id="paramsCollege.college_type" style="width:200px;" class="inputstyle"/>
					</td>
				</tr>
				<tr>
					<td align="right">社团人数：</td>
					<td align="left"><input type="text" name="paramsCollege.college_persons" id="paramsCollege.college_persons" style="width:200px;" class="inputstyle"/></td>
				</tr>
				<tr>
					<td align="right">入社社费：</td>
					<td align="left"><input type="text" name="paramsCollege.college_money" id="paramsCollege.college_money" style="width:200px;" class="inputstyle"/></td>
				</tr>
				<tr>
				  <td align="right">社团图片：</td>
				  <td align="left">
				    <img id="userImg" src="images/sttp/zxtb.gif" width="70" height="80" style="border:0px;"/>
				    <span id="op" style="display:none"><img src="images/loading004.gif"  height="50" /></span>
			      </td>
			    </tr>
			    <tr>
				  <td align="right">上传图片：</td>
			      <td align="left"> 
			          <iframe name="uploadPage" src="uploadImg.jsp" width="100%" height="50" marginheight="0" marginwidth="0" scrolling="no" frameborder="0"></iframe>            
			       </td>
			    </tr>
			    <tr> 
		          <td align="right"><font color="red">*</font> 初步规划：</td>
		          <td>
		            <textarea style="width:300px;height:80px" name="paramsCollege.college_plan"  id="paramsCollege.college_plan"></textarea>
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
    var num = /^\d+$/;
	var num2 = /^\d+(\.\d+)?$/;
	$("#addBtn").bind("click",function(){
		if(user_id==null || user_id==""){
			alert("申请社团请先登录！");
			return;
		}
		if($("#paramsCollege\\.college_name").val()==''){
			alert("社团名称不能为空！");
			return;
		}
		if($("#paramsCollege\\.college_type").val()==''){
			alert("社团类型不能为空！");
			return;
		}
	 	if($("#paramsCollege\\.college_persons").val()!=''){
			if(!num.exec($("#paramsCollege\\.college_persons").val())){
				alert('社团人数必须为数字');
				return;
			}
		}else{
			$("#paramsCollege\\.college_persons").val(0);
		}
	 	if($("#paramsCollege\\.college_money").val()!=''){
			if(!num2.exec($("#paramsCollege\\.college_money").val())){
				alert('入社社费必须为数字');
				return;
			}
		}else{
			$("#paramsCollege\\.college_money").val(0);
		}
	 	if($("#paramsCollege\\.college_pic").val()==''){
			alert("社团图片不能为空！");
			return;
		}
	 	if($("#paramsCollege\\.college_plan").val()==''){
			alert("初步规划不能为空！");
			return;
		}
		var aQuery = $("#info").serialize();  
		$.post('page_createCollege.action',aQuery,
			function(responseObj) {
					if(responseObj.success) {	
						 alert('申请已成功提交，请等待审批！');
						 window.location.href="page_myCollegeApply.action";
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