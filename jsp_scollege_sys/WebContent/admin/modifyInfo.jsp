<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改个人信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
$(document).ready(function(){
	 var user_sex = "${admin.user_sex}";
	 $("#sex"+user_sex).attr('checked','checked');
	 
	 var num=/^\d+$/;
	 $("#saveBtn").bind('click',function(){
		if($("#paramsUser\\.real_name").val()==''){
			alert('姓名不能为空');
			return;
		}
		if($("#paramsUser\\.nick_name").val()==''){
			alert('昵称不能为空');
			return;
		}
		if($("#paramsUser\\.user_age").val()!=''){
			if(!num.exec($("#paramsUser\\.user_age").val())){
				alert('年龄必须为数字');
				return;
			}
		}else{
			$("#paramsUser\\.user_age").val(0);
		}
		
		$("#info").submit();
		 
	 });
	
});	 
	
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">系统管理&gt;&gt;个人信息</span>
</div>
<form id="info" name="info" action="Admin_saveAdmin.action" method="post">    
<input type="hidden" name="paramsUser.user_id" value="${admin.user_id}"/>
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">编辑个人信息</TD>
              <TD class="edittitleright">&nbsp;</TD>
            </TR>
        </TABLE>
     </td>
   </tr>
   <tr>
     <td height="1" bgcolor="#8f8f8f"></td>
   </tr>
   <tr>
     <td >
     <table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
       <tr>
          <td width="15%" align="right" style="padding-right:5px">${admin.user_type!=3?'学号':'用户名'}：</td>
          <td colspan="3">${admin.user_name}</td>
        </tr> 
        <tr>
          <td width="15%" align="right" style="padding-right:5px"><font color="red">*</font> 姓名：</td>
          <td width="35%">
            <input type="text" id="paramsUser.real_name" name="paramsUser.real_name" value="${admin.real_name}"/>
          </td> 
          <td width="15%" align="right" style="padding-right:5px"><font color="red">*</font> 昵称：</td>
          <td width="35%">
            <input type="text" id="paramsUser.nick_name" name="paramsUser.nick_name" value="${admin.nick_name}"/>
          </td>
        </tr>
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 性别：</td>
          <td>
             <input type="radio" name="paramsUser.user_sex" id="sex1" value="1"/>男&nbsp;&nbsp;
             <input type="radio" name="paramsUser.user_sex" id="sex2" value="2"/>女
          </td>
          <td align="right" style="padding-right:5px">年龄：</td>
          <td>
             <input type="text" id="paramsUser.user_age" name="paramsUser.user_age" value="${admin.user_age}"/>
          </td>
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px">邮箱：</td>
          <td>
             <input type="text" id="paramsUser.user_mail" name="paramsUser.user_mail" value="${admin.user_mail}"/>
          </td>
          <td align="right" style="padding-right:5px">院系：</td>
          <td>
             <input type="text" id="paramsUser.user_dept" name="paramsUser.user_dept" value="${admin.user_dept}"/>
          </td>
        </tr>            
         
     </table>
     </td>
   </tr>  
   <tr>
     <td>
       <table width="100%" align="center" cellpadding="0" cellspacing="0" class="editbody">
        <tr class="editbody">
          <td align="center" height="30">
            <input type="button" id="saveBtn" Class="btnstyle" value="编 辑"/> 
          </td>
        </tr>
      </table>
     </td>
   </tr>
</table>
</form>
</body>
</html>