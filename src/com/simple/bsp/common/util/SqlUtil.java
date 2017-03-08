package com.simple.bsp.common.util;

public class SqlUtil {
	public  static final String database = "mysql";

	public static String getPageQuerySql(String sql, int pagenum, int rows) {
		String pageQuerySql = "";

		if ("oracle".equals(database)) {
			pageQuerySql = "select * from (select A.*,ROWNUM RN from (" + sql
					+ ")A where ROWNUM<=" + pagenum * rows + ") where RN>"
					+ (pagenum - 1) * rows + "";
		} else if ("mysql".equals(database)) {
			pageQuerySql = sql + " limit " + (pagenum - 1) * rows
					+ ", " + rows;
		}

		return pageQuerySql;
	}
}
