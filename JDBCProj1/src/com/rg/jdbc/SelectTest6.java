package com.rg.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			sc=new Scanner(System.in);
			System.out.println("enter emp no");
			int no=sc.nextInt();
			//register jdbc driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
			//create statement obj
			if(con!=null)
				st=con.createStatement();
			//prepare SQL query
			String query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE EMPNO="+no;
			System.out.println(query);
			if(st!=null)
				rs=st.executeQuery(query);
			//process the resultset obj
			if(rs!=null) {
				if(rs.next()) {
					System.out.println("Record found");
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
					
				}
				else {
					System.out.println("record not found");
					
				}
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
