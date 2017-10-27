package com.javapoint;

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
	public String getToFetch(){
		return toFetch;
	}

	@Override
	public void addResponse(Response theResponse, ArrayList<String> theHandle) {
		theResponse.subtopics = new ArrayList<String>(theHandle);
	}

}
