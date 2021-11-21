package learn.Spring.SpringBoot.soap.services;

import java.util.*;

import org.springframework.stereotype.Component;

import learn.Spring.SpringBoot.soap.bean.Course;

@Component
public class CourseDetailsService {
	

	private static List<Course> courses = new ArrayList<>();

	static {
		Course course1 = new Course(1, "Spring", "This you should know");
		courses.add(course1);

		Course course2 = new Course(2, "SOAP", "Spring Boot SOAP Wsdl");
		courses.add(course2);

		Course course3 = new Course(3, "Spring Boot", "Intresting thing to know");
		courses.add(course3);

		Course course4 = new Course(4, "Spring Framework", "Understand the besic");
		courses.add(course4);
	}

	// find course 
	public Course findById(int id) {
		for (Course course : courses) {
			if (course.getId() == id)
				return course;
		}
		return null;
	}

	// list of courses
	public List<Course> findAll() {
		return courses;
	}
	//remove course
	public String deleteById(int id) {
		Iterator<Course> iterator = courses.iterator();
		while (iterator.hasNext()) {
			Course course = iterator.next();
			if (course.getId() == id) {
				iterator.remove();
				return "Succesfully Deleted";
			}
		}
		return "Failed! Try Again";
	}

	// updating course & new course
	
	public String addNew(String name, String description) {
		Course course = new Course(courses.size()+1, name, description);
		courses.add(course);
		
		return "Added Succesfully";

		
	}


}
