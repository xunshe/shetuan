<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>留言信息</title>
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
   document.info.action="Admin_listSblogs.action";
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
       alert("请至少选择一个要删除的留言！");
       return false;
    }
    if(confirm('确认删除吗!?'))
    {
       document.info.action="Admin_delSblogs.action?paramsSblog.ids="+ids;
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
  document.info.action="Admin_listSblogs.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listSblogs.action";
  document.info.submit();
}
</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">留言管理&gt;&gt;留言查询</span>
</div>
<form name="info" id="info" action="Admin_listSblogs.action" method="post">
<input type="hidden" name="paramsSblog.sblog_type" id="paramsSblog.sblog_type" value='<s:property value="#attr.sblog_type"/>'/>
<input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">留言列表</td>
    <td width="" align="right">
            留言标题：
      <input type="text" id="paramsSblog.sblog_title" name="paramsSblog.sblog_title" value="${paramsSblog.sblog_title}" class="inputstyle"/>&nbsp;
            审批结果：
      <s:select name="paramsSblog.sblog_flag" id="paramsSblog.sblog_flag" list="#{1:'待审批',2:'审批通过'}" value="%{#attr.paramsSblog.sblog_flag}" 
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
     <td width="" align="center">留言人</td>
     <td width="" align="center">留言标题</td>
     <td width="" align="center">留言内容</td> 
     <td width="" align="center">留言时间</td>
     <td width="" align="center">审批结果</td>
     <td width="" align="center">操作</td>
   </tr>
   <s:if test="#attr.sblogs!=null && #attr.sblogs.size()>0">
   <s:iterator value="#attr.sblogs" id="sblog" status="status">
   <tr class="<s:if test='(#status.index + 1)%2==0'>list1</s:if><s:else>list0</s:else>" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <td width="" align="center"><s:checkbox name="chkid" fieldValue="%{#sblog.sblog_id}" cssStyle="vertical-align:text-bottom;"/></td>
     <td width="" align="center"><s:property value="#status.index+1"/></td>
     <td width="" align="center"><s:property value="#sblog.real_name"/></td>
     <td width="" align="center"><s:property value="#sblog.sblog_title"/></td>
     <td width="" align="center" title="<s:property value='#sblog.sblog_content'/>">
     <s:if test="#sblog.sblog_content.length()>20">
     <s:property value="#sblog.sblog_content.substring(0,18)"/>...
     </s:if>
     <s:else> <s:property value="#sblog.sblog_content"/></s:else>
     </td>
     <td width="" align="center"><s:property value="#sblog.sblog_date.substring(0,19)"/></td>  
     <td width="" align="center"><s:property value="#sblog.sblog_flagDesc"/></td>
     <td width="" align="center">
       <s:if test="#sblog.sblog_flag==1">
       <s:a href="Admin_approveSblog.action?paramsSblog.sblog_id=%{#sblog.sblog_id}">审批通过</s:a>
       </s:if>
     </td>
   </tr> 
   </s:iterator>
   </s:if>
   <s:else>
   <tr>
     <td height="60" colspan="8" align="center"><b>&lt;不存在留言信息&gt;</b></td>
   </tr>
   </s:else>
   
</table>
<jsp:include page="page.jsp"></jsp:include>
</form> 
</body>
</html>