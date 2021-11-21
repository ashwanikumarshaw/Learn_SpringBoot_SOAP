package learn.Spring.SpringBoot.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import learn.Spring.SpringBoot.soap.bean.Course;
import learn.Spring.SpringBoot.soap.services.CourseDetailsService;
import learn.Spring.Stub.*;

@Endpoint
public class CourceDetailEndpoint {
	
	@Autowired
	CourseDetailsService service;
	
	
/*
 * method
 * input -GetCourseDetailsRequest
 * output -GetCourseDetailsResponse
 * 
 * targetNamespace="some.uniqe.text" 
 * name ="GetCourseDetailsResponse"
 */
	@PayloadRoot(namespace ="some.uniqe.text" ,localPart = "GetCourseDetailsRequest")//accept req of namespace and the name we config
	@ResponsePayload // convert java obj into xml 
	public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
		
		Course course = service.findById(request.getId());

		return mapCourseDetails(course);
	}

	private GetCourseDetailsResponse mapCourseDetails(Course course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		
		response.setCourseDetails(mapCourse(course));
		
		return response;
	}
	private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses) {
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		for(Course course:courses) {
			
			CourseDetails mapCourse=mapCourse(course);
		response.getCourseDetails().add(mapCourse);
		}
		
		return response;
	}

	private CourseDetails mapCourse(Course course) {
		CourseDetails courseDetails = new CourseDetails();
		
		courseDetails.setId(course.getId());
		
		courseDetails.setName(course.getName());
		
		courseDetails.setDescription(course.getDescription());
		return courseDetails;
	}
	
	@PayloadRoot(namespace ="some.uniqe.text" ,localPart = "GetAllCourseDetailsRequest")//accept req of namespace and the name we config
	@ResponsePayload // convert java obj into xml 
	public GetAllCourseDetailsResponse processAllCourseDetailsRequest(@RequestPayload GetAllCourseDetailsRequest request) {
		
		List<Course> courses = service.findAll();

		return mapAllCourseDetails(courses);
	}
	
	@PayloadRoot(namespace ="some.uniqe.text" ,localPart = "DeletCourseDetailsRequest")//accept req of namespace and the name we config
	@ResponsePayload // convert java obj into xml 
	public DeletCourseDetailsResponse processDeletCourseDetailsRequest(@RequestPayload DeletCourseDetailsRequest request) {
		
		String status = service.deleteById(request.getId());

		DeletCourseDetailsResponse response =new DeletCourseDetailsResponse();
		response.setStatusString(status);
		return  response;
	}
	@PayloadRoot(namespace ="some.uniqe.text" ,localPart = "AddCourseDetailsRequest")//accept req of namespace and the name we config
	@ResponsePayload // convert java obj into xml 
	public AddCourseDetailsResponse processAddCourseDetailsRequest(@RequestPayload AddCourseDetailsRequest request) {
		
		String status = service.addNew(request.getName(),request.getDescription());

		AddCourseDetailsResponse response =new AddCourseDetailsResponse();
		response.setStatusString(status);
		return  response;
	}
	
	
}
