$(document).ready(function(){
	var postStr = {
		'params.user_name':'',
		'params.user_pass':''
	};
	var selfCenterBtn = $("#selfCenterBtn");
	var loginOutBtn = $("#loginOutBtn");
	var loginInBtn = $("#loginInBtn");
	var regBtn = $("#regBtn");
	var infoField = $("#infoField");
	var loginField = $("#loginField");
	var user_name = $("#user_name");
	var user_pass = $("#user_pass");
	
	loginOutBtn.bind('click',function(){
		$.post('LoginOutSystem.action',null,
			function(responseObj) {
					if(responseObj.success) {	
						 alert('退出成功！');
						 infoField.css('display','none');
						 loginField.css('display','block');
					}else  if(responseObj.err.msg){
						 alert('退出异常：'+responseObj.err.msg);
					}else{
						 alert('退出异常：服务器异常！');
					}	
		 },'json');
	});
	
	var infos = "，欢迎您登录！ <input type=\"button\" id=\"loginOutBtn\" class=\"btnstyle\" value=\"退出\"/>";
	loginInBtn.bind('click',function(){
		if(user_name.val()==''||user_pass.val()==''){
			alert("用户名和密码不能为空！")
			return;
		}
		postStr['params.user_name'] = user_name.val();
		postStr['params.user_pass'] = user_pass.val();
		
		$.post('LoginInSystem.action',postStr,
			function(responseObj) {
					if(responseObj.success) {	
						 alert('登录成功！');
						 user_name.val('');
						 user_pass.val('');
						 loginField.css('display','none');
						 infoField.css('display','block');
						 infoField.html(responseObj.data.nick_name + infos);
						 window.location.reload();
					}else  if(responseObj.err.msg){
						 alert('登录异常：'+responseObj.err.msg);
					}else{
						 alert('登录异常：服务器异常！');
					}	
		 },'json');
	});
	
	regBtn.bind('click',function(){
		 window.location.href="page_reg.action";
	});
	
	selfCenterBtn.bind('click',function(){
		 window.location.href="page_myCollegeApply.action";
	});
});