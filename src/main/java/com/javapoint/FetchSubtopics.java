package com.javapoint;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FetchSubtopics extends Fetcher {

	final private static String toFetch = "subtopic";

	public FetchSubtopics() {
		this.query = "SELECT DISTINCT " + toFetch + " FROM subtopic_list";
	}
	
	public FetchSubtopics(String provided, Request request) {
		// TODO Auto-generated method stub
		this.query = "SELECT " + toFetch + " ";
		
		switch (provided) {
		case "topics":
			this.query += "FROM subtopic_list "
						+ "WHERE " ;
			this.query += printOrCondition("topic", printSpacedStrings(request.topics, ", "));
			
			break;
			
		default:
			this.query = "Hasn't been written";
			break;
		}
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
			theResponse.subtopics = new ArrayList<String>(handle);
		}
	}

	@Override
	public ArrayList<PreparedStatementPOJO> getPreparedStatementParameters() {
		
		return this.preparedStatementParameters;
	}

}
