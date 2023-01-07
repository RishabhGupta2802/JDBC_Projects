package com.rg.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		try {
			sc=new Scanner(System.in);
			float startAvg=0.0f,endAvg=0.0f;
			System.out.println("Enter start avg range");
			startAvg=sc.nextFloat();
			System.out.println("Enter end avg range");
			endAvg=sc.nextFloat();
			//load jdbc class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
			//create statement obj
			if(con!=null)
			st=con.createStatement();
			//prepare query
			String query="DELETE FROM STUDENT WHERE AVG>="+startAvg+"AND AVG<="+endAvg;
			System.out.println(query);
			//send and execute non-select query
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			if(count!=0)
				System.out.println("No record found");
			else
				System.out.println("record found and deleted");
			
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			
			}
			try {
				if(st!=null)
					st.close();
			
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
		}

	}

}
