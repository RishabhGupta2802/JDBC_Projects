package com.rg.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class PersonAgeCalculatorAppUsingJavaCode {
  private static final String GET_DOB_QUERY="SELECT DOB FROM JDBC_PERSON_DATE WHERE PID=?";
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			sc=new Scanner(System.in);
			int pid=0;
			if(sc!=null) {
				System.out.println("Enter the person id:");
				pid=sc.nextInt();
			}
			//register jdbc driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
			//create PreparedStatement obj
			if(con!=null)
				ps=con.prepareStatement(GET_DOB_QUERY);
			// set the query param value
			if(ps!=null) {
				ps.setInt(1,pid);
			}
			//execute the query
			if(ps!=null)
				rs=ps.executeQuery();
			//process the resultset
			java.sql.Date sqdob=null;
			if(rs.next()) {
				sqdob=rs.getDate(1);
				java.util.Date sysDate=new Date();
				long dobMS=sqdob.getTime();
				long sysDateMS=sysDate.getTime();
				float age=(sysDateMS-dobMS)/(1000.0f*60.0f*60.0f*24.0f*365.25f);
				System.out.println("Person age::"+age);
			}
			else {
				System.out.println("person not found");
				
			}
				
				
		}
		catch(SQLException se) {
	      	  se.printStackTrace();
	        }
			 catch(Exception e) {
				 e.printStackTrace();
			 }
			 finally {
				 //close jdbc objs
				 try {
					 if(rs!=null)
						 rs.close();
				 }
				 catch(SQLException se) {
		        	  se.printStackTrace();
				 }
				 try {
					 if(ps!=null)
						 ps.close();
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
			 }
		

	}

}
