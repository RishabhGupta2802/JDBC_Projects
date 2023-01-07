package com.rg.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
     Connection con=null;
     Statement st=null;
     ResultSet rs=null;
     try {
    	 //register jdbc driver class
    	 Class.forName("oracle.jdbc.driver.OracleDriver");
    	 //establish the connection
    	 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
    	 //create Statement obj
    	 if(con!=null)
    	 st=con.createStatement();
         //prepare query
    	 String query="SELECT COUNT(*) FROM EMP";
    	 System.out.println(query);
    	 //send and execute query
    	 if(st!=null)
    		 rs=st.executeQuery(query);
    	 //process resultset obj
    	 if (rs!=null) {
    		 rs.next();
    		 int count=rs.getInt("count(*)");
    		 System.out.println("emp table record count is::"+count);
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
	}

}
}
