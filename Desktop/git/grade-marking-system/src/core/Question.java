package core;

import controller.RubricController;

public class Question {
	private String questionID;
	private String assignmentID;
	private String rubricID;
	private String questionName;
	private String questionWeight;

	public Question(String questionID, String assignmentID, String rubricID, String questionName, String questionWeight) {
		this.questionID = questionID;
		this.assignmentID = assignmentID;
		this.rubricID = rubricID;
		this.questionName = questionName;
		this.questionWeight = questionWeight;
	}
	
	
	public String getQuestionID() {
		return questionID;
	}
	
	public String getAssignmentID() {
		return assignmentID;
	}
	
	public String getName() {
		return questionName;
	}
	
	public String getWeight() {
		return questionWeight;
	}
	
	public String getRubricID() {
		return rubricID;
	}
	
	public String toString() {
		return questionName + " (" + RubricController.getNameDB(rubricID) + ") - " + questionWeight + "%";
	}
}
