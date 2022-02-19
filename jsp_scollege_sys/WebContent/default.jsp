<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
	var swf_width=280;
	var swf_height=240;
	//文字颜色|文字位置|文字背景颜色|文字背景透明度|按键文字颜色|按键默认颜色|按键当前颜色|自动播放时间|图片过渡效果|是否显示按钮|打开方式
	var configtg='0xffffff|1|0xaf3c3c|6|0xffffff|0xC5DDBC|0x000033|4|3|1|_blank';
	var files = '';   
	var links = '';
	var texts = '';   
	<s:iterator id="picnews" value="#attr.picnewss" status="row">
		files+='images/hdtp/<s:property value="#picnews.picnews_picture" />';
		links=links+'page_queryPicnews.action?paramsPicnews.picnews_id=<s:property value="#picnews.picnews_id"/>';
		<s:if test="#picnews.picnews_title.length()>22">
		texts+='<s:property value="#picnews.picnews_title.substring(0,22)" />...';
		</s:if>
		<s:else>
		texts+='<s:property value="#picnews.picnews_title" />';
		</s:else>
		
		<s:if test="!#row.last">
			files+='|';
			links+='|';
			texts+='|';
		</s:if>
	</s:iterator>
	
	function playit(){
		document.write('<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" width="'+ swf_width +'" height="'+ swf_height +'">');
		document.write('<param name="movie" value="images/hdtp/bcastr3.swf"><param name="quality" value="high">');
		document.write('<param name="menu" value="false"><param name=wmode value="opaque">');
		document.write('<param name="FlashVars" value="bcastr_file='+files+'&bcastr_link='+links+'&bcastr_title='+texts+'&bcastr_config='+configtg+'">');
		document.write('<embed src="images/hdtp/bcastr3.swf" wmode="opaque" FlashVars="bcastr_file='+files+'&bcastr_link='+links+'&bcastr_title='+texts+'&bcastr_config='+configtg+'&menu="false" quality="high" width="'+ swf_width +'" height="'+ swf_height +'" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />'); document.write('</object>');
	}
	
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
	<div id="middle_left">
		<div id="picnews">
			<script type="text/javascript">playit();</script>
		</div>
		<div id="info">
			<div class="titleBg">最新新闻</div>
			<div class="info_con">
					<ul>
					  <s:iterator value="#attr.newss1" id="news">
					  <li>
						<div>
							<div class="info_text">
								<s:a href="page_queryNews.action?paramsNews.news_id=%{#news.news_id}" target="_blank" title="%{#news.news_title}">
									<s:property value="#news.news_title.length()>24?#news.news_title.substring(0,22)+'...':#news.news_title"/>
								</s:a>
							</div>
							<div class="info_time">[<s:property value="#news.news_date.substring(0,10)"/>]</div>
						</div>
					  </li>
					  </s:iterator>
					</ul>
			</div>
		</div>
		<div id="school">
			<div class="titleBg">校园风光</div>
			<div class="school_con" id="school_con">
				<ul>
					<li>
						<img src="images/xyfg/1.jpg" width="210" height="190">
					 </li>
					 <li>
						<img src="images/xyfg/2.jpg" width="210" height="190">
					 </li>
					 <li>
						<img src="images/xyfg/3.jpg" width="210" height="190">
					 </li>
					 <li>
						<img src="images/xyfg/4.jpg" width="210" height="190">
					 </li>
					 <li>
						<img src="images/xyfg/5.jpg" width="210" height="190">
					 </li>
					 <li>
						<img src="images/xyfg/6.jpg" width="210" height="190">
					 </li>
					 <li>
						<img src="images/xyfg/7.jpg" width="210" height="190">
					 </li>
					 <li>
						<img src="images/xyfg/8.jpg" width="210" height="190">
					 </li>
					 <li>
						<img src="images/xyfg/9.jpg" width="210" height="190">
					 </li>
					 <li>
						<img src="images/xyfg/10.jpg" width="210" height="190">
					 </li>
				</ul>
			</div>
