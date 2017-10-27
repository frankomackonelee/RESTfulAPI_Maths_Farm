package com.javapoint;

import java.util.ArrayList;

public class FetchTitles extends Fetcher {
	final private static String toFetch = "title";
		
	public FetchTitles(Request request){
		this.query = "SELECT " + toFetch + " FROM question_title";
	}
	
	public FetchTitles(String provided, Request request) {
		// TODO Auto-generated method stub
		this.query = "SELECT " + toFetch + " ";
		
		switch (provided) {
		case "levels":
			this.query +=	"FROM question_title AS Q "
					+ "INNER JOIN question_difficulty AS D ON D.question_id=Q.question_id "	
					+ "WHERE " + printOrCondition("level", printSpacedNumbers(request.levels, ", "));			
			break;
			
		case "topics":
		case "subtopics":
			this.query += "FROM question_title AS Q "
						+ "INNER JOIN question_subtopics AS S ON S.question_id=Q.question_id "
						+ "INNER JOIN subtopic_list AS T ON S.subtopic_id=T.subtopic_id "
						+ "WHERE " ;
			if(provided == "topics"){
				this.query += printOrCondition("topic", printSpacedStrings(request.topics, ", "));
			}else{
				this.query += printOrCondition("T.subtopic", printSpacedStrings(request.subtopics, ", "));
			}
			
			break;
			
		default:
			this.query = "Hasn't been written";
			break;
		}
	}
	
	public FetchTitles(String provided1, String provided2, Request request){
		if(provided1 == "levels" || provided2 == "levels"){
			this.query = "SELECT DISTINCT Q.title "
					+ "FROM question_title AS Q " 
					+ "INNER JOIN question_subtopics AS S ON  Q.question_id=S.question_id "
					+ "INNER JOIN subtopic_list AS T ON T.subtopic_id=S.subtopic_id "
					+ "INNER JOIN question_difficulty AS D ON D.question_id=Q.question_id " 
					+ "WHERE " + printOrCondition("D.level", printSpacedNumbers(request.levels, ", "));		
			if(provided1 == "topics" || provided2 == "topics"){
				this.query +="AND " + printOrCondition("T.topic", printSpacedStrings(request.topics, ", "));
			}else if(provided1 == "subtopics" || provided2 == "subtopics"){
				this.query +="AND " + printOrCondition("T.subtopic", printSpacedStrings(request.subtopics, ", "));
			}else{
				this.query = "Hasn't been written";				
			}
		}else{
			this.query = "Hasn't been written";			
		}
	}
	
	@Override
	public String getToFetch(){
		return toFetch;
	}

	@Override
	public void addResponse(Response theResponse, ArrayList<String> theHandle) {
		theResponse.titles = new ArrayList<String>(theHandle);
	}
}
