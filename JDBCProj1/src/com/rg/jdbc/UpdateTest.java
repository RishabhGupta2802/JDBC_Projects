package com.rg.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		try {
			sc=new Scanner(System.in);
			int no=0;
			String newAddrs=null;
			float newAvg=0.0f;
			if(sc!=null) {
				System.out.println("Enter the student no to update details");
				no=sc.nextInt();
				System.out.println("Enter new address for student");
				newAddrs=sc.next();
				System.out.println("Enter new average of the student");
				newAvg=sc.nextFloat();
			}
			newAddrs="'"+newAddrs+"'";
			//load jdbc driver class(optional)
			Class.forName("com.mysql.cj.jdbc.Driver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:mysql:///ntaj915db","root","root");
			//create statement obj
			if(con!=null)
               st=con.createStatement();
			//prepare SQL query
			String query="UPDATE STUDENT SET SADD ="+newAddrs+",AVG="+newAvg+"WHERE SNO="+no;
			System.out.println(query);
			//send and execute query
			int count=0;
			if(st!=null) {
				count=st.executeUpdate(query);
				
			}
			//process the result
			if(count==0) {
				System.out.println("no record found for updation");
				
			}
			else {
				System.out.println( count+" no. of record of update");
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
