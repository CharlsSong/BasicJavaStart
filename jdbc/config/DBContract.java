package com.biz.jdbc.config;

public class DBContract {
	public final static String IO_LIST_TABLE = " TBL_IOLIST ";
	
	public static class CRUD{
		public final static String SELECT_ALL = "SELECT "
				+ " * FROM "
				+ IO_LIST_TABLE;
	}
	
	public static class IO_COLUMN {
		public static String IO_SEQ = "io_seq";
		public static String IO_DATE = "io_date";
		public static String IO_TIME = "io_time";
		public static String IO_INOUT = "io_inout";
		public static String IO_DNAME = "io_dname";
		public static String IO_PNAME = "io_pname";
		public static String IO_PRICE = "io_price";
		public static String IO_QTY = "io_qty";
		public static String IO_TOTAL = "io_total";
	}
	
	public static class DB_CONN{
		public static final String JDBC_DRIVER="oracle.jdbc.OracleDriver";
		public static final String URL="jdbc:oracle:thin:@localhost:1521:xe";
		public static final String USER_NAME="user1";
		public static final String PASSWORD="1234";
	}
}
