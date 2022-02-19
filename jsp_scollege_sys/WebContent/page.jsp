<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
 
		 <div id="page">
			共 ${totalCount} 条，第 ${pageNo}/${pageCount} 页
			<s:if test="pageNo > 1">
				&nbsp;　<a href="javascript:ChangePage('1');" class="alinkstyle">首页</a>&nbsp;&nbsp;<a href="javascript:ChangePage('${pageNo-1}');" class="alinkstyle">前页</a>
			</s:if>
			<s:else>
				&nbsp;　首页
			    &nbsp;前页
			</s:else>
			<s:if test="pageNo < pageCount">
		        <a href="javascript:ChangePage('${pageNo+1}');" class="alinkstyle">后页</a>&nbsp;&nbsp;<a href="javascript:ChangePage('${pageCount}');" class="alinkstyle">末页</a>
		    </s:if> 
		    <s:else>
				&nbsp;后页
			    &nbsp;末页
			</s:else>
	        &nbsp;　<input type="text" name="goPage" id="goPage" class="inputpagestyle" />
	        &nbsp;<input type="button" value="GO" onclick="GoPage();" class="btnstyle"/>
		 </div>
	 