<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><s:property value="#attr.sblog_typeDesc" /></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/message.css">
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
<body>
<jsp:include page="top.jsp"></jsp:include>
<div id="middle">
	 <div class="nav">当前位置: 主页 > 留言板 </div>
	 <!-- 信息开始 -->
	 <s:if test="totalCount > 0">
	 <s:iterator value="#attr.sblogs" id="sblog">
	 <div class="messages">
		 <div class="messages_left">
			<div class="nickName"><s:property value="#sblog.nick_name" /></div>
			<div class="headphoto"><img src="<s:property value='#sblog.sblog_pic' />"/></div>
			<div class="stuNo">学号：<s:property value="#sblog.user_name" /></div>
		</div>

		<div class="messages_right">
			<div class="time">
				主题：<s:property value="#attr.sblog.sblog_title" />&nbsp;&nbsp;
				<img src="images/time.gif" valign="middle"/> 
				<s:property value="#attr.sblog.sblog_date.substring(0,19)" />　
			</div>

			<div class="title">
				 <s:property value="#attr.sblog.sblog_content" />
			</div>
			
		</div>
	 </div>
	 </s:iterator>
	 </s:if>
	<!-- 信息结束 -->

	 <jsp:include page="page.jsp"></jsp:include>

	 <!-- 发布留言 -->
	 <div id="addSblog" style="display:block">
	 <form name="info2" id="info2" action="page_addSblog.action" method="post">
	 <input type="hidden" name="paramsSblog.user_id" id="paramsSblog.user_id" value="<s:property value='#attr.userFront.user_id' />"/>
	 <table class="reply_add">
			<tr>
				<td class="theme" colspan="4">发布留言：</td>
			</tr>
			<tr>
				<td align="right" width="18%">主题：</td>
				<td align="left" width="32%"><input type="text" name="paramsSblog.sblog_title" id="paramsSblog.sblog_title" style="width:200px;" class="inputstyle"/></td>
				<td align="right" width="18%">验证码：</td>
				<td align="left">
					<input type="text" id="paramsSblog.random" name="paramsSblog.random" style="width:80px;" class="inputstyle"/>
					&nbsp;<img src="Random.jsp" width="50" valign="middle" style="cursor:pointer;vertical-align:middle" title="换一张" id="safecode" border="0" onClick="reloadcode()"/>
				</td>
			</tr>
			<tr>
				<td align="right" width="18%">留言内容：</td>
				<td align="left" width="32%"><textarea name="paramsSblog.sblog_content" id="paramsSblog.sblog_content" style="width:200px;height:50px" class="inputstyle"></textarea></td>
				<td align="right" width="18%">选择头像：</td>
				<td align="left">
					<input type="radio" name="paramsSblog.sblog_pic" checked="checked" value="images/head/man04.gif" />
					<img src="images/head/man04.gif" width="50" />
					<input type="radio" name="paramsSblog.sblog_pic" value="images/head/woman08.gif" />
					<img src="images/head/woman08.gif" width="50" />
				</td>
			</tr>
			<tr>
				<td align="center" colspan="4">
					<input type="button" id="addBtn" class="btnstyle" value="提交"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="reset" id="resetBtn" class="btnstyle" value="清空"/>
				</td>
			</tr>
	 </table>
	 </form>
	 </div>
	 <a name="link"></a>
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script type="text/javascript">
var user_id = "<s:property value='#attr.userFront.user_id' />";
//实现验证码点击刷新
function reloadcode(){
	var verify=document.getElementById('safecode');
	verify.setAttribute('src','Random.jsp?'+Math.random());
}
 
$(document).ready(function(){
	$("#addBtn").bind("click",function(){
		if(user_id==''){
			alert('请先登录后在进行留言！')
			return;
		}
		if($("#paramsSblog.reply_title").val()==''){
			alert('留言主题不能为空！')
			return;
		}
		if($("#random").val()==''){
			alert('验证码不能为空！')
			return;
		}
		if($("#paramsSblog.reply_content").val()==''){
			alert('留言内容不能为空！')
			return;
		}
		var aQuery = $("#info2").serialize();  
		$.post('page_addSblog.action',aQuery,
			function(responseObj) {
					if(responseObj.success) {	
						 alert('留言成功，请等待审核通过！');
						 window.location.reload();
					}else  if(responseObj.err.msg){
						 alert('留言失败：'+responseObj.err.msg);
					}else{
						 alert('留言失败：服务器异常！');
					}	
		},'json');
	});
});


</script>
</body>
</html>