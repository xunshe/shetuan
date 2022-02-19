package com.nkl.common.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;

import org.apache.log4j.Logger;

public class BaseDao {
	
	 private static Logger log = Logger.getLogger(BaseDao.class);
	 private static ConnPoll connpoll=ConnPoll.getConnPollInstance();//数据库连接池对象	   

	/**
	 * 构造方法
	 */
	public BaseDao() {

	}

	/**
	 * 得到数据库连接
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @return 数据库连接
	 */
	public static Connection getConnection() {
		return connpoll.getConn(); //获得数据库连接
	}

	/**
	 * 释放资源
	 * 
	 * @param conn
	 *            数据库连接
	 * @param pstmt
	 *            PreparedStatement对象
	 * @param rs
	 *            结果集
	 */
	public static void closeDB(ResultSet rs, PreparedStatement pstmt,
			Connection conn) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if (conn != null) {
				if (!conn.isClosed()) {
					conn.close();
				}
				conn = null;
			}
		} catch (SQLException e) {
			log.error("数据库关闭失败!", e);
		}
	}

	/**
	 * 设置参数
	 * 
	 * @param values
	 * @throws SQLException
	 */
	private static void setParam(Object[] param, PreparedStatement pstmt)
			throws SQLException {
		// 判断是否有参数
		if (param != null) {
			for (int i = 0; i < param.length; i++) {
				pstmt.setObject(i + 1, param[i]); // 为预编译sql设置参数
			}
		}
	}

	/**
	 * 执行数据库查询或更新，但不关闭数据库
	 * 
	 * @param preparedSql
	 *            预编译的 SQL 语句
	 * @param param
	 *            预编译的 SQL 语句中的‘？’参数的字符串数组
	 * @param flag
	 *            0标示更新 1标示查询
	 * @return 查询结果
	 */
	public static Result execute(String preparedSql, Object[] param, int flag, Connection conn) {
		PreparedStatement pstmt = null; // 预编译的sql语句对象
		ResultSet rs = null; // 结果集对象
		Result result = null;
		try {
			pstmt = conn.prepareStatement(preparedSql); // 得到PreparedStatement对象
			setParam(param, pstmt);
			if (flag == 0) {
				pstmt.executeUpdate(); // 执行SQL语句
			} else {
				rs = pstmt.executeQuery(); // 获取结果集
				if (rs != null) {
					result = ResultSupport.toResult(rs); // 将结果集保存在Result中
				}
			}
			return result;
		} catch (SQLException e) {
			log.error("BaseDao执行execute异常：", e);
			throw new RuntimeException("BaseDao执行execute异常：", e);
		} finally {
			closeDB(rs, pstmt, null);
		}
	} 

	/**
	 * 执行SQL语句，可以进行增、删、改的操作，不能执行查询
	 * 
	 * @param sql
	 *            预编译的 SQL 语句
	 * @param param
	 *            预编译的 SQL 语句中的‘？’参数的字符串数组
	 * @return 影响的条数
	 */
	public static int executeUpdate(String preparedSql, Object[] param, Connection conn) {
		PreparedStatement pstmt = null; // 预编译的sql语句对象
		int num = -1;
		try {
			pstmt = conn.prepareStatement(preparedSql); // 得到PreparedStatement对象
			setParam(param, pstmt);
			num = pstmt.executeUpdate(); // 执行SQL语句
			return num;
		} catch (SQLException e) {
			log.error("BaseDao执行executeSQL异常：", e);
			throw new RuntimeException("BaseDao执行executeSQL异常：", e);
		} finally {
			closeDB(null, pstmt, null);
		}

	}

	/**
	 * 执行查询语句
	 * 
	 * @param param
	 *            预编译的 SQL 语句中的‘？’参数的字符串数组
	 * @return
	 */
	public static Object executeQueryObject(String preparedSql, Object[] param, Connection conn) {
		PreparedStatement pstmt = null; // 预编译的sql语句对象
		ResultSet rs = null; // 结果集对象
		Object object = null;
		try {
			pstmt = conn.prepareStatement(preparedSql); // 得到PreparedStatement对象

			setParam(param, pstmt); // 设置参数

			rs = pstmt.executeQuery(); // 获取结果集

			if (rs.next()) {
				object = rs.getObject(1);
			}

			return object; // 返回Result
		} catch (SQLException e) {
			log.error("BaseDao执行executeQueryObject异常：", e);
			throw new RuntimeException("BaseDao执行executeQueryObject异常：", e);
		} finally {
			closeDB(rs, pstmt, null);
		} 
	}
	
	/**
	 * 执行查询语句
	 * 
	 * @param param
	 *            预编译的 SQL 语句中的‘？’参数的字符串数组
	 * @return
	 */
	public static Result executeQuery(String preparedSql, Object[] param, Connection conn) {
		PreparedStatement pstmt = null; // 预编译的sql语句对象
		ResultSet rs = null; // 结果集对象
		try {
			pstmt = conn.prepareStatement(preparedSql); // 得到PreparedStatement对象

			setParam(param, pstmt); // 设置参数

			rs = pstmt.executeQuery(); // 获取结果集

			Result result = ResultSupport.toResult(rs); // 将结果集保存在Result中

			return result; // 返回Result
		} catch (SQLException e) {
			log.error("BaseDao执行executeQuery异常：", e);
			throw new RuntimeException("BaseDao执行executeQuery异常：", e);
		} finally {
			closeDB(rs, pstmt, null);
		} 
	}

	/**
	 * 执行查询语句,通过反射获取实体类的集合
	 * @param className 实体类名 
	 * @param sql 查询的sql语句
	 * @param params  预编译的 SQL 语句中的‘？’参数的字符串数组
	 * @param conn 数据库连接对象
	 * @return 实体类的集合List
	 */
	public static List<Object> executeQuery(String className,String sql, Object[] params,Connection conn)
	{
	    PreparedStatement pstmt=null;
	    ResultSet rs=null;
		try 
		{
			pstmt = conn.prepareStatement(sql);  // 得到PreparedStatement对象				
			setParam(params,pstmt);  //设置参数				
			rs = pstmt.executeQuery();  //获取结果集						
			return getEntityListByInvoke(className, rs);  //返回结果集
		} 
		catch (SQLException e) 
		{
			log.debug("Basedao执行executeQuery异常：",e);		 
			throw new RuntimeException("BaseDao执行executeQuery异常：", e);
		}
		finally
		{
			 closeDB(rs,pstmt,null);  // 释放资源
		}
	}
	
	/**
	 * 执行存储过程
	 * 
	 * @param sql
	 *            存储过程语句
	 * @param param
	 *            存储过程参数值
	 * @param type
	 *            存储过程参数的类型
	 * @return 输出参数的集合
	 */
	public static List<Object> executeCall(String sql, Object[] params,
			String[] type, Connection conn) {
		CallableStatement cstmt = null; // 预编译的sql语句对象
		ResultSet rs = null; // 结果集对象
		List<Object> list = new ArrayList<Object>();
		try {
			cstmt = conn.prepareCall(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					if ("IN".equals(type[i])) {
						cstmt.setObject(i + 1, params[i]);// 设置输入参数值
					} else {
						cstmt.registerOutParameter(i + 1,
								Integer.parseInt(params[i].toString()));// 注册输出参数
					}
				}
			}

			cstmt.execute();// 调用存储过程
			rs = cstmt.getResultSet();
			if (rs != null) {
				Result result = ResultSupport.toResult(rs); // 将结果集保存在Result中
				list.add(result);// 封装执行结果集
			}
			for (int i = 0; i < params.length; i++) {
				if ("OUT".equals(type[i])) {
					list.add(cstmt.getObject(i + 1));// 封装输出参数值
				}
			}
		} catch (SQLException e) {
			log.error("BaseDao执行executeCall异常：", e);
			throw new RuntimeException("BaseDao执行executeCall异常：", e);
		} finally {
			closeDB(rs, cstmt, null);
		} 
		return list;
	}
	
	/**
	 * 通过反射获取 ResultSet 包含的相应类实体的集合
	 * @param rs 查询结果集
	 * @param className 实体类的类名
	 * @return 实体类集合
	 */
	public static List<Object> getEntityListByInvoke(String className, ResultSet rs)
	{   
		List<Object> list = new ArrayList<Object>();
		try 
		{  
			while (rs.next()) 
			{   
				Object o = Class.forName(className).newInstance(); 			       
			    ResultSetMetaData  meta = rs.getMetaData();    
			    for (int i = 1; i <= meta.getColumnCount(); i++) 
			    {   
			        String fieldName = meta.getColumnName(i); // 获取表的字段名称    				          
			        Object value = rs.getObject(i); // 获取数据库表字段的值    			          
			        invoke(o, fieldName, value);   // 把值保存到o 
			    }
			    list.add(o);   
			}
		}
		catch (SQLException e)
		{
			log.error("反射获取实体类集合异常：", e);
			throw new RuntimeException("反射获取实体类集合异常：", e);
		}
		catch (InstantiationException e)
		{
			log.error("反射获取实体类集合异常：", e);
			throw new RuntimeException("反射获取实体类集合异常：", e);
		}
		catch (IllegalAccessException e) 
		{
			log.error("反射获取实体类集合异常：", e);
			throw new RuntimeException("反射获取实体类集合异常：", e);
		}
		catch (ClassNotFoundException e)
		{
			log.error("反射获取实体类集合异常：", e);
			throw new RuntimeException("反射获取实体类集合异常：", e);
		}   
		return list;   
	}   		

	/**  
     * 通过反射为实体类相应字段赋值  
     * @param o 实体类对象  
     * @param fieldName  要赋值的字段名称 
     * @param value  要给该字段赋的值 
     */  
	public static void invoke(Object o, String fieldName, Object value) 
	{   	          
            /**
		     * 拼出字段的getter与setter方法名，比如字段是USER_NAME，则对应为getUserName setUserName
		     */
		    String field=Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
            String setMethodName = "set" + field;
            String getMethodName = "get" + field;	            
            
            Method getMethod = null;
            Method setMethod = null;
			try 
			{   
				getMethod = o.getClass().getMethod(getMethodName, new Class[] {});//通过以上拼出的方法 名称，获得这个GET方法对象
                
				setMethod = o.getClass().getMethod(setMethodName, new Class[]{getMethod.getReturnType()});//通过以上拼出的方法 名称，获得这个SET方法对象；这个需要传入一个参数，参数是GET方法的返回值 					
			} 
			catch (SecurityException e)
			{
				log.error("反射为实体类相应字段赋值异常：", e);
				throw new RuntimeException("反射为实体类相应字段赋值异常：", e);
			} 
			catch (NoSuchMethodException e) 
			{
				log.error("反射为实体类相应字段赋值异常：", e);
				throw new RuntimeException("反射为实体类相应字段赋值异常：", e);
			}
            
            try 
            {
				setMethod.invoke(o, new Object[] { formatObject(getMethod.getReturnType(),value) });// 调用拷贝对象的setXXX()方法 
			} 
            catch (IllegalArgumentException e) 
            {
            	log.error("反射为实体类相应字段赋值异常：", e);
            	throw new RuntimeException("反射为实体类相应字段赋值异常：", e);
			} 
            catch (IllegalAccessException e) 
            {
            	log.error("反射为实体类相应字段赋值异常：", e);
            	throw new RuntimeException("反射为实体类相应字段赋值异常：", e);
			} 
            catch (InvocationTargetException e)
            {
            	log.error("反射为实体类相应字段赋值异常：", e);
            	throw new RuntimeException("反射为实体类相应字段赋值异常：", e);
			}	               	       
    }   	
	
	/**
	 * @Title: formatObject
	 * @Description: 格式化反射参数类型
	 * @param clazz
	 * @param value
	 * @return
	 * @return Object
	 */
	@SuppressWarnings("rawtypes")
	private static Object formatObject(Class clazz,Object value){
		String clazzName = clazz.getName();
		if (value == null) {
			if ("int".equals(clazzName) || "double".equals(clazzName) || "float".equals(clazzName)) {
				return 0;
			}
			return value;
		}
		
		String valueClazzName = value.getClass().getName();
		
		if (clazzName.equals(valueClazzName)) {
			return value;
		}else {
			Object object = value;
			if ("int".equals(clazzName)) {
				object = Integer.parseInt(value.toString());
			}else if ("double".equals(clazzName)) {
				object = Double.parseDouble(value.toString());
			}else if ("float".equals(clazzName)) {
				object = Float.parseFloat(value.toString());
			}else if ("java.lang.String".equals(clazzName)) {
				object = value.toString();
			}
			return object;
		}
		
	}

}
