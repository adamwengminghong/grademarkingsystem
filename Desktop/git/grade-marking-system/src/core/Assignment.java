package core;

import controller.CourseController;

public class Assignment {
	private String assignmentID;
	private String courseID;
	private String assignmentName;
	private String assignmentWeight;
	
	public Assignment(String assignmentID, String courseID, String assignmentName, String assignmentWeight) {
		this.assignmentID = assignmentID;
		this.assignmentName = assignmentName;
		this.courseID = courseID;
		this.assignmentWeight = assignmentWeight;
	}
	
	public Assignment(String courseID, String assignmentName, String assignmentWeight) {
		this.assignmentName = assignmentName;
		this.courseID = courseID;
		this.assignmentWeight = assignmentWeight;
	}
	
	public String getCourseID() {
		return courseID;
	}
	
	public String getID() {
		return assignmentID;
	}
	
	public String getName() {
		return assignmentName;
	}
	
	public String getWeight() {
		return assignmentWeight;
	}
	
	public String toString() {
		return assignmentName + " (" + CourseController.getNameDB(this.getCourseID()) + ")" + " - " + assignmentWeight + "%";
	}

}
