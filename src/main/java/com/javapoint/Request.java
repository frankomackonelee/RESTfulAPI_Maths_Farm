package com.javapoint;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Request {
	/* Requests should look something like:
	 * 	
	 	{	"seeking":["titles"],
	  		"topics":["Algebra", "Shape"]
	 	}
	 */
    @XmlElement public String[] seeking;
    @XmlElement public String[] topics;
    @XmlElement public String[] subtopics;
    @XmlElement public String[] levels;
    @XmlElement public String[] titles;
}
