<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>后台管理登录</title>
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
<BODY>
<FORM id="info" name="info"  method="post" action="LoginInSystem.action">
<DIV id=UpdatePanel1>
<DIV>
<TABLE cellSpacing=0 cellPadding=0 width=900 align=center border=0>
	<TBODY>
		<TR>
			<TD style="HEIGHT: 105px"><IMG src="images/login_1.jpg" border=0></TD>
		</TR>
		<TR>
			<TD background=images/login_2.jpg height=300>
			<TABLE height=300 cellPadding=0 width=900 border=0>
			<TBODY>
				<TR>
					<TD colSpan=2 height=35></TD></TR>
				<TR>
					<TD width=360></TD>
					<TD>
					<TABLE cellSpacing=0 cellPadding=2 border=0>
					<TBODY>
						<TR>
							<TD style="HEIGHT: 28px" width=80>登 录 名：</TD>
							<TD style="HEIGHT: 28px" width=150><INPUT id="params.user_name" name="params.user_name" value="${params.user_name}" style="WIDTH: 130px" name=txtName></TD>
							<TD style="HEIGHT: 28px" width=370>
								<SPAN  id="RequiredFieldValidator1" style="FONT-WEIGHT: bold; VISIBILITY: hidden; COLOR: white">请输入登录名</SPAN>
							</TD>
						</TR>
						<TR>
							<TD style="HEIGHT: 28px">登录密码：</TD>
							<TD style="HEIGHT: 28px"><INPUT id="params.user_pass" name="params.user_pass"   style="WIDTH: 130px" 
							  type="password"></TD>
							<TD style="HEIGHT: 28px">
								<SPAN id="RequiredFieldValidator2" style="FONT-WEIGHT: bold; VISIBILITY: hidden; COLOR: white">请输入密码</SPAN>
							</TD>
						</TR>
						<TR>
							<TD style="HEIGHT: 28px">验 证 码：</TD>
							<TD style="HEIGHT: 28px">
								<INPUT id="params.random" name="params.random" style="WIDTH: 60px" name=txtcode>
								<img src="Random.jsp" width="50" valign="middle" style="cursor:pointer;vertical-align:middle" title="换一张" id="safecode" border="0" onClick="reloadcode()"/>
							</TD>
							<TD style="HEIGHT: 28px">
								<SPAN id="RequiredFieldValidator3" style="FONT-WEIGHT: bold; VISIBILITY: hidden; COLOR: white">请输入验证码</SPAN>
							</TD>
						</TR>
						<TR>
							<TD></TD>
							<TD colspan="2" id="loginTip" style="HEIGHT:22px;color:orange">${tip}</TD>
						</TR>
						<TR>
							<TD></TD>
							<TD><img id="loginInBtn"  style="border:0px;cursor:pointer" src="images/login_button.gif" /> </TD>
						</TR>
					</TBODY>
					</TABLE>
					</TD>
				</TR>
			</TBODY>
			</TABLE>
			</TD>
		</TR>
		<TR>
			<TD>
				<IMG src="images/login_3.jpg" border=0>
			</TD>
		</TR>


	</TBODY>
</TABLE>
</DIV>
</DIV>
</FORM>
<script language="javascript" type="text/javascript">
	//实现验证码点击刷新
	function reloadcode(){
		var verify=document.getElementById('safecode');
		verify.setAttribute('src','Random.jsp?'+Math.random());
	}

	$(document).ready(function(){
		var loginInBtn = $("#loginInBtn");
		var user_name = $("#params\\.user_name");
		var user_pass = $("#params\\.user_pass");
		var random = $("#params\\.random");
		var loginTip = $("#loginTip");
		
		loginInBtn.bind('click',function(){
			if(user_name.val()==''||user_pass.val()==''||random.val()==''){
				loginTip.html("用户名、密码和验证码不能为空！")
				return;
			}
			$("#info").submit();
			 
		});
	});
</script>
</BODY>
</HTML>
