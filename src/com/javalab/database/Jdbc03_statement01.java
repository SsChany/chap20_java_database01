package com.javalab.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc03_statement01 {
	public static void main(String[] args) {
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		
		String dbId = "square";
		
		String dbPwd = "1234";
		
		Connection con = null;
		
		Statement stmt = null;
		
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			System.out.println("1. 드라이버 로드 성공!");
			
			con = DriverManager.getConnection(url, dbId, dbPwd);
			System.out.println("2. 커넥션 객체 성공!");
			
			stmt = con.createStatement();
			System.out.println("3. stmt 객체 생성 성공 : " + stmt);
			
			String sql = "select * from orders"; 
			
			rs = stmt.executeQuery(sql);
			//System.out.println("5. sql명령어 성공적으로 실행됨!! 결과 존재? : " + rs.next());
			System.out.println();
			//ORDER_ID, CUST_ID, BOOK_ID, SALEPRICE, ORDER_DATE
			while (rs.next()) {
				System.out.println(rs.getInt("order_id")+ "\t" + rs.getInt("cust_id") + "\t" + rs.getInt("book_id") + "\t" + rs.getInt("saleprice") +"\t"+ rs.getDate("order_date"));
			}
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 ERR! : " + e.getMessage());
		}catch (SQLException e) {
			System.out.println("SQL ERR! : " + e.getMessage());
		} finally {
			try {
				//자원 해제 (반납)
				
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("자원해제 ERR! : " + e.getMessage());
			}
		
		
			
		
		
		}
	}
}
