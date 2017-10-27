/**
 * 
 */
function AJAXrequest(myMessage){
	//This url will need to updated when deployed on the live system;
	this.url = "http://localhost:8080/MFQ-1.1-SNAPSHOT/rest/Questions/Info";
	this.message = myMessage;
	this.requestType = "POST";
	this.responseType = "json";
	//this.response = null;
}
//methods
AJAXrequest.prototype.makeRequest = function (responseHandler) {
	if (window.XMLHttpRequest) { // Mozilla, Safari, IE7+ ...
	    httpRequest = new XMLHttpRequest();
	} else if (window.ActiveXObject) { // IE 6 and older
	    httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
	}

	httpRequest.onreadystatechange = function(){
		if (httpRequest.readyState === XMLHttpRequest.DONE) {
		    
		    if (httpRequest.status === 200) {
		    	var response = JSON.parse(httpRequest.responseText);
		    	//response as a javascript object is passed to the response handler
		    	responseHandler(response);

			} else {
				//alert("Response status: " + httpRequest.responseType)
			    // there was a problem with the request, for example the response may contain a 404 (Not Found) or 500 (Internal Server Error) response code
			}
		} else {
			//alert("httpRequest.readyState: " + httpRequest.readyState)
		    // still not ready
		}
	};
	
	//third option is set to true to make the request Asyncronous as in AJAX
	httpRequest.open(this.requestType, this.url, true);
	httpRequest.setRequestHeader('Content-Type', 'application/json');
	httpRequest.send(this.message);
};