<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑图片新闻</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script charset="utf-8" src="editor/kindeditor.js"></script>
<script language="javascript" type="text/javascript">
function trimStr(str)     
{     
    if ((typeof(str) != "string") || !str)   
    {    
        return "";    
    }   
    return str.replace(/(^\s*)|(\s*$)/g, "");    
} 
function Check()
{     
      KE.sync('noticeContent');
      //var time=/^\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}$/;//int类型
     
      var noticeTitle=document.getElementById("paramsPicnews.picnews_title");
      var noticeContent=document.getElementById("noticeContent");
      var noticeAdmin=document.getElementById("paramsPicnews.picnews_admin");
      var noticePic=document.getElementById("paramsPicnews.picnews_picture");
     
	  if(trimStr(noticeTitle.value)=='')
	  {
		alert("新闻标题不能为空！");
		noticeTitle.focus();
		return false;
	  }
	  if(trimStr(noticeContent.value)=='')
	  {
		alert("新闻内容不能为空！");
		noticeContent.focus();
		return false;
	  }
	  if(trimStr(noticeAdmin.value)=='')
	  {
		alert("发布作者不能为空！");
		noticeAdmin.focus();
		return false;
	  }
	  if(trimStr(noticePic.value)=='')
	  {
		alert("新闻图片不能为空！");
		return false;
	  }
	  return true;
}
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">图片新闻&gt;&gt;编辑新闻</span>
</div>
<form id="info" name="info" action="Admin_savePicnews.action" method="post" onsubmit="return Check()">    
<input type="hidden" name="paramsPicnews.picnews_id" value='<s:property value="#attr.picnews.picnews_id"/>'/>
<input type="hidden" name="paramsPicnews.picnews_picture" id="paramsPicnews.picnews_picture" value='<s:property value="#attr.picnews.picnews_picture"/>'/>
<table width="600" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">编辑图片新闻</TD>
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
          <td colspan="3">
          	<s:textfield name="paramsPicnews.picnews_title" id="paramsPicnews.picnews_title" value="%{#attr.picnews.picnews_title}" style="width:250px;"/>
          </td>  
        </tr> 
        <tr> 
          <td align="right" style="padding-right:5px">新闻内容：</td>
          <td colspan="3">
            <textarea style="width:350px;height:250px" name="paramsPicnews.picnews_content"  id="noticeContent"><s:property value="#attr.picnews.picnews_content" escape="false"/></textarea>
          </td>
        </tr>   
       <tr>
		  <td align="right" style="padding-right:5px">新闻图片：</td>
		  <td align="left">
		    <img id="userImg" src="../images/hdtp/<s:property value='#attr.picnews.picnews_picture'/>" width="70" height="80" style="border:0px;"/>
	        &nbsp;<span id="op" style="display:none"><img src="images/loading004.gif"  height="80" /></span>
	      </td>
	      <td align="left" colspan="2"> 
	          <iframe name="uploadPage" src="uploadImg.jsp" width="100%" height="80" marginheight="0" marginwidth="0" scrolling="no" frameborder="0"></iframe>            
	       </td>
	  </tr>
	  <tr>
          <td width="30%" align="right" style="padding-right:5px">发布作者：</td>
          <td colspan="3">
          	<s:textfield name="paramsPicnews.picnews_admin" id="paramsPicnews.picnews_admin" value="%{#attr.picnews.picnews_admin}" style="width:250px;"/>
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
           <input type="submit" Class="btnstyle" value="编 辑"/>
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