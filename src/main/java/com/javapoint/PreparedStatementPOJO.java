package com.javapoint;

public class PreparedStatementPOJO {
	private String parameterType;
	private int	index;
	private String stringValue;
	private int intValue;
	
	public PreparedStatementPOJO(int index, String value) {
		this.parameterType = "string";
		this.index = index;
		this.stringValue = value;
	}
	
	public PreparedStatementPOJO(int index, int value){
		this.parameterType = "int";
		this.index = index;
		this.index = value;		
	}
	
	public String getParameterType(){
		return parameterType;
	}
	
	public int getIndex(){
		return index;
	}
	
	public String getStringValue(){
		return stringValue;
	}
	
	public int getIntValue(){
		return intValue;
	}
}