<script type="text/javascript">
	//滚动插件
	(function($){
		$.fn.extend({
			ScrollLeft:function(opt,callback){
					//参数初始化
					if(!opt) var opt={};
					var _this=this.eq(0).find("ul:first");
					var colW=_this.find("li:first").width(), //获取行宽
						col=opt.col?parseInt(opt.col,3):parseInt(this.width()/colW,3), //每次滚动的行数，默认为一屏，即父容器高度
						speed=opt.speed?parseInt(opt.speed,10):500, //卷动速度，数值越大，速度越慢（毫秒）
						timer=opt.timer?parseInt(opt.timer,10):3000; //滚动的时间间隔（毫秒）
					if(col==0) col=3;
					var leftWidth=0-col*colW-20;
					//滚动函数
					scrollLeft=function(){
							_this.animate({
									marginLeft:leftWidth
							},speed,function(){
									for(i=1;i<=col;i++){
											_this.find("li:first").appendTo(_this);
									}
									_this.css({marginLeft:0});
							});
					}
					//鼠标事件绑定
					_this.hover(function(){
							clearInterval(timerID);
					},function(){
							timerID=setInterval("scrollLeft()",timer);
					}).mouseout();
			}       
		});
	})(jQuery);
	
	$(document).ready(function(){
		$("#school_con").ScrollLeft({col:1,speed:1000,timer:3000});
	});
</script>
		</div>
		<div class="infos1">
			<div class="titleBg2">
				<div class="titleBg_text">社团活动</div>
				<div class="titleBg_more"><a href="page_listActivitys.action" target="_blank">More>></a></div>
			</div>
			<div class="infos_con">
				<ul>
					<s:iterator value="#attr.activitys1" id="activity">
					<li>
						<s:property value="#activity.activity_date.substring(0,10)"/>&nbsp;
						<s:a href="page_queryActivity.action?paramsActivity.activity_id=%{#activity.activity_id}" target="_blank" title="%{#activity.activity_title}">
							<s:property value="#activity.activity_title.length()>18?#activity.activity_title.substring(0,18)+'...':#activity.activity_title"/>
						</s:a>
					</li>
					</s:iterator>
				</ul>
			</div>
		</div>
		<div class="infos2">
			<div class="titleBg2">
				<div class="titleBg_text">校园活动</div>
				<div class="titleBg_more"><a href="page_listActivitys.action" target="_blank">More>></a></div>
			</div>
			<div class="infos_con">
				<ul>
					<s:iterator value="#attr.activitys2" id="activity">
					<li>
						<s:property value="#activity.activity_date.substring(0,10)"/>&nbsp;
						<s:a href="page_queryActivity.action?paramsActivity.activity_id=%{#activity.activity_id}" target="_blank" title="%{#activity.activity_title}">
							<s:property value="#activity.activity_title.length()>18?#activity.activity_title.substring(0,18)+'...':#activity.activity_title"/>
						</s:a>
					</li>
					</s:iterator>
				</ul>
			</div>
		</div>
		<div class="infos1">
			<div class="titleBg2">
				<div class="titleBg_text">社团新闻</div>
				<div class="titleBg_more"><a href="page_listNewss.action" target="_blank">More>></a></div>
			</div>
			<div class="infos_con">
				<ul>
					<s:iterator value="#attr.newss2" id="news">
					<li>
						<s:property value="#news.news_date.substring(0,10)"/>&nbsp;
						<s:a href="page_queryNews.action?paramsNews.news_id=%{#news.news_id}" target="_blank" title="%{#news.news_title}">
							<s:property value="#news.news_title.length()>18?#news.news_title.substring(0,18)+'...':#news.news_title"/>
						</s:a>
					</li>
					</s:iterator>
				</ul>
			</div>
		</div>
		<div class="infos2">
			<div class="titleBg2">
				<div class="titleBg_text">校园新闻</div>
				<div class="titleBg_more"><a href="page_listNewss.action" target="_blank">More>></a></div>
			</div>
			<div class="infos_con">
				<ul>
					<s:iterator value="#attr.newss3" id="news">
					<li>
						<s:property value="#news.news_date.substring(0,10)"/>&nbsp;
						<s:a href="page_queryNews.action?paramsNews.news_id=%{#news.news_id}" target="_blank" title="%{#news.news_title}">
							<s:property value="#news.news_title.length()>18?#news.news_title.substring(0,18)+'...':#news.news_title"/>
						</s:a>
					</li>
					</s:iterator>
				</ul>
			</div>
		</div>
		<div class="infos3">
			<div class="titleBg3">
				<div class="titleBg_text">留言信息</div>
				<div class="titleBg_more"><a href="page_listSblogs.action" target="_blank">More>></a></div>
			</div>
			<div class="infos_con">
				<ul>
					<s:iterator value="#attr.sblogs" id="sblog">
					<li>
						<s:a href="page_listSblogs.action" target="_blank" title="%{#sblog.sblog_title}">
							<s:property value="#sblog.sblog_title.length()>18?#sblog.sblog_title.substring(0,18)+'...':#sblog.sblog_title"/>
						</s:a>&nbsp;
						[ <s:property value="#sblog.sblog_content.length()>30?#sblog.sblog_content.substring(0,30):#sblog.sblog_content"/>...　<s:property value="#sblog.sblog_date.substring(0,19)"/> ]
					</li>
					</s:iterator>
				</ul>
			</div>
		</div>
	</div>
	<div id="middle_right">
		<div id="note">
			<div class="titleBg">校园社团</div>
			<div class="note_con" id="note_con">
				<ul>
				  <s:iterator value="#attr.colleges" id="college">
				  <li>
					<s:a href="page_queryCollege.action?paramsCollege.college_id=%{#college.college_id}" target="_blank" title='%{#college.college_name}'>
						<img width="200" height="113" src='images/sttp/<s:property value="#attr.college.college_pic" />'/>
						<br/><s:property value="#college.college_name"/>
					</s:a>
				  </li>
				  </s:iterator>
				</ul>
			</div>
