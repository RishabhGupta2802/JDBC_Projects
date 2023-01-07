package com.rg.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest8_Mysql {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			//load jdbc driver class(optional)
			Class.forName("com.mysql.cj.jdbc.Driver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:mysql:///ntaj915db","root","root");
			//create Statement object
			if(con!=null)
			st=con.createStatement();
			//prepare sql query
            String query="SELECT * FROM STUDENT";
            //EXECUTE QUERY
            if(st!=null)
            	rs=st.executeQuery(query);
            //process the ResultSet obj
            if(rs!=null) {
            	boolean isRSEmpty=true;
                while(rs.next()) {
                	isRSEmpty=false;
                	System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
                	
                }
			if(isRSEmpty) {
				System.out.println("record not found");
				
			}
			else
			{
				System.out.println("record found and displayed");
			}
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
