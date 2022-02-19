<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>活动消息信息</title>
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
   document.activity.action="Admin_listActivitys.action";
   document.activity.submit();
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
       alert("请至少选择一个要删除的选项！");
       return false;
    }
    if(confirm('确认删除吗!?'))
    {
       document.activity.action="Admin_delActivitys.action?paramsActivity.ids="+ids;
       document.activity.submit();
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
  document.activity.action="Admin_listActivitys.action";
  document.activity.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.activity.action="Admin_listActivitys.action";
  document.activity.submit();
}
</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">${admin.user_type==2?'社团活动':'活动消息' }&gt;&gt;活动消息查询</span>
</div>
<form name="activity" id="activity" action="Admin_listActivitys.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">${admin.user_type==2?'社团活动':'活动消息' }列表</td>
    <td width="" align="right">
            活动名称：
      <input type="text" id="paramsActivity.activity_title" name="paramsActivity.activity_title" value="${paramsActivity.activity_title}" class="inputstyle"/>&nbsp;&nbsp;
            活动类型：
      <s:select name="paramsActivity.activity_type" id="paramsActivity.activity_type" list="#{1:'社团活动',2:'校园活动'}" value="%{#attr.paramsActivity.activity_type}" 
      			listKey="key" listValue="value" headerKey="0" headerValue="请选择" 
      			cssClass="selectstyle" cssStyle="width:100px"></s:select>&nbsp;   
            审批结果：
      <s:select name="paramsActivity.activity_flag" id="paramsActivity.activity_flag" list="#{1:'待审批',2:'审批通过'}" value="%{#attr.paramsActivity.activity_flag}" 
      			listKey="key" listValue="value" headerKey="0" headerValue="请选择" 
      			cssClass="selectstyle" cssStyle="width:100px"></s:select>&nbsp;&nbsp;
      <input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;
      <input type="button" value="增加" onclick="window.location='Admin_addActivityShow.action';" class="btnstyle"/> &nbsp;
      <input type="button" value="删除" onclick="del();" class="btnstyle"/>
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
<table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <td width="40" align="center"><input type="checkbox" onclick="CheckAll(this);" style="vertical-align:text-bottom;" title="全选/取消全选"/></td>
     <td width="40" align="center">序号</td>
     <td width="" align="center">活动名称</td>
     <td width="" align="center">活动类型</td>
     <td width="" align="center">活动日期</td>
     <td width="" align="center">活动地点</td>
     <td width="" align="center">审批结果</td>
     <td width="" align="center">操作</td>
   </tr>
   <s:if test="#attr.activitys!=null && #attr.activitys.size()>0">
   <s:iterator value="#attr.activitys" id="activity" status="status">
   <tr class="<s:if test='(#status.index + 1)%2==0'>list1</s:if><s:else>list0</s:else>" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <td width="" align="center"><s:checkbox name="chkid" fieldValue="%{#activity.activity_id}" cssStyle="vertical-align:text-bottom;"/></td>
     <td width="" align="center"><s:property value="#status.index+1"/></td>
     <td width="" align="center"><s:property value="#activity.activity_title"/></td>
     <td width="" align="center"><s:property value="#activity.activity_typeDesc"/></td>
     <td width="" align="center"><s:property value="#activity.activity_date.substring(0,10)"/></td>  
     <td width="" align="center"><s:property value="#activity.activity_address"/>&nbsp;</td>
     <td width="" align="center"><s:property value="#activity.activity_flagDesc"/></td>
     <td width="" align="center">
       <s:a href="Admin_editActivity.action?paramsActivity.activity_id=%{#activity.activity_id}">编辑</s:a>&nbsp;
       <s:if test="#activity.activity_flag==1 && #attr.admin.user_type==3">
       <s:a href="Admin_approveActivity.action?paramsActivity.activity_id=%{#activity.activity_id}">审批</s:a>
       </s:if>
     </td>
   </tr> 
   </s:iterator>
   </s:if>
   <s:else>
   <tr>
     <td height="60" colspan="8" align="center"><b>&lt;不存在活动消息信息&gt;</b></td>
   </tr>
   </s:else>
   
</table>
<jsp:include page="page.jsp"></jsp:include>
</form> 
</body>
</html>