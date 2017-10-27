package com.javapoint;

import java.util.ArrayList;

public class FetchLevels extends Fetcher {

	final private static String toFetch = "level";

	public FetchLevels() {
		this.query = "SELECT DISTINCT " + toFetch + " FROM question_difficulty ORDER BY " + toFetch;
	}
	
	@Override
	public String getToFetch(){
		return toFetch;
	}

	@Override
	public void addResponse(Response theResponse, ArrayList<String> theHandle) {
		theResponse.levels = new ArrayList<String>(theHandle);
	}

}
