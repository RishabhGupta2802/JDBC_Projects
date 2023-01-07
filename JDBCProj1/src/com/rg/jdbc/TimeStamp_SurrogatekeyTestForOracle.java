package com.rg.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;

public class TimeStamp_SurrogatekeyTestForOracle {
  private static final String INSERT_CUSTOMER_QUERY="INSERT INTO JDBC_CUSTOMER VALUES(JDBC_CID_SEQ.NEXTVAL,?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
	    Connection con=null;
	    PreparedStatement ps=null;
	   
	    try {
	    	sc=new Scanner(System.in);
	    	String cname=null,caddrs=null;
	    	float billAmt=0.0f;
	    	if(sc!=null) {
	    		System.out.println("Enter customer name::");
	    		cname=sc.next();
	    		System.out.println("Enter customer address::");
	    		caddrs=sc.next();
	    		System.out.println("Enter customer billAmount::");
	    		billAmt=sc.nextFloat();
	    		
	    	}
	    	//register with jdbc driver class
	    	Class.forName("oracle.jdbc.driver.OracleDriver");
	    	//establish the connection
	    	con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
	    	//create the preparedstatement obj
	    	if(con!=null)
	    		ps=con.prepareStatement(INSERT_CUSTOMER_QUERY);
	    	//set the query param
	    	if(ps!=null) {
	    		ps.setString(1,cname);
	    		ps.setString(2,caddrs);
	    		ps.setFloat(3,billAmt);
	    		ps.setTimestamp(4,Timestamp.valueOf(LocalDateTime.now()));
	    		
	    	}
	    	//execute the pre-compile SQL query
	    	int count=0;
	    	if(ps!=null)
	    		count=ps.executeUpdate();
	    	//process the result 
	    	if(count==0)
	    		System.out.println("Record not inserted");
	    	else
	    		System.out.println("Record inserted");
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
