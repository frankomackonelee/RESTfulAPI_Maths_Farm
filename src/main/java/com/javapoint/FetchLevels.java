package com.javapoint;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FetchLevels extends Fetcher {

	final private static String toFetch = "level";

	public FetchLevels() {
		this.query = "SELECT DISTINCT " + toFetch + " FROM question_difficulty ORDER BY " + toFetch;
	}
	
	@Override
	public String GetSQL() {
		return this.query;
	}

	@Override
	public void AddReponse(ResultSet rs, Response theResponse) throws SQLException {
		ArrayList<String> handle  = new ArrayList<String>();
		
		if(rs.isBeforeFirst()){
			while (rs.next()) {
				handle.add(rs.getString(1));
			}				
		}
		
		if(handle!=null && handle.size() != 0){
			System.out.println("Adding " + toFetch + "s the response: " + handle.toString());
			theResponse.levels = new ArrayList<String>(handle);
		}
	}

	@Override
	public ArrayList<PreparedStatementPOJO> getPreparedStatementParameters() {
		// TODO Auto-generated method stub
		
		return this.preparedStatementParameters;
	}

}
