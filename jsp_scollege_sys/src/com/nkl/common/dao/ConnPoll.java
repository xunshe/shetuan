package com.nkl.common.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.log4j.Logger;

public class ConnPoll {
	private static Logger log = Logger.getLogger(ConnPoll.class);
	private static BasicDataSource bds=null;
	private static ConnPoll connpoll;
	 
	private  String path = "prop/database.properties";  //保存数据库连接信息的属性文件的相对路径
	 
	/**
	 * Constructor 
	 * @Title: ConnPoll
	 * @Description: 初始化数据源
	 */
	private ConnPoll()
	{
		Properties props = new Properties();
		try {
			props.load(ConnPoll.class.getClassLoader().getResourceAsStream(path));
			DataSource ds= BasicDataSourceFactory.createDataSource(props); 
			bds = (BasicDataSource) ds;
		} catch (FileNotFoundException e) {
			log.error("初始化数据源异常：未找到数据库配置文件", e);
			throw new RuntimeException("初始化数据源异常：未找到数据库配置文件", e);
		} catch (IOException e) {
			log.error("初始化数据源异常：读取数据库配置文件异常", e);
			throw new RuntimeException("初始化数据源异常：读取数据库配置文件异常", e);
		}catch (Exception e) {
			log.error("初始化数据源异常", e);
			throw new RuntimeException("初始化数据源异常", e);
		}
	}
	/**
	 * 单例模式得到连接池的一个实例
	 * @return 连接池
	 */
	public static ConnPoll getConnPollInstance()
	{
		if(connpoll==null)
		{
			connpoll=new ConnPoll();
		}
		return connpoll;
	}
	/**
	 * 从连接池得到一个连接
	 * @return Connection
	 */
	public synchronized  Connection getConn ()
	{
		Connection conn=null;
		try 
		{
			conn=bds.getConnection();
			//System.out.println("目前活跃数 "+bds.getNumActive());
			//System.out.println("目前空闲数 "+bds.getNumIdle());
		} 
		catch (SQLException e) 
		{
			log.error("数据库连接超时", e);	 
    		throw new RuntimeException("数据库连接超时!");
		} 
		return conn;
	}
	
	public static void main(String[] args) throws SQLException, InterruptedException
	{
		ConnPoll connpoll=ConnPoll.getConnPollInstance();
		Connection[] conn=new Connection[12];
		for(int i=0;i<conn.length;i++)
		{			
			conn[i]=connpoll.getConn();
			//System.out.println("第"+(i+1)+"个 "+conn[i]+"\n");
		}
	}
}
