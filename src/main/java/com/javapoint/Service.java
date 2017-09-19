package com.javapoint;

import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.mariadb.jdbc.MySQLDataSource;

import com.google.gson.Gson;

@Path("/Questions")
public class Service    
{	

    @POST
    @Path("/Info")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String PostRequestForInfo(final Request request) {
    	ArrayList<String> requestSpecifications = getRequestSpecification(request);
    	
    	if(requestSpecifications.size()>2){
    		return "{\"problem\":\"there were too many specifications\"}";
    	}
    	
    	ArrayList<Fetcher> fetchers = new ArrayList<Fetcher>();
    	
    	for(String lookingFor : request.seeking){
    		switch (lookingFor) {
			case "titles":
    			FetchTitles myFetcher = null;
    			if(requestSpecifications.size()==2){
    	    		myFetcher = new FetchTitles(requestSpecifications.get(1), requestSpecifications.get(0), request);
    			}else if(requestSpecifications.size()==1){
    	    		myFetcher = new FetchTitles(requestSpecifications.get(0), request);
    	    	}else if(requestSpecifications.size()==0){
    	    		myFetcher = new FetchTitles(request);
    	    	}
        		fetchers.add(myFetcher);
    			break;
			
			case "levels":
				if(requestSpecifications.size()==0){
					FetchLevels levelFetcher = new FetchLevels();
					fetchers.add(levelFetcher);
    	    	}else{ return "{\"problem\":\"this request isn't programmed\"}"; }
				break;
				
			case "subtopics":
				if(requestSpecifications.size()==0){
					FetchSubtopics subtopicFetcher = new FetchSubtopics();
					fetchers.add(subtopicFetcher);
    	    	}else if(requestSpecifications.size()==1){
    	    		FetchSubtopics subtopicFetcher = new FetchSubtopics(requestSpecifications.get(0), request);
					fetchers.add(subtopicFetcher);
    			}else{ return "{\"problem\":\"this request isn't programmed\"}"; }
				break;
				
			default:
				return "{\"problem\":\"you are asking for " + request.seeking + " which is not yet programmed\"}";
			}
    	}
    	
    	Response theResonse = ReponseFactory.MakeResponse(fetchers);
    	Gson myGson = new Gson();
    	String jsonString = myGson.toJson(theResonse);
    	System.out.println(jsonString);
    	return jsonString;
    }

    @POST
    @Path("/{other}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String WrongPath(@PathParam("other") String invalidPath){

	    return "{\"problem\":\"problem with URL\","
	    		+ "\"here\":\"" + invalidPath + "\"" 
	    		+ "}";
    }
    
    private ArrayList<String> getRequestSpecification(Request requestBody){
    	ArrayList<String> ret = new ArrayList<String>();
    	if(requestBody.topics != null){ret.add("topics");}
    	if(requestBody.subtopics != null){ret.add("subtopics");}
    	if(requestBody.levels != null){ret.add("levels");}
    	if(requestBody.titles != null){ret.add("titles");}
    	return ret;
    }
}

