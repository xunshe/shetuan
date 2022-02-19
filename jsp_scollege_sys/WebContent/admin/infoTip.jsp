<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>提示信息</title>
<style type="text/css">
BODY { 
	FONT-SIZE: 12px; 
	color: #000000; 
	font-size: 75%; 
	background-color:#ffffff;    
	background-position:right bottom;
	background-repeat:no-repeat;
	overflow-y:auto;
}
.btnstyle {
	CURSOR: hand;
	CURSOR: pointer;
	COLOR: #000000;
	PADDING-TOP: 2px;
	PADDING-LEFT: 1px;
	PADDING-RIGHT: 2px;
	HEIGHT: 21px;
    background: url(bt_yangs.gif) repeat-x;
	background-image:url("images/bt_yangs.gif");
	border:#69a2fd 1px solid;
}
.editbody
{
    font-size:12px;
    color:#000000;
    background-color:#DFEDFF;
}
.edittitleleft
{
    width:8px;
    color:#000000;
    background-color:#f5f5f5;
    background-image:url("images/table_1.gif");
}
.edittitle
{
    font-size:12px;
    color:#000000;
    background-color:#f5f5f5;
    background-image:url("images/table_2.gif");
}
.edittitleright
{
    width:8px;
    color:#000000;
    background-color:#f5f5f5;
    background-image:url("images/table_3.gif");
}
</style>
</head>
<body>
    <Table border="0" cellspacing="0" cellpadding="0" align="center" width=400> 
        <TR><TD height="50"></TD></TR>
        <TR><TD>
          <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%" height=24> 
            <TR>
              <TD class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">提示信息</TD>
              <TD class="edittitleright">&nbsp;</TD>
            </TR>
          </TABLE>  
        </TD></TR>
        <TR><TD>
          <Table border="0" cellspacing="1" cellpadding="1" width="100%" align="center"> 
            <TR class="editbody">
              <s:if test="#attr.tipType=='success'"><TD id="htmlsuccess" height="60">&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="#attr.tip"/></TD></s:if>
              <s:else><TD id="htmlerror"><br/>&nbsp;&nbsp;&nbsp;&nbsp;操作失败，请您检测以下错误：<br><UL><LI><s:property value="#attr.tip"/></LI></UL></TD></s:else>
            </TR>
          </table>
        </TD></TR>
        <TR class="editbody"><TD height=30 align="center">
          <s:if test="#attr.url1!=null"><input type='button' value='<s:property value="#attr.value1"/>' name='BtnRet1' class='btnstyle' onclick="window.location='<s:property value="#attr.url1"/>'" /></s:if>
	      <s:if test="#attr.url2!=null"><input type='button' value='<s:property value="#attr.value2"/>' name='BtnRet2' class='btnstyle' onclick="window.location='<s:property value="#attr.url2"/>'" /></s:if>
	      <s:if test="#attr.url3!=null"><input type='button' value='<s:property value="#attr.value3"/>' name='BtnRet3' class='btnstyle' onclick="window.location='<s:property value="#attr.url3"/>'" /></s:if>
        </TD></TR>
      </TABLE> 
  </body>
</html>