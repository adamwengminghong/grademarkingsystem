package core;

public class Student {
	
	private String studentID;
	private String studentSurname;
	private String studentFirstname;
	private String studentGroup;
	private String finalGrade;
	
	public Student(String studentID, String studentSurname, String studentFirstname, String studentGroup, String score) {
		this.studentID = studentID;
		this.studentSurname = studentSurname;
		this.studentFirstname = studentFirstname;
		this.studentGroup = studentGroup;
		finalGrade = score;
	}
	
	public Student(String studentID, String studentSurname, String studentFirstname, String studentGroup) {
		this.studentID = studentID;
		this.studentSurname = studentSurname;
		this.studentFirstname = studentFirstname;
		this.studentGroup = studentGroup;
	}

	
	public String getID() {
		return studentID;
	}
	
	public void setID(String id) {
		studentID = id;
	}
	
	public String getSurname() {
		return studentSurname;
	}
	
	public void setSurname(String surname) {
		studentSurname = surname;
	}
	
	public String getFirstname() {
		return studentFirstname;
	}
	
	public void setFirstname(String firstname) {
		studentFirstname = firstname;
	}
	
	public String getGroup() {
		return studentGroup;
	}
	
	public void setGroup(String group) {
		studentGroup = group;
	}
	
	public String toString() {
		return studentID + " - " + studentSurname + " " + studentFirstname;
	}
	
	public String getGrade() {
		return finalGrade;
	}
}
