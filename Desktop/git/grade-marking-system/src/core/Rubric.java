package core;

import controller.RubricItemController;

public class Rubric {
	private String rubricID;
	private String rubricName;
	
	public Rubric(String rubricName) {
		this.rubricName = rubricName;
	}
	
	public Rubric(String rubricID, String rubricName) {
		this.rubricID = rubricID;
		this.rubricName = rubricName;
	}
	
	public String getID() {
		return rubricID;
	}
	
	public String getName() {
		return rubricName;
	}
	
	public void setID(String id) {
		rubricID = id;
	}
	
	public void setName(String name) {
		rubricName = name;
	}
	
	public String toString() {
		return rubricName;
		
	}
	
	public Boolean isAvaliable() {
		if (RubricItemController.getTotalWeight(this) != 100.0) {
			return false;
		}
		return true;
	}
}
