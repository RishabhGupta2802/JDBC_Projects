package com.rg.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTest {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		try {
			sc=new Scanner(System.in);
			System.out.println("Enter Student number");
			int no=sc.nextInt();
			System.out.println("Enter Student name");
			String name=sc.next();
			System.out.println("Enter Student address");
			String adds=sc.next();
			System.out.println("Enter Student average");
			float avg=sc.nextFloat();
			//convert input value as required sql query
			name="'"+name+"'";
			adds="'"+adds+"'";
			
			//load jdbc driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
		    //create statement object
			if(con!=null)
				st=con.createStatement();
			//prepare SQL query
			String query="INSERT INTO STUDENT VALUES("+no+","+name+","+adds+","+avg+")";
			System.out.println(query);
			//send and execute query
			if(st!=null) {
				int count=st.executeUpdate(query);
				if(count==0)
					System.out.println("Record not inserted");
				else
					System.out.println("record inserted");
				
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
	 


