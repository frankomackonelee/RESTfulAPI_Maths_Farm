package com.javapoint;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class Fetcher {
	protected String query;
	protected int indexCounter=1;
	protected ArrayList<PreparedStatementPOJO> preparedStatementParameters = new ArrayList<PreparedStatementPOJO>();
	
	public abstract String getToFetch();
	public abstract void addResponse(Response theResponse, ArrayList<String> theHandle);

	public String GetSQL() {
		return this.query;
	}

	public void fetchReponse(ResultSet rs, Response theResponse) throws SQLException {
		ArrayList<String> handle  = new ArrayList<String>();
		
		if(rs.isBeforeFirst()){
			while (rs.next()) {
				handle.add(rs.getString(1));
			}				
		}
		
		if(handle!=null && handle.size() != 0){
			System.out.println("Adding " + getToFetch() + "s to the response: " + handle.toString());
			addResponse(theResponse, handle);
		}
	}

	public ArrayList<PreparedStatementPOJO> getPreparedStatementParameters() {
		// TODO Auto-generated method stub
		
		return this.preparedStatementParameters;
	}
	
	protected String printSpacedStrings(String[] arr, String spacer){
		String list;
		int count = arr.length;
		if (count != 0){
			list = "";
			for (int i = 0; i < count; i++) {
				list += "?";
				list += i < count - 1 ? ", " : ""; 
				
				preparedStatementParameters.add(new PreparedStatementPOJO(indexCounter, arr[i]));
				indexCounter++;
			}				
		}else{
			list = "1";
		}
		return list;
	}

	protected String printSpacedNumbers(String[] arr, String spacer){
		String list;
		int count = arr.length;
		if (count != 0){
			list = "";
			for (int i = 0; i < count; i++) {
				list += "?";
				list += i < count - 1 ? ", " : " "; 
				
				preparedStatementParameters.add(new PreparedStatementPOJO(indexCounter, arr[i]));
				indexCounter++;
			}				
		}else{
			list = "1";
		}
		return list;
	}
	
	protected String printOrCondition(String field, String list){
		return field + " IN (" + list + ") ";
	}
}
