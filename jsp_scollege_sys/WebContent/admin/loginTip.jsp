<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
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
    background: url(images/bt_yangs.gif) repeat-x;
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
        <TR><TD height=10></TD></TR>
        <TR><TD>
          <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%" height=24> 
            <TR>
              <TD class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">提示信息</TD>
              <TD class="edittitleright">&nbsp;</TD>
            </TR>
          </TABLE>  
        </TD></TR>
        <TR><TD style="border-left:1px solid #67a1fc;border-right:1px solid #67a1fc;">
          <Table border="0" cellspacing="1" cellpadding="1" width="100%" align="center"> 
            <TR class="editbody">
              <TD id="htmlerror"><br/>&nbsp;&nbsp;&nbsp;&nbsp;操作失败，请您检测以下错误：<br><UL><LI>由于您未登录或长时间未操作会话已失效，请重新登录</LI></UL></TD>
            </TR>
          </table>
        </TD></TR>
        <TR class="editbody"><TD height=30 align="center"  style="border:1px solid #67a1fc;border-top:0px;">
          <input type='button' value='确 定' name='BtnRet' class='btnstyle' onclick="window.parent.location='login.jsp'" />
        </TD></TR>
      </TABLE> 
  </body>
</html>