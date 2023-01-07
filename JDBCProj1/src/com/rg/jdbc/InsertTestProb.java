package com.rg.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTestProb {
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		try {
			sc=new Scanner(System.in);
			int sal=0,empno=0;
			String ename=null,job=null;
			if(sc!=null) {
				System.out.println("Enter the employee no: ");
			    empno=sc.nextInt();
			    System.out.println("Enter the employee name:");
			    ename=sc.next();
			    System.out.println("Enter the employee salary:");
			    sal=sc.nextInt();
			    System.out.println("Enter the employee job:");
			    job=sc.next();
			}
			ename="'"+ename+"'";
			job="'"+job+"'";
			//load jdbc driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//established the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
			//create statement object
			if(con!=null)
				st=con.createStatement();
			//prepare SQl query
			String query="INSERT INTO EMP(EMPNO,ENAME,SAL,JOB) VALUES("+empno+","+ename+","+sal+","+job+")";
			 System.out.println(query);
			 //send and execute SQL query
			 if(st!=null) {
				 int count=st.executeUpdate(query);
				 //process 
				 if(count==0)
					 System.out.println("Record not inserted");
				 else
					 System.out.println("Record inserted");
				 
			 }
                          			
		}
		catch(SQLException se){
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
