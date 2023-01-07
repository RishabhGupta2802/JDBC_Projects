package com.rg.jdbc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BLOBInsertTestForOracle {
 private static final String INSERT_ACTOR_QUERY="INSERT INTO JDBC_ACTOR_TAB VALUES(ACTORID_SEQ.NEXTVAL,?,?,?)";
	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
				PreparedStatement ps=con.prepareStatement(INSERT_ACTOR_QUERY);
				){
			//read input values
			String actorName=null,actorAddrs=null,photoLocation=null;
			if(sc!=null) {
				System.out.println("Enter actor name::");
				actorName=sc.next();
				System.out.println("Enter actor address::");
				actorAddrs=sc.next();
				System.out.println("Enter actor photolocation::");
				photoLocation=sc.next();
				
			}
			//create stream pointing to photo file
			InputStream is=new FileInputStream(photoLocation);
			//set param value
			if(ps!=null) {
				ps.setString(1, actorName);
				ps.setString(2, actorAddrs);
				ps.setBinaryStream(3,is);
			}
			//execute query
			int count=0;
			if(ps!=null)
				count=ps.executeUpdate();
			//process the result 
			if(count==0)
				System.out.println("Record not inserted");
			else
				System.out.println("Record  inserted");
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main

}//class
