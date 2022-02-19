<%@ page language="java"  import="java.util.*" contentType="text/html; charset=GBK"%>
<%@ page import="com.jspsmart.upload.SmartUpload" %>
<%@ page import="com.jspsmart.upload.SmartUploadException" %>
<%@ page import="java.io.*" %>
<%@ page import="com.nxw.common.utilTool.FormatDate" %>
<%
//错误信息
String errorMsg = "";

//图片设置信息
String id = "";
String imgTitle = "";
String imgWidth = "";
String imgHeight = "";
String imgBorder = "";

//文件最大值
long maxSize = 1024*500;
//定义文件上传的类型
String types = ",gif,jpg,jpeg,png,bmp,";

SmartUpload mySmartUpload = new SmartUpload();
mySmartUpload.initialize(pageContext);
mySmartUpload.setMaxFileSize(maxSize);
mySmartUpload.upload();
com.jspsmart.upload.File file=mySmartUpload.getFiles().getFile(0);
//重命名该图片
String old_name=file.getFileName();
String file_name=FormatDate.formatDateToString(new Date(),"yyyyMMddHHmmssSSS")+old_name.substring(old_name.indexOf("."));
//获得图片存储路径
//String saveurl=request.getRealPath("/")+"images\\user\\huser\\"+file_name;
String current=application.getRealPath("/")+"img_artical";
String saveurl=current+"\\"+file_name;

//文件保存目录URL
String saveUrl  = request.getContextPath()+"/img_artical/"+file_name;

//获得文件扩展名
String fileExt = old_name.substring(old_name.indexOf(".")+1);

//检查文件大小
if(file.getSize()>maxSize){
  errorMsg = "上传文件大小超过限制!";
}
//检查文件扩展名
else if(types.indexOf((","+fileExt+",").toLowerCase())==-1){
  errorMsg = "上传文件扩展名是不允许的扩展名!";	
}
else
{
   id=mySmartUpload.getRequest().getParameter("id");
   imgTitle=mySmartUpload.getRequest().getParameter("imgTitle");
   imgWidth=mySmartUpload.getRequest().getParameter("imgWidth");
   imgHeight=mySmartUpload.getRequest().getParameter("imgHeight");
   imgBorder=mySmartUpload.getRequest().getParameter("imgBorder");
  //存储图片
  //mySmartUpload.save(saveurl);
  file.saveAs(saveurl, com.jspsmart.upload.File.SAVEAS_PHYSICAL);
}
if(errorMsg.equals("")){
   //插入图片，关闭层
   out.println("<html>");
   out.println("<head>");
   out.println("<title>Insert Image</title>");
   out.println("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
   out.println("</head>");
   out.println("<body>");
   out.println("<script type=\"text/javascript\">parent.KE.plugin[\"image\"].insert(\""+id+"\",\""+saveUrl+"\",\""+imgTitle+"\",\""+imgWidth+"\",\""+imgHeight+"\",\""+imgBorder+"\");</script>");
   //System.out.println("<script type=\"text/javascript\">parent.KE.plugin[\"image\"].insert(\""+id+"\",\""+saveUrl+"\",\""+imgTitle+"\",\""+imgWidth+"\",\""+imgHeight+"\",\""+imgBorder+"\");</script>");
   out.println("</body>");
   out.println("</html>");
}
else{
   out.println("<html>");
   out.println("<head>");
   out.println("<title>error</title>");
   out.println("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
   out.println("</head>");
   out.println("<body>");
   out.println("<script type=\"text/javascript\">alert(\""+errorMsg+"\");history.back();</script>");
   out.println("</body>");
   out.println("</html>");
}
%>
  