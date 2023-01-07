package com.rg.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
     Connection con=null;
     Statement st=null;
     ResultSet rs=null;
     
     try {
    	 //register jdbc driver class
    	 Class.forName("oracle.jdbc.driver.OracleDriver");
    	 //establish connection
    	 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
    	 //create statement object
    	 if (con!=null)
    	 st=con.createStatement();
    	 //prepare SQL query
    	 String query="SELECT*FROM STUDENT WHERE AVG=(SELECT MAX(avg)FROM STUDENT)";
    	 System.out.println(query);
    	 //send and execute SQL query
    	 if(st!=null)
    		 rs=st.executeQuery(query);
    	 if (rs!=null) {
    		 boolean isRSEmpty=true;
    		 while(rs.next()) {
    			 isRSEmpty=false;
    			 System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
    			 
    		 }
    		 if(isRSEmpty)
    			 System.out.println("No record found");
    		 else
    			 System.out.println("records found and displayed");
    		 
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
     }

 }
}
