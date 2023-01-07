package com.rg.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlToOracleDBTransferRecordsOperation {
  private static final String ORACLE_STUDENT_INSERT_QUERY="INSERT INTO STUDENT VALUES(ORA_SNO_SEQ.NEXTVAL,?,?,?)";
  private static final String MYSQL_GET_STUDENT_QUERY="SELECT SNAME,SADD,AVG FROM STUDENT";
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");//for oracle
			Class.forName("com.mysql.cj.jdbc.Driver");//Mysql
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		try(
				//establish the connection
				Connection oraCon=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
				Connection mysqlCon=DriverManager.getConnection("jdbc:mysql:///ntaj915db","root","root");
				//create statement objs
				Statement mysqlST=mysqlCon.createStatement();
				PreparedStatement oraPS=oraCon.prepareStatement(ORACLE_STUDENT_INSERT_QUERY);
				//execute the Query on student db table of mysql
				ResultSet rs=mysqlST.executeQuery(MYSQL_GET_STUDENT_QUERY);){
		
	
	//process the ResultSet(mysql) and insert each record into oracle DB table
	if(rs!=null) {
		while(rs.next()) {
			//get each record from mysql db table though RS
			String name=rs.getString(1);
			String addrs=rs.getString(2);
		    float avg=rs.getFloat(3);
		    //set each recieved record values to Insert SQL QUERY of oracle
		    oraPS.setString(1, name);
		    oraPS.setString(2, addrs);
		    oraPS.setFloat(3, avg);
		    //execute the Query
		    oraPS.executeUpdate();
		}
		System.out.println("Record are transffered from mysql DB table to oracle db table");
	}//if

}//try
		catch(SQLException se) {
			se.printStackTrace();
			System.out.println("Problem in transfering record");
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Problem in transfering record");
		}
	
}	
	

}
	
	
	
	
	
	
	
	
