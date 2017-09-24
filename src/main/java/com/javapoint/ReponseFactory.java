package com.javapoint;

import java.sql.*;
import java.util.ArrayList;

public class ReponseFactory {
	/*
	 * The db connection is managed in a way similar to page 319-321 of Java Programming The OO Approach
	 * I intend to move a copy of it to be used accessing the DB for my webservice
	 */
	
	static String driverInfo = "org.mariadb.jdbc.Driver";
	static String url = "jdbc:mariadb://localhost:3306/mathsfarm";
	static String username = "root";
	static String password = "ThisIsNotThePassword";
	
	public static Response MakeResponse(ArrayList<Fetcher> fetchers){
		
		try {
			System.out.println("Loading JDBC driver...");
			Class.forName(driverInfo);
			System.out.println("JDBC driver successfully loaded!");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		
		Connection connection = null;
		try {
			System.out.println("Connecting to the MySQL database...");
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("MySQL Database connected!");
						
		
			Response theResponse = new Response();

			for(Fetcher theFetcher : fetchers){
				
				String query = theFetcher.GetSQL();
				
				System.out.println(query);	
				PreparedStatement pstmt = connection.prepareStatement( query );
				ArrayList<PreparedStatementPOJO> pstmtParameters = theFetcher.getPreparedStatementParameters();
				for(PreparedStatementPOJO psPOJO : pstmtParameters){
					if(psPOJO.getParameterType() == "string"){
						pstmt.setString(psPOJO.getIndex(), psPOJO.getStringValue());
					}else{
						pstmt.setInt(psPOJO.getIndex(), psPOJO.getIntValue());
					}
				}
				ResultSet rs = pstmt.executeQuery( );
				
				theFetcher.AddReponse(rs, theResponse);	
				
				pstmt.close();	
			}
			
			return theResponse;
			
		} catch (SQLException e) {
			
			System.out.println(e.toString());
			return null;		
		} finally {
			if (connection != null) {
				System.out.println("Closing the connection....");
				try {
					connection.close();
					System.out.println("Connection closed....");
				} catch (SQLException ignore) {
				}
			}else{
				System.out.println("...the connection did not open");
			}
		}

	}
	
}
