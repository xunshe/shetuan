<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:if test="#attr.news!=null && #attr.news.news_id!=0">编辑</s:if><s:else>发布</s:else>新闻资讯</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
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
	
	 
	$("#addBtn").bind('click',function(){
		KE.sync('noticeContent');
		if($("#paramsNews\\.news_title").val()==''){
			alert("新闻标题不能为空！");
			return;
		}
		if($("#noticeContent").val()==''){
			alert("新闻内容不能为空！");
			return;
		}
		$("#paramsNews\\.news_id").val(0);
		$("#info").attr('action','Admin_addNews.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
		    KE.sync('noticeContent');
		    if($("#paramsNews\\.news_title").val()==''){
				alert("新闻标题不能为空！");
				return;
			}
			if($("#noticeContent").val()==''){
				alert("新闻内容不能为空！");
				return;
			}
			$("#info").attr('action','Admin_saveNews.action').submit();
			 
	});
	
});

 
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">${admin.user_type==2?'社团新闻':'新闻资讯' }&gt;&gt;<s:if test="#attr.news!=null && #attr.news.news_id!=0">编辑</s:if><s:else>发布</s:else>新闻资讯</span>
</div>
<form id="info" name="info" action="Admin_saveNews.action" method="post">    
<s:hidden id="paramsNews.news_id" name="paramsNews.news_id" value="%{#attr.news.news_id}" /> 
<input type="hidden" name="paramsNews.news_picture" id="paramsNews.news_picture" value='<s:property value="#attr.news.news_picture"/>'/>
<table width="600" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle"><s:if test="#attr.infos!=null">编辑</s:if><s:else>发布</s:else>新闻资讯</TD>
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
          <td width="30%" align="right" style="padding-right:5px">新闻标题：</td>
          <td width="70%">
            <s:textfield cssStyle="width:345px;" name="paramsNews.news_title" id="paramsNews.news_title" value="%{#attr.news.news_title}"/> 
          </td>  
        </tr> 
        <tr>
		  <td align="right" style="padding-right:5px">新闻图片：</td>
		  <td align="left">
		    <img id="userImg" src="../images/xwtp/<s:property value='%{#attr.news.news_picture}'/>" width="70" height="80" style="border:0px;"/>
		    <span id="op" style="display:none"><img src="images/loading004.gif"  height="50" /></span>
	      </td>
	    </tr>
	    <tr>
		  <td align="right" style="padding-right:5px">上传图片：</td>
	      <td align="left"> 
	          <iframe name="uploadPage" src="uploadImg3.jsp" width="100%" height="50" marginheight="0" marginwidth="0" scrolling="no" frameborder="0"></iframe>            
	       </td>
	    </tr>
        <tr> 
          <td align="right" style="padding-right:5px">新闻内容：</td>
          <td>
            <textarea style="width:350px;height:250px" name="paramsNews.news_content"  id="noticeContent"><s:property value="#attr.news.news_content" escape="false"/></textarea>
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
            <s:if test="#attr.news!=null && #attr.news.news_id!=0">
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