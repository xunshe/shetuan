<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:if test="#attr.activity!=null && #attr.activity.activity_id!=0">编辑</s:if><s:else>发布</s:else>活动消息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script charset="utf-8" src="editor/kindeditor.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	function trimStr(str)     
	{     
	    if ((typeof(str) != "string") || !str)   
	    {    
	        return "";    
	    }   
	    return str.replace(/(^\s*)|(\s*$)/g, "");    
	}
	
	var num=/^\d+(\.\d+)?$/;
	$("#addBtn").bind('click',function(){
		KE.sync('noticeContent');
		if($("#paramsActivity\\.activity_title").val()==''){
			alert("活动名称不能为空！");
			return;
		}
		if($("#paramsActivity\\.activity_date").val()==''){
			alert("活动日期不能为空！");
			return;
		}
		if($("#paramsActivity\\.activity_address").val()==''){
			alert("活动地点不能为空！");
			return;
		}
		if($("#paramsActivity\\.activity_money").val()!=''){
			if(!num.exec($("#paramsActivity\\.activity_money").val())){
				alert('活动经费为数字');
				return;
			}
		}else{
			$("#paramsActivity\\.activity_money").val(0);
		}
		if($("#noticeContent").val()==''){
			alert("活动内容不能为空！");
			return;
		}
		$("#paramsActivity\\.activity_id").val(0);
		$("#info").attr('action','Admin_addActivity.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
		    KE.sync('noticeContent');
		    if($("#paramsActivity\\.activity_title").val()==''){
				alert("活动名称不能为空！");
				return;
			}
			if($("#paramsActivity\\.activity_date").val()==''){
				alert("活动日期不能为空！");
				return;
			}
			if($("#paramsActivity\\.activity_address").val()==''){
				alert("活动地点不能为空！");
				return;
			}
			if($("#paramsActivity\\.activity_money").val()!=''){
				if(!num.exec($("#paramsActivity\\.activity_money").val())){
					alert('活动经费为数字');
					return;
				}
			}else{
				$("#paramsActivity\\.activity_money").val(0);
			}
			if($("#noticeContent").val()==''){
				alert("活动内容不能为空！");
				return;
			}
			$("#info").attr('action','Admin_saveActivity.action').submit();
			 
	});
	
});

 
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">${admin.user_type==2?'社团活动':'活动消息' }&gt;&gt;<s:if test="#attr.activity!=null && #attr.activity.activity_id!=0">编辑</s:if><s:else>发布</s:else>活动消息</span>
</div>
<form id="info" name="info" action="Admin_saveActivity.action" method="post">    
<s:hidden id="paramsActivity.activity_id" name="paramsActivity.activity_id" value="%{#attr.activity.activity_id}" /> 
<table width="600" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle"><s:if test="#attr.infos!=null">编辑</s:if><s:else>发布</s:else>活动消息</TD>
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
          <td width="30%" align="right" style="padding-right:5px">活动名称：</td>
          <td width="70%">
            <s:textfield cssStyle="width:345px;" name="paramsActivity.activity_title" id="paramsActivity.activity_title" value="%{#attr.activity.activity_title}"/> 
          </td>  
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px">活动日期：</td>
          <td>
            <s:textfield name="paramsActivity.activity_date" id="paramsActivity.activity_date" value="%{#attr.activity.activity_date}" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/> 
          </td>  
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px">活动地点：</td>
          <td>
            <s:textfield cssStyle="width:345px;" name="paramsActivity.activity_address" id="paramsActivity.activity_address" value="%{#attr.activity.activity_address}"/> 
          </td>  
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px">活动器材：</td>
          <td>
            <s:textfield cssStyle="width:345px;" name="paramsActivity.activity_equip" id="paramsActivity.activity_equip" value="%{#attr.activity.activity_equip}"/> 
          </td>  
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px">器材参考：</td>
          <td style="height:60px">
            <s:property value="%{#attr.equips}"/> 
          </td>  
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px">活动经费：</td>
          <td>
            <s:textfield cssStyle="width:345px;" name="paramsActivity.activity_money" id="paramsActivity.activity_money" value="%{#attr.activity.activity_money}"/> 
          </td>  
        </tr> 
        <tr> 
          <td align="right" style="padding-right:5px">活动内容：</td>
          <td>
            <textarea style="width:350px;height:250px" name="paramsActivity.activity_content"  id="noticeContent"><s:property value="#attr.activity.activity_content" escape="false"/></textarea>
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
            <s:if test="#attr.activity!=null && #attr.activity.activity_id!=0">
          	<input type="button" id="editBtn" Class="btnstyle" value="编 辑"/> 
          	</s:if>
          	<s:else>
          	<input type="button" id="addBtn" Class="btnstyle" value="添 加" />
          	</s:else>
            &nbsp;<label style="color:red">${tip}</label> 
          </td>
        </tr>
      </table>
     </td> 
   </tr>
</table>
</form>
<script>        
	   KE.show({ 
	            id : 'noticeContent',
	            items:['source', '|','plainpaste', '|', 'selectall', 'bold','italic'],
	            resizeMode:1
	            
	                    
	   });
	   
</script>
</body>
</html>