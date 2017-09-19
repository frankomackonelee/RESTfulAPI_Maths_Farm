package com.javapoint;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Response {
	/*
	 * { 	"selectors": [	"subtopics",
	  						"titles"], 
		    "subtopics":[	"Algebraic manipulation"
		    				"Coordinates",
		    				"Functions"],
		    "titles":[	"Adding the next three terms to the sequences",
		    			"Find the equation of a line given the gradient and a point",
		    			"Solving one sided equations with negative coefficient for x"]
	 */
    @XmlElement public ArrayList<String> seeking;
    @XmlElement public ArrayList<String> topics;
    @XmlElement public ArrayList<String> subtopics;
    @XmlElement public ArrayList<String> levels;
    @XmlElement public ArrayList<String> titles;
}
