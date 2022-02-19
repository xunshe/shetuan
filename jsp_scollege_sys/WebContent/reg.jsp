<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户注册</title>
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
		 <div class="nav">当前位置: 主页 > 用户注册 </div>
		 <div class="list_info">
		 	 <form name="info" id="info" style="width:100%;height:400px" action="LoginRegSystem.action" method="post">
		     <input type="hidden" name="params.user_type" id="params.user_type" value="1"/>
			 <table class="regTable">
				<tr>
					<td class="theme" colspan="2">新用户注册</td>
				</tr>
				<tr>
					<td align="right" width="20%"><span style="color:red">*</span> 用户名(学号)：</td>
					<td align="left" width="80%"><input type="text" name="params.user_name" id="params.user_name" style="width:200px;" class="inputstyle"/></td>
				</tr>
				<tr>
					<td align="right" width="20%"><span style="color:red">*</span> 密　码：</td>
					<td align="left" width="80%"><input type="password" name="params.user_pass" id="params.user_pass" style="width:200px;" class="inputstyle"/></td>
				</tr>
				<tr>
					<td align="right" width="20%"><span style="color:red">*</span> 确认密码：</td>
					<td align="left" width="80%"><input type="password" name="user_rpass" id="user_rpass" style="width:200px;" class="inputstyle"/></td>
				</tr>
				<tr>
					<td align="right" width="20%"><span style="color:red">*</span> 姓　名：</td>
					<td align="left" width="80%"><input type="text" name="params.real_name" id="params.real_name" style="width:200px;" class="inputstyle"/></td>
				</tr>
				<tr>
					<td align="right" width="20%"><span style="color:red">*</span> 昵　称：</td>
					<td align="left" width="80%"><input type="text" name="params.nick_name" id="params.nick_name" style="width:200px;" class="inputstyle"/></td>
				</tr>
				<tr>
					<td align="right" width="20%">性　别：</td>
					<td align="left" width="80%"> 
					    <input type="radio" name="params.user_sex" checked="checked" value="1" />
						男
						<input type="radio" name="params.user_sex" value="2" />
						女
					</td>
				</tr>
				<tr>
					<td align="right" width="20%">年　龄：</td>
					<td align="left" width="80%"><input type="text" name="params.user_age" id="params.user_age" style="width:200px;" class="inputstyle"/></td>
				</tr>
				<tr>
					<td align="right" width="20%">邮　箱：</td>
					<td align="left" width="80%"><input type="text" name="params.user_mail" id="params.user_mail" style="width:200px;" class="inputstyle"/></td>
				</tr>
				<tr>
					<td align="right" width="20%">所在院系：</td>
					<td align="left" width="80%"><input type="text" name="params.user_dept" id="params.user_dept" style="width:200px;" class="inputstyle"/></td>
				</tr>
				<tr>
					<td align="right" width="20%"><span style="color:red">*</span> 验证码：</td>
					<td align="left" width="80%"> 
						<input type="text" id="params.random" name="params.random" style="width:80px;" class="inputstyle"/>
						&nbsp;<img src="Random.jsp" width="50" valign="middle" style="cursor:pointer;vertical-align:middle" title="换一张" id="safecode" border="0" onClick="reloadcode()"/>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<input type="button" id="addBtn" class="btnstyle" value="提交"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
//实现验证码点击刷新
function reloadcode(){
	var verify=document.getElementById('safecode');
	verify.setAttribute('src','Random.jsp?'+Math.random());
}
$(document).ready(function(){
	var addBtn = $("#addBtn");
	var user_name = $("#params\\.user_name");
	var user_pass = $("#params\\.user_pass");
	var user_rpass = $("#user_rpass");
	var real_name = $("#params\\.real_name");
	var nick_name = $("#params\\.nick_name");
	var user_age = $("#params\\.user_age");
	var random = $("#params\\.random");
	
	var name=/^[a-z][a-z0-9_]{3,15}$/;
    var pass=/^[a-zA-Z0-9]{3,15}$/;
    var num= /^\d+$/;
	addBtn.bind("click",function(){
		
		if(user_name.val()==''||user_pass.val()==''){
			alert("用户名或密码不能为空");
			return;
		}
		if(!name.exec(user_name.val())){
			window.alert("用户名必须为字母开头 、字母数字4-16位！");
			return false;
		}
		if(!pass.exec(user_pass.val())){
			window.alert("密码必须为字母数字4-16位！");
			return false;
		}
		if(user_pass.val()!=user_rpass.val()){
			alert("两次输入密码不一致");
			return;
		}
		if(real_name.val()==''){
			alert("姓名不能为空");
			return;
		}
		if(nick_name.val()==''){
			alert("昵称不能为空");
			return;
		}
		if(user_age.val()!=''){
			if(!num.exec(user_age.val())){
				alert("年龄必须为数字");
				return;
			}
		}else{
			user_age.val(0);
		}
		if(random.val()==''){
			alert("验证码不能为空");
			return;
		}
		var aQuery = $("#info").serialize();  
		$.post('LoginRegSystem.action',aQuery,
			function(responseObj) {
					if(responseObj.success) {	
						 alert('注册成功！');
						 window.location.href="page_index.action";
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