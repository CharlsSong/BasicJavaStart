package com.biz.jdbc.exec;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.biz.jdbc.config.DBConnection;
import com.biz.jdbc.config.DBContract;

public class JDBC_EX01 {
	public static void main(String[] args) {
		PreparedStatement pst = null;
		String sql = DBContract.CRUD.SELECT_ALL;
		
		Connection dbConn = DBConnection.getDBConnection();
		try {
			pst = dbConn.prepareStatement(sql);
			ResultSet rst = pst.executeQuery();
			
			System.out.println("□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□");
			System.out.println("날짜\t시간\t거래처\t상품\t수량\t단가");
			System.out.println("□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□");
			while(rst.next()) {
				System.out.print(rst.getString(DBContract.IO_COLUMN.IO_DATE) + "\t");
				System.out.print(rst.getString(DBContract.IO_COLUMN.IO_TIME) + "\t");
				System.out.print(rst.getString(DBContract.IO_COLUMN.IO_DNAME) + "\t");
				System.out.print(rst.getString(DBContract.IO_COLUMN.IO_PNAME) + "\t");
				System.out.print(rst.getString(DBContract.IO_COLUMN.IO_QTY) + "\t");
				System.out.println(rst.getString(DBContract.IO_COLUMN.IO_PRICE));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
