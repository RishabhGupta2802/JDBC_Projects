package com.rg.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateInsertTestMySql {
  private static final String INSERT_PERSON_DATE="INSERT INTO JDBC_PERSON_DATE VALUES (?,?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		try {
			sc=new Scanner(System.in);
			String pname=null,sdob=null,sdoj=null,sdom=null;
			int pid=0;
			if(sc!=null) {
				System.out.println("Enter the person id ");
				pid=sc.nextInt();
				System.out.println("Enter person name");
				pname=sc.next();
				System.out.println("Enter DOB(dd-MM-yyyy)");
				sdob=sc.next();
				System.out.println("Enter DOJ(MM-dd-yyyy)");
				sdoj=sc.next();
				System.out.println("Enter DOM(yyyy-MM-dd)");
				sdom=sc.next();
			}
			//convert string date values to java.sql.Date class objects
		//for DOB
			//to java.util.Date class obj
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date udob=sdf.parse(sdob);
			//to java.sql.Date class obj
			long ms=udob.getTime();
			java.sql.Date sqdob=new java.sql.Date(ms);
		//for DOJ
			//to java.util.Date class obj
			SimpleDateFormat sdf1=new SimpleDateFormat("MM-dd-yyyy");
			java.util.Date udoj=sdf1.parse(sdoj);
			//to java.sql.Date class obj
			long ms1=udoj.getTime();
			java.sql.Date sqdoj=new java.sql.Date(ms1);
		//for DOM
			//to java.sql.Date class obj
			java.sql.Date sqdom= java.sql.Date.valueOf(sdom);
			//register jdbc driver class
			Class.forName("com.mysql.cj.jdbc.Driver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:mysql:///ntaj915db","root","root");
			//create PrepareStatement obj
			if(con!=null) 
				ps=con.prepareStatement(INSERT_PERSON_DATE);
			//set the value to query params
			if(ps!=null) {
				ps.setInt(1,pid);
				ps.setString(2,pname);
				ps.setDate(3,sqdob);
				ps.setDate(4,sqdoj);
				ps.setDate(5,sqdom);
			}
			//execute the query
			int count=0;
			if(ps!=null) 
				count=ps.executeUpdate();
			//process the result
			if (count==0)
				System.out.println("Record not inserted");
			else
				System.out.println("Record inserted");
				
			
			
		}
		catch(ClassNotFoundException cnf) {
			  cnf.printStackTrace();
		  }
		  catch(SQLException se) {
			  se.printStackTrace();
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
