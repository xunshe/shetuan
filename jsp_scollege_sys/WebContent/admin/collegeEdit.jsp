<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑社团</title>
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
	
	var num = /^\d+$/;
	var num2 = /^\d+(\.\d+)?$/;
	$("#editBtn").bind('click',function(){
		 	KE.sync('noticeContent');
		 	if($("#paramsCollege\\.college_type").val()==''){
				alert("社团类型不能为空！");
				return;
			}
		 	if($("#paramsCollege\\.college_persons").val()!=''){
				if(!num.exec($("#paramsCollege\\.college_persons").val())){
					alert('社团人数必须为数字');
					return;
				}
			}else{
				$("#paramsCollege\\.college_persons").val(0);
			}
		 	if($("#paramsCollege\\.college_money").val()!=''){
				if(!num2.exec($("#paramsCollege\\.college_money").val())){
					alert('入社社费必须为数字');
					return;
				}
			}else{
				$("#paramsCollege\\.college_money").val(0);
			}
		 	if($("#paramsCollege\\.college_plan").val()==''){
				alert("初步规划不能为空！");
				return;
			}
			if($("#noticeContent").val()==''){
				alert("社团简介不能为空！");
				return;
			}
			$("#info").attr('action','Admin_saveCollege.action').submit();
			 
	});
	
});

 
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">社团管理&gt;&gt;编辑社团信息</span>
</div>
<form id="info" name="info" action="Admin_saveCollege.action" method="post">    
<s:hidden id="paramsCollege.college_id" name="paramsCollege.college_id" value="%{#attr.college.college_id}" /> 
<input type="hidden" name="paramsCollege.college_pic" id="paramsCollege.college_pic" value='<s:property value="#attr.college.college_pic"/>'/>
<table width="600" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">编辑社团信息</TD>
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
          <td width="30%" align="right" style="padding-right:5px">社团名称：</td>
          <td width="70%">
            <s:property value="#attr.college.college_name"/> 
          </td>  
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 社团类型：</td>
          <td>
            <s:textfield  name="paramsCollege.college_type" id="paramsCollege.college_type" value="%{#attr.college.college_type}"/> 
          </td>  
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px">社团社长：</td>
          <td>
            <s:property value="#attr.college.real_name"/> 
          </td>  
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px">申请时间：</td>
          <td>
            <s:property value="#attr.college.create_date.substring(0,10)"/>
          </td>  
        </tr>
        <tr>
          <td align="right" style="padding-right:5px">社团人数：</td>
          <td>
            <s:textfield  name="paramsCollege.college_persons" id="paramsCollege.college_persons" value="%{#attr.college.college_persons}"/> 
          </td>  
        </tr> 
         <tr>
          <td align="right" style="padding-right:5px">入社社费：</td>
          <td>
            <s:textfield  name="paramsCollege.college_money" id="paramsCollege.college_money" value="%{#attr.college.college_money}"/> 
          </td>  
        </tr> 
        <tr>
		  <td align="right" style="padding-right:5px">社团图片：</td>
		  <td align="left">
		    <img id="userImg" src="../images/sttp/<s:property value='#attr.college.college_pic'/>" width="70" height="80" style="border:0px;"/>
		    <span id="op" style="display:none"><img src="images/loading004.gif"  height="50" /></span>
	      </td>
	    </tr>
	    <tr>
		  <td align="right" style="padding-right:5px">上传图片：</td>
	      <td align="left"> 
	          <iframe name="uploadPage" src="uploadImg2.jsp" width="100%" height="50" marginheight="0" marginwidth="0" scrolling="no" frameborder="0"></iframe>            
	       </td>
	    </tr>
        <tr> 
          <td align="right" style="padding-right:5px"><font color="red">*</font> 初步规划：</td>
          <td>
            <textarea style="width:350px;height:80px" name="paramsCollege.college_plan"  id="paramsCollege.college_plan"><s:property value="#attr.college.college_plan" escape="false"/></textarea>
          </td>
        </tr> 
        <tr> 
          <td align="right" style="padding-right:5px"><font color="red">*</font> 社团简介：</td>
          <td>
            <textarea style="width:350px;height:250px" name="paramsCollege.college_note"  id="noticeContent"><s:property value="#attr.college.college_note" escape="false"/></textarea>
          </td>
        </tr>   
        <tr>
          <td align="right" style="padding-right:5px">审批结果：</td>
          <td>
            <s:property value="#attr.college.college_flagDesc"/> 
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
            <input type="button" id="editBtn" Class="btnstyle" value="编 辑"/> 
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