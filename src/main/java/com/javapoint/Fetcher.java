package com.javapoint;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class Fetcher {
	protected String query;
	protected int indexCounter=1;
	protected ArrayList<PreparedStatementPOJO> preparedStatementParameters = new ArrayList<PreparedStatementPOJO>();
	
	public abstract String GetSQL();
	public abstract void AddReponse(ResultSet rs, Response theResponse) throws SQLException;
	public abstract ArrayList<PreparedStatementPOJO> getPreparedStatementParameters();
	
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
