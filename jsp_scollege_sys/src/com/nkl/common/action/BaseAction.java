package com.nkl.common.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import com.nkl.common.domain.BaseDomain;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = -3040899951717448806L;
	private static final String ERROR_MSG = "msg";
	private static final String ERROR_STACK = "errorStack";

	// action执行结果（true|flase）
	private boolean success = true;

	// action执行结果（json插件返回客户端）
	private Map<Object, Object> result = null;

	// action执行结果原因（json插件返回客户端）
	private Map<Object, Object> errorReason = new HashMap<Object, Object>();

	@JSON(name = "data")
	public Map<Object, Object> getResult() {
		return result;
	}

	public void setResult(Map<Object, Object> result) {
		this.result = result;
	}
	 
    /**
     * @Title: setResult
     * @Description: 设置输出接果
     * @param key
     * @param value
     * @return void
     */
    public void setResult(Object key, Object value) {
        if (result == null) {
            result = new HashMap<Object, Object>();
        }
        result.put(key, value);
    }
	
	@JSON(name = "err")
	public Map<Object, Object> getErrorReason() {
		return errorReason;
	}

	public void setErrorReason(Map<Object, Object> errorReason) {
		this.errorReason = errorReason;
	}

	/**
	 * @Title: setErrorReason
	 * @Description: 设置失败原因
	 * @param errorMsg
	 * @return void
	 */
	protected void setErrorReason(String errorMsg) {
		if (errorReason == null) {
			errorReason = new HashMap<Object, Object>();
		}

		setSuccess(false);
		this.errorReason.put(ERROR_MSG, errorMsg);
		this.errorReason.put(ERROR_STACK, "");
	}

	protected void setErrorReason(String errorMsg, Exception e) {
		if (errorReason == null) {
			errorReason = new HashMap<Object, Object>();
		}

		setSuccess(false);
		this.errorReason.put(ERROR_MSG, errorMsg);
		this.errorReason.put(ERROR_STACK, generateStackTrace(e));
	}

	/**
	 * @Title: generateStackTrace
	 * @Description: 生成异常堆栈字符串
	 * @param e
	 * @return
	 * @return String
	 */
	private String generateStackTrace(Exception e) {
		if (e == null) {
			return null;
		}
		StringBuffer stringBuffer = new StringBuffer();
		ByteArrayOutputStream byteArrayOutputStream = null;
		try {
			byteArrayOutputStream = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(byteArrayOutputStream));
			stringBuffer.append(byteArrayOutputStream.toString());
		} catch (Exception ex) {
		} finally {
			if (byteArrayOutputStream != null) {
				try {
					byteArrayOutputStream.close();
				} catch (IOException ex2) {
				}
			}
		}
		return stringBuffer.toString();
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	/**
	 * 缺省页行数.
	 */
	private static final int DEFAULT_PAGE_LIMIT = 12;

	/**
	 * 起始行号.
	 */
	private int start = 0;
	
	/**
	 * 页码.
	 */
	private int pageNo = 1;

	/**
	 * 行数.
	 */
	private int limit = DEFAULT_PAGE_LIMIT;

	/**
	 * 返回总行数.
	 */
	private int totalCount = 0;

	/**
	 * 排序字段（例sp.spCode）.
	 */
	private String sort;

	/**
	 * 正序|反序（例ASC）.
	 */
	private String dir;
	
	/**
     * 得到http session.
     * 
     * @return http session
     */
    protected final HttpSession getSession() {
        return getHttpServletRequest().getSession(true);
    }

    /**
     * 得到http request.
     * 
     * @return http request
     */
    protected final HttpServletRequest getHttpServletRequest() {
        HttpServletRequest request = ServletActionContext.getRequest();
        return request;
    }
    
    /**
     * 得到http session.
     * 
     * @return http request
     */
    protected final HttpSession getHttpSession() {
        HttpServletRequest request = ServletActionContext.getRequest();
        return request.getSession();
    }

    /**
     * 得到http response.
     * 
     * @return http response
     */
    protected final HttpServletResponse getHttpServletResponse() {
        HttpServletResponse response = ServletActionContext.getResponse();
        return response;
    }
    
    /**
     * 设置分页属性.
     * 
     * @param domain
     *            输入DO对象
     */
    protected void setPagination(BaseDomain domain) {
        // 设置分页属性: start,limit,sort,order
        domain.setStart((getPageNo() - 1) * getLimit());
        domain.setLimit(getLimit());
        domain.setSort(getSort());
        domain.setOrder(getDir());
    }
    
    public int getPageCount() {
    	if (limit!=0) {
    		return (totalCount+limit-1)/limit;
		}
		return totalCount;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
		if (this.pageNo <= 0) {
			this.pageNo = 1;
		}
	}
}
