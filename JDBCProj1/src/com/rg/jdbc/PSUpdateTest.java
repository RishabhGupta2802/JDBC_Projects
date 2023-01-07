package com.rg.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PSUpdateTest {
 private static final String UPDATE_STUDENT_BY_SNO="UPDATE STUDENT SET SADD=?,AVG=?WHERE SNO=?";
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
				
		try {
			sc=new Scanner(System.in);
		    int no=0; 
			String newAddrs=null;
			float newAvg=0.0f;
			if(sc!=null) {
				System.out.println("Enter student no to update his details");
				no=sc.nextInt();
				System.out.println("enter new address of the student");
				newAddrs=sc.next();
				System.out.println("enter new avg of the student");
				newAvg=sc.nextFloat();
			}
			//register with jdbc driver s/w
			Class.forName("com.mysql.cj.jdbc.Driver");
			//established the connection
			con=DriverManager.getConnection("jdbc:mysql:///ntaj915db","root","root");
			//create statement object
			if(con!=null)
				ps=con.prepareStatement(UPDATE_STUDENT_BY_SNO);
			//set pre-compiles SQL Query params values
			if(ps!=null) {
				ps.setString(1,newAddrs);
				ps.setDouble(2,newAvg);
				ps.setInt(3,no);
			}
			//send and execute the SQL Query
			int count=0;
			if(ps!=null) {
				count=ps.executeUpdate();
			}
			//process the result
			if(count==0)
				System.out.println("No record found for updation");
			else
				System.out.println(count+"No. of record found for updation");
				
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
