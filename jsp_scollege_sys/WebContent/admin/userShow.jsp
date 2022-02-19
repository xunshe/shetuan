<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
var tempClassName="";
function tr_mouseover(obj) 
{ 
	tempClassName=obj.className;
	obj.className="list_mouseover";
}
function tr_mouseout(obj)      
{ 
	obj.className=tempClassName;
}      
function CheckAll(obj) 
{
	var checks=document.getElementsByName("chkid");
    for (var i=0;i<checks.length;i++)
	{
	    var e = checks[i];
	    e.checked = obj.checked;
	}
    
}


function serch()
{
   document.info.action="Admin_listUsers.action";
   document.info.submit();
}
function del()
{
	var checks=document.getElementsByName("chkid");
    var ids="";
	for (var i=0;i<checks.length;i++)
    {
        var e = checks[i];
		if(e.checked==true)
		{
		  if(ids=="")
		  {
		    ids=ids+e.value;
		  }
		  else
		  {
		    ids=ids+","+e.value;
		  }
		}
    }
    if(ids=="")
    {
       alert("请至少选择一个要删除的用户！");
       return false;
    }
    if(confirm('确认删除吗!?'))
    {
       document.info.action="Admin_delUsers.action?paramsUser.ids="+ids;
       document.info.submit();
    }
    
}
function GoPage()
{
  var pagenum=document.getElementById("goPage").value;
  var patten=/^\d+$/;
  if(!patten.exec(pagenum))
  {
    alert("页码必须为大于0的数字");
    return false;
  }
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listUsers.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listUsers.action";
  document.info.submit();
}
</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">用户管理&gt;&gt;用户查询</span>
</div>
<form name="info" id="info" action="Admin_listUsers.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">用户列表</td>
    <td width="" align="right">
            学号：
      <input type="text" id="paramsUser.user_name" name="paramsUser.user_name" value="${paramsUser.user_name}" class="inputstyle"/>&nbsp;
           用户类型：
      <s:select name="paramsUser.user_type" id="paramsUser.user_type" list="#{1:'注册用户',2:'社团管理员'}" value="%{#attr.paramsUser.user_type}" 
      			listKey="key" listValue="value" headerKey="0" headerValue="请选择" 
      			cssClass="selectstyle" cssStyle="width:120px"></s:select>&nbsp;&nbsp;
      <input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;
      <input type="button" value="增加" onclick="window.location='Admin_addUserShow.action';" class="btnstyle"/> &nbsp;
      <input type="button" value="删除" onclick="del();" class="btnstyle"/>
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
<table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <td width="40" align="center"><input type="checkbox" onclick="CheckAll(this);" style="vertical-align:text-bottom;" title="全选/取消全选"/></td>
     <td width="40" align="center">序号</td>
     <td width="" align="center">学号</td>
     <td width="" align="center">姓名</td>
     <td width="" align="center">性别</td>
     <td width="" align="center">昵称</td>
     <td width="" align="center">年龄</td>
     <td width="" align="center">邮箱</td>
     <td width="" align="center">用户类型</td>
     <td width="" align="center">注册时间</td>
     <td width="" align="center">操作</td>
   </tr>
   <s:if test="#attr.users!=null && #attr.users.size()>0">
   <s:iterator value="#attr.users" id="user" status="status">
   <tr class="<s:if test='(#status.index + 1)%2==0'>list1</s:if><s:else>list0</s:else>" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <td width="" align="center"><s:checkbox name="chkid" fieldValue="%{#user.user_id}" cssStyle="vertical-align:text-bottom;"/></td>
     <td width="" align="center"><s:property value="#status.index+1"/></td>
     <td width="" align="center"><s:property value="#user.user_name"/></td>
     <td width="" align="center"><s:property value="#user.real_name"/></td>
     <td width="" align="center"><s:property value="#user.user_sexDesc"/></td>
     <td width="" align="center"><s:property value="#user.nick_name"/></td>
     <td width="" align="center"><s:property value="#user.user_age"/></td>
     <td width="" align="center"><s:property value="#user.user_mail"/></td>
     <td width="" align="center"><s:property value="#user.user_typeDesc"/></td>
     <td width="" align="center"><s:property value="#user.reg_date.substring(0,19)"/></td>  
     <td width="" align="center">
       <img src="images/edit.png"/>&nbsp;<s:a href="Admin_editUser.action?paramsUser.user_id=%{#user.user_id}">编辑</s:a>
     </td>
   </tr> 
   </s:iterator>
   </s:if>
   <s:else>
   <tr>
     <td height="60" colspan="11" align="center"><b>&lt;不存在用户信息&gt;</b></td>
   </tr>
   </s:else>
   
</table>
<jsp:include page="page.jsp"></jsp:include>
</form> 
</body>
</html>