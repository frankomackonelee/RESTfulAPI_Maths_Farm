/** This is an example of the html we want generated!
 * 	<div>...
		<select id='title_select' class='selector'>
			<option value='Other'>Choose topic and subtopic</option>
		</select>
		<br>
		<input type='checkbox' id='bonus'>Include a bonus</input>
		<button type='button' id='updatebackground'>Change Background</button>
	</div>
 */
var emptySelectors = "NotSelected";

function fillElement(idName, selectorArray){
	
	var div = document.getElementById(idName);
	div.innerHTML = '';

	var option = document.createElement('option');
	option.setAttribute('value', emptySelectors);
	option.innerText = "";
	div.appendChild(option);	
	
	for (var i = 0; i < selectorArray.length; i++) {
		var option = document.createElement('option');
		option.setAttribute('value',selectorArray[i]);
		option.innerText = selectorArray[i];
		div.appendChild(option);		
	}
	
}

function changeResponse(domSelector){
	return function(){
			switch (domSelector.id) {
			case "topics":
				var request = {
						seeking: ["subtopics","titles"],
						topics: [domSelector.value]
				}	
				sendAJAXrequest(request);				
				break;
	
			case "subtopics":
			case "levels":
				var request = {
								seeking: ["titles"],
							};
				if(document.getElementById("subtopics").value!=emptySelectors){
					request.subtopics = [document.getElementById("subtopics").value];
				};
				if(document.getElementById("levels").value!=emptySelectors){
					request.levels = levels = [document.getElementById("levels").value];
				};
				sendAJAXrequest(request);
				break;
				
			case "titles":
				alert("I haven't programmed this one");
				break;
				
			default:
				break;
		}	
	}
}

function sendAJAXrequest(message, responseFunction){
	myAJAXRequest = new AJAXrequest(JSON.stringify(message));
	myAJAXRequest.makeRequest(receiveAJAXresponse);	
}

function receiveAJAXresponse(theResponse) {
	//theResponse is a javascript object
	if(!theResponse.hasOwnProperty("titles")){
		alert("I'm sorry but there are no questions meeting your criteria")
	}
	for (var name in theResponse) {
		switch (name) {
		case "topics":
			fillElement(name, theResponse.topics);
			break;

		case "subtopics":
			fillElement(name, theResponse.subtopics);
			break;

		case "levels":
			fillElement(name, theResponse.levels);
			break;
			
		case "titles":
			fillElement(name, theResponse.titles);
			break;
			
		default:
			break;
		}
	}
}

document.addEventListener("DOMContentLoaded",function(){	
	
	var childDivs = document.getElementById('questionSpecs').getElementsByTagName('select');
	
	for( i=0; i< childDivs.length; i++ )
	{
		childDiv = childDivs[i];
		childDiv.onchange = changeResponse(childDiv)
	}
	
	var request = {
			seeking: ["subtopics","levels","titles"],
	}	
	sendAJAXrequest(request);	
	  
});
