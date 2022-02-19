package com.nkl.common.util;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
@SuppressWarnings({"rawtypes","unchecked"})
public class Param {
	
	public static String get(String key)
	{
		ActionContext ctx = ActionContext.getContext();
		return ctx.get(key)!=null?ctx.get(key).toString():null;
	}
	/**
	 * 模拟request.getParameter(key)
	 * @param key
	 * @return
	 */
	public static String getParameter(String key)
	{
		ActionContext ctx = ActionContext.getContext();
		String[] param=(String[])ctx.getParameters().get(key);
		if(param!=null&&param[0]!=null)
		{
			return param[0].toString();
		}
		return null;
	}
	/**
	 * 模拟request.getParameterValues(key)
	 * @param key
	 * @return
	 */
	public static String[] getParameterValues(String key)
	{
		ActionContext ctx = ActionContext.getContext();
		String[] param=(String[])ctx.getParameters().get(key);
		return param;
	}
	/**
	 * 模拟request.setAttribute(String key, Object value)
	 * @param key
	 * @return
	 */
	public static void setAttribute(String key, Object value)
	{
		ActionContext ctx = ActionContext.getContext();
		Map request = (Map)ctx.get("request");
		request.put(key, value);
	}
	/**
	 * 模拟request.getAttribute(String key)
	 * @param key
	 * @return
	 */
	public static Object getAttribute(String key)
	{
		ActionContext ctx = ActionContext.getContext();
		Map request = (Map)ctx.get("request");
		return request.get(key);
	}
	/**
	 * 模拟request.getSession().setAttribute(String key, Object value)
	 * @param key
	 * @param value
	 */
	public static void setSession(String key, Object value) {
		ActionContext ctx = ActionContext.getContext();
		Map session = ctx.getSession();
		session.put(key, value);
	}
	/**
	 * 模拟request.getSession().getAttribute(String key)
	 * @param key
	 * @param value
	 */
	public static Object getSession(String key) {
		ActionContext ctx = ActionContext.getContext();
		Map session = ctx.getSession();
		return session.get(key);
	}
	/**
	 * 模拟session销毁key
	 * @param key
	 */
	public static void removeSession(String key) {
		ActionContext ctx = ActionContext.getContext();
		Map session = ctx.getSession();
		if(session.containsKey(key))
		{
			session.remove(key);
		}
	}
	/**
	 * 模拟session销毁
	 */
	public static void clearSession() {
		ActionContext ctx = ActionContext.getContext();
		Map session = ctx.getSession();
		session.clear();
	}
	/**
	 * 模拟application.setAttribute(String key, Object value)
	 * @param key
	 * @param value
	 */
	public static void setApplication(String key, Object value) {
		ActionContext ctx = ActionContext.getContext();
		Map application = ctx.getApplication();
		application.put(key, value);
	}
	/**
	 * 模拟application.getAttribute(String key)
	 * @param key
	 * @param value
	 */
	public static Object getApplication(String key) {
		ActionContext ctx = ActionContext.getContext();
		Map application = ctx.getApplication();
		return application.get(key);
	}
	
	public static String getProjectPath(){
		ActionContext ac = ActionContext.getContext();      
        ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);      
        return sc.getRealPath("/");   
	}
	
	public static void main(String[] args) {
		
	}
}
