<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>社团成员信息</title>
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
   document.info.action="Admin_listMembers.action";
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
       alert("请至少选择一个要删除的社团成员！");
       return false;
    }
    if(confirm('确认删除吗!?'))
    {
       document.info.action="Admin_delMembers.action?paramsMember.ids="+ids;
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
  document.info.action="Admin_listMembers.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listMembers.action";
  document.info.submit();
}
</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">社团成员&gt;&gt;社团成员查询</span>
</div>
<form name="info" id="info" action="Admin_listMembers.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">社团成员列表</td>
    <td width="" align="right">
            社团名称：
      <input type="text" id="paramsMember.college_name" name="paramsMember.college_name" value="${paramsMember.college_name}" class="inputstyle"/>&nbsp;      
            成员姓名：
      <input type="text" id="paramsMember.real_name" name="paramsMember.real_name" value="${paramsMember.real_name}" class="inputstyle"/>&nbsp;
            审批结果：
      <s:select name="paramsMember.member_flag" id="paramsMember.member_flag" list="#{1:'待审批',2:'审批通过'}" value="%{#attr.paramsMember.member_flag}" 
      			listKey="key" listValue="value" headerKey="0" headerValue="请选择" 
      			cssClass="selectstyle" cssStyle="width:100px"></s:select>&nbsp;&nbsp;
      <input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;
      <input type="button" value="删除" onclick="del();" class="btnstyle"/>
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
<table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <td width="40" align="center"><input type="checkbox" onclick="CheckAll(this);" style="vertical-align:text-bottom;" title="全选/取消全选"/></td>
     <td width="40" align="center">序号</td>
     <td width="" align="center">成员姓名</td>
     <td width="" align="center">社团名称</td>
     <td width="" align="center">申请日期</td>
     <td width="" align="center">申请原因</td>
     <td width="" align="center">个人爱好</td>
     <td width="" align="center">审批结果</td>
     <td width="" align="center">操作</td>
   </tr>
   <s:if test="#attr.members!=null && #attr.members.size()>0">
   <s:iterator value="#attr.members" id="member" status="status">
   <tr class="<s:if test='(#status.index + 1)%2==0'>list1</s:if><s:else>list0</s:else>" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <td width="" align="center"><s:checkbox name="chkid" fieldValue="%{#member.member_id}" cssStyle="vertical-align:text-bottom;"/></td>
     <td width="" align="center"><s:property value="#status.index+1"/></td>
     <td width="" align="center"><s:property value="#member.real_name"/></td>
     <td width="" align="center"><s:property value="#member.college_name"/></td>
     <td width="" align="center"><s:property value="#member.reg_date.substring(0,10)"/></td>  
     <td width="" align="center"><s:property value="#member.member_reason"/></td>
     <td width="" align="center"><s:property value="#member.member_hobby"/>&nbsp;</td>
     <td width="" align="center"><s:property value="#member.member_flagDesc"/></td>
     <td width="" align="center">
     <s:if test="#member.member_flag==1">
       <img src="images/edit.png"/>&nbsp;<s:a href="Admin_approveMember.action?paramsMember.member_id=%{#member.member_id}">审批通过</s:a>
     </s:if>
     </td>
   </tr> 
   </s:iterator>
   </s:if>
   <s:else>
   <tr>
     <td height="60" colspan="9" align="center"><b>&lt;不存在社团成员信息&gt;</b></td>
   </tr>
   </s:else>
   
</table>
<jsp:include page="page.jsp"></jsp:include>
</form> 
</body>
</html>