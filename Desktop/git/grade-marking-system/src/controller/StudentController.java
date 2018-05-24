package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import core.Student;
import databases.SQLConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentController {
	
	public static ObservableList<Student> initStudentDB() {
		ObservableList<Student> list = FXCollections.observableArrayList();
		SQLConnect connection = new SQLConnect();
		Connection conn = connection.connetDB();
		try {
			String sql = "SELECT * FROM student";
			PreparedStatement ps = null;
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Student student = new Student(rs.getString("student_id"), rs.getString("student_surname"), rs.getString("student_firstname"), rs.getString("student_group"));
				list.add(student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.closeDB();
		}
		return list;
	}
	
	
	public static void addStudentDB(Student student) {
		SQLConnect connection = new SQLConnect();
		Connection conn = connection.connetDB();
		try {
			String sql = "INSERT INTO student (student_id, student_surname, student_firstname, student_group) VALUES(?, ?, ?, ?)";
			PreparedStatement ps = null;
			ps = conn.prepareStatement(sql);
			ps.setString(1, student.getID());
			ps.setString(2, student.getSurname());
			ps.setString(3, student.getFirstname());
			ps.setString(4, student.getGroup());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.closeDB();
		}
	}
	
	public static void clearStudentDB() {
		SQLConnect connection = new SQLConnect();
		Connection conn = connection.connetDB();
		try {
			String sql = "DELETE FROM student";
			PreparedStatement ps = null;
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.closeDB();
		}
	}
}
