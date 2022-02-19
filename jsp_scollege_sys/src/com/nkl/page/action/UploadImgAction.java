package com.nkl.page.action;

import java.util.Date;

import com.nkl.common.action.BaseUploadAction;
import com.nkl.common.util.DateUtil;
import com.nkl.common.util.FindProjectPath;
import com.nkl.common.util.Param;
import com.nkl.common.util.Print;
import com.nkl.common.util.UploadFile;

@SuppressWarnings("serial")
public class UploadImgAction extends BaseUploadAction {
	/**
	 * 文件上传
	 */
	public String execute() {
		Print.println("进入execute方法");
		//重命名该图片
		String old_name=getUploadFileName();
		String file_name=DateUtil.dateToDateString(new Date(),"yyyyMMddHHmmssSSS")+old_name.substring(old_name.indexOf("."));
		//设置保存文件位置
		String saveFile=FindProjectPath.getRootPath(super.getSavePath()+"/"+file_name);
		//String saveFile="d:/"+file_name;
		//上传文件
		String errorString=UploadFile.upload(getUpload(), saveFile, getUploadContentType(), getUpload().length(), getAllowedTypes(), getMaximunSize());
		//判断上传结果
		if(!"".equals(errorString))
		{
			Print.println(errorString);
			Param.setAttribute("tip", "no");
			Param.setAttribute("errorString", errorString);
			return INPUT;
		}
		Param.setAttribute("tip", "ok");
		Param.setAttribute("filename",file_name);
		return SUCCESS;
	}
}
