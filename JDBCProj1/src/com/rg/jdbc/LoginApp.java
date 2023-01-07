package com.rg.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginApp {

	public static void main(String[] args) {
		Scanner sc =null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			sc=new Scanner(System.in);
			String user=null,pwd=null;
			if(sc!=null) {
			System.out.println("Enter the username:");
			user=sc.next();
			System.out.println("Enter the password:");
			pwd=sc.next();
			}
			user="'"+user+"'";
			pwd="'"+pwd+"'";
			//load jdbc driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
			//create statement object
			if(con!=null)
				st=con.createStatement();
			//prepare SQL query
			String query="SELECT COUNT(*) FROM USERINFO WHERE USERNAME="+user+"AND PASSWORD="+pwd;
			System.out.println(query);
			//send and execute query
			if(st!=null)
				rs=st.executeQuery(query);
			//process the resultset 
			if(rs!=null) {
				rs.next();
				int count=rs.getInt(1); 
				
				if(count==0)
					System.out.println("Invalid credentials");
				else
					System.out.println("valid credentials");
				
			}
			
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
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
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

}
