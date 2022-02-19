<%@ page language="java"  import="java.util.*" contentType="text/html; charset=GBK"%>
<%@ page import="com.jspsmart.upload.SmartUpload" %>
<%@ page import="com.jspsmart.upload.SmartUploadException" %>
<%@ page import="java.io.*" %>
<%@ page import="com.nxw.common.utilTool.FormatDate" %>
<%
//������Ϣ
String errorMsg = "";

//ͼƬ������Ϣ
String id = "";
String imgTitle = "";
String imgWidth = "";
String imgHeight = "";
String imgBorder = "";

//�ļ����ֵ
long maxSize = 1024*500;
//�����ļ��ϴ�������
String types = ",gif,jpg,jpeg,png,bmp,";

SmartUpload mySmartUpload = new SmartUpload();
mySmartUpload.initialize(pageContext);
mySmartUpload.setMaxFileSize(maxSize);
mySmartUpload.upload();
com.jspsmart.upload.File file=mySmartUpload.getFiles().getFile(0);
//��������ͼƬ
String old_name=file.getFileName();
String file_name=FormatDate.formatDateToString(new Date(),"yyyyMMddHHmmssSSS")+old_name.substring(old_name.indexOf("."));
//���ͼƬ�洢·��
//String saveurl=request.getRealPath("/")+"images\\user\\huser\\"+file_name;
String current=application.getRealPath("/")+"img_artical";
String saveurl=current+"\\"+file_name;

//�ļ�����Ŀ¼URL
String saveUrl  = request.getContextPath()+"/img_artical/"+file_name;

//����ļ���չ��
String fileExt = old_name.substring(old_name.indexOf(".")+1);

//����ļ���С
if(file.getSize()>maxSize){
  errorMsg = "�ϴ��ļ���С��������!";
}
//����ļ���չ��
else if(types.indexOf((","+fileExt+",").toLowerCase())==-1){
  errorMsg = "�ϴ��ļ���չ���ǲ��������չ��!";	
}
else
{
   id=mySmartUpload.getRequest().getParameter("id");
   imgTitle=mySmartUpload.getRequest().getParameter("imgTitle");
   imgWidth=mySmartUpload.getRequest().getParameter("imgWidth");
   imgHeight=mySmartUpload.getRequest().getParameter("imgHeight");
   imgBorder=mySmartUpload.getRequest().getParameter("imgBorder");
  //�洢ͼƬ
  //mySmartUpload.save(saveurl);
  file.saveAs(saveurl, com.jspsmart.upload.File.SAVEAS_PHYSICAL);
}
if(errorMsg.equals("")){
   //����ͼƬ���رղ�
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
  