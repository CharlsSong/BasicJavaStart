package com.biz.jdbc.exec;

import com.biz.jdbc.service.IoService;

public class JDBC_Ex02 {
	public static void main(String[] args) {
		IoService ios = new IoService();
		ios.selectAll();
		
	}
}
