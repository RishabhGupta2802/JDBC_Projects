package com.rg.jdbc;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class BLOBRetrieveTestForOracle {
  private static final String RETRIEVE_ACTOR_QUERY="SELECT*FROM JDBC_ACTOR_TAB WHERE ACTORID=?";
	public static void main(String[] args) {
		try(Scanner sc= new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
				PreparedStatement ps=con.prepareStatement(RETRIEVE_ACTOR_QUERY);
				){
			//read input
			int aid=0;
			if(sc!=null) {
				System.out.println("Enter the actor id:");
				aid=sc.nextInt();
			}
			//set actor id as the query param value
             if(ps!=null)
            	 ps.setInt(1, aid);
             //execute the query
             try(ResultSet rs=ps.executeQuery()){
            	 //process the ResultSet
            	 if(rs!=null) {
            		 if(rs.next()) {
            			 System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
            			 //get the inputStream representing blob value
            			 InputStream is=rs.getBinaryStream(4);
            			 //create outputStream representing empty dest file
            			 OutputStream os=new FileOutputStream("new_pic.jpg");
            			 //perform file content copy operation
            			 IOUtils.copy(is,os);
            			 System.out.println("Photo retrieved to the file");
            			 
            		 }//if
            		 else {
            			 System.out.println("Record not found");
            			 return;
            		 }//else
            	 }//if
             }//try2
		}//try1
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main

}//class
