package com.rg.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PS_LoginApp {
private static final String AUTH_QUERY="SELECT COUNT(*)FROM USERINFO WHERE USERNAME=? AND PASSWORD=?";
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//read input
			sc=new Scanner(System.in);
			String user=null,pwd=null;
			if(sc!=null) {
				System.out.println("Enter the username:");
				 user=sc.next();
				System.out.println("Enter the password:");
				 pwd=sc.next();
			}
			//register jdbc driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
			//create preparedstatement object having pre-compiled query
			if(con!=null) 
				ps=con.prepareStatement(AUTH_QUERY);
			//set param values to the pre-compile query
			if(ps!=null) {
				ps.setString(1,user);
				ps.setString(2,pwd);
				
			}
				//send and execute the query
			if(ps!=null)
				rs=ps.executeQuery();
			//process the ResultSet object
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
    	   //close jdbc obj
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
