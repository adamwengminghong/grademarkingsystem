package core;


public class Course {
	private String courseID;
	private String courseName;
	
	public Course(String courseID, String courseName) {
		this.courseID = courseID;
		this.courseName = courseName;
	}
	
	public Course(String courseName) {
		this.courseName = courseName;
	}
	
	public String getName() {
		return courseName;
	}
	
	public String getID() {
		return courseID;
	}
	
	public String toString() {
		return courseName;
	}
}
