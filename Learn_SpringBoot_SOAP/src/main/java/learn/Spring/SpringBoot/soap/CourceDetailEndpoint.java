package learn.Spring.SpringBoot.soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import learn.Spring.Stub.*;

@Endpoint
public class CourceDetailEndpoint {
/*
 * method
 * input -GetCourseDetailsRequest
 * output -GetCourseDetailsResponse
 * 
 * targetNamespace="some.uniqe.text" 
 * name ="GetCourseDetailsResponse"
 */
	@PayloadRoot(namespace = "some.uniqe.text" ,localPart = "GetCourseDetailsResponse")//accept req of namespace and the name we config
	@ResponsePayload // convert java obj into xml 
	public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {//@RequestPayload convert xml to java obj
		GetCourseDetailsResponse response =new GetCourseDetailsResponse();
		//Add some values in response payload
		CourseDetails courseDetails=new CourseDetails();
		courseDetails.setId(request.getId());
		courseDetails.setName("Spring boot by Ashwani");
		courseDetails.setDescription("I try to teach myself");
		
		response.setCourseDetails(courseDetails);
		return response;
	}
	
}
