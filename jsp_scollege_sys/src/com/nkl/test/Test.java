package com.nkl.test;

import java.sql.Connection;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import com.nkl.common.dao.BaseDao;

public class Test {

	/**
	 * @Title: main
	 * @Description: TODO
	 * @param args
	 * @return void
	 */
	public static void main(String[] args) {
		Connection conn = BaseDao.getConnection();
		String preparedSql = "SELECT table_name FROM information_schema.tables WHERE table_schema='school'";
		Result result = BaseDao.executeQuery(preparedSql, null, conn);
		for (int i = 0; i < result.getRowCount(); i++) {
			Map<String, Object> map = result.getRows()[i];
			System.out.println(map.get("TABLE_NAME"));
		}
	}

}