<script type="text/javascript">
	//滚动插件
	(function($){
		$.fn.extend({
			Scroll:function(opt,callback){
					//参数初始化
					if(!opt) var opt={};
					var _this=this.eq(0).find("ul:first");
					var lineH=_this.find("li:first").height(), //获取行高
						line=opt.line?parseInt(opt.line,10):parseInt(this.height()/lineH,10), //每次滚动的行数，默认为一屏，即父容器高度
						speed=opt.speed?parseInt(opt.speed,10):500, //卷动速度，数值越大，速度越慢（毫秒）
						timer=opt.timer?parseInt(opt.timer,10):3000; //滚动的时间间隔（毫秒）
					if(line==0) line=1;
					var upHeight=0-line*lineH;
					//滚动函数
					scrollUp=function(){
							_this.animate({
									marginTop:upHeight
							},speed,function(){
									for(i=1;i<=line;i++){
											_this.find("li:first").appendTo(_this);
									}
									_this.css({marginTop:0});
							});
					}
					//鼠标事件绑定
					_this.hover(function(){
							clearInterval(timerID);
					},function(){
							timerID=setInterval("scrollUp()",timer);
					}).mouseout();
			}       
		});
	})(jQuery);
	
	$(document).ready(function(){
		$("#note_con").Scroll({line:1,speed:500,timer:4000});
	});
</script>
		</div>
		<div id="friendlink">
			<div class="titleBg">友情链接</div>
			<div class="friendlink_con">
				<a href="http://www.ruc.edu.cn/" target="_blank"><img src="images/yqlj/1.jpg"/></a>
				<a href="http://portal.ruc.edu.cn/idc/" target="_blank"><img src="images/yqlj/2.png"/></a>
				<a href="http://www.ruc.edu.cn/" target="_blank"><img src="images/yqlj/3.jpg"/></a>
				<a href="http://www.ruc.edu.cn/" target="_blank"><img src="images/yqlj/4.png"/></a>
				<a href="http://lexue.tcl.com/" target="_blank"><img src="images/yqlj/5.jpg"/></a>
				<a href="http://youthruc.ruc.edu.cn/" target="_blank"><img src="images/yqlj/6.png"/></a>
				<a href="http://alumni.rbs.org.cn/" target="_blank"><img src="images/yqlj/7.png"/></a>
				<a href="http://www.ruc.edu.cn/" target="_blank"><img src="images/yqlj/8.png"/></a>
				<a href="http://www.ruc.edu.cn/" target="_blank"><img src="images/yqlj/9.png"/></a>
			</div>
		</div>
	</div>
</div>
<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>