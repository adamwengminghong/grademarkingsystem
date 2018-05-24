package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import core.Course;
import core.Assignment;
import databases.SQLConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AssignmentController {
	
	public static ObservableList<Assignment> initAssignmentDB() {
		ObservableList<Assignment> list = FXCollections.observableArrayList();
		SQLConnect connection = new SQLConnect();
		Connection conn = connection.connetDB();
		try {
			String sql = "SELECT * FROM assignment";
			PreparedStatement ps = null;
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Assignment assignment = new Assignment(rs.getString("assignment_id"), rs.getString("assignment_course_id"), rs.getString("assignment_name"), rs.getString("assignment_weight"));
				list.add(assignment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.closeDB();
		}
		return list;
	}
	
	public static ObservableList<Assignment> initAssignmentDB(Course course) {
		ObservableList<Assignment> list = FXCollections.observableArrayList();
		SQLConnect connection = new SQLConnect();
		Connection conn = connection.connetDB();
		try {
			String sql = "SELECT * FROM assignment WHERE assignment_course_id = ?";
			PreparedStatement ps = null;
			ps = conn.prepareStatement(sql);
			ps.setString(1, course.getID());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Assignment assignment = new Assignment(rs.getString("assignment_id"), rs.getString("assignment_course_id"), rs.getString("assignment_name"), rs.getString("assignment_weight"));
				list.add(assignment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.closeDB();
		}
		return list;
	}
	
	
	public static void addAssignment(Course course, Assignment assignment) {
		SQLConnect connection = new SQLConnect();
		Connection conn = connection.connetDB();
		try {
			String sql = "INSERT INTO assignment (assignment_course_id, assignment_name, assignment_weight) VALUES(?, ?, ?)";
			PreparedStatement ps = null;
			ps = conn.prepareStatement(sql);
			ps.setString(1, course.getID());
			ps.setString(2, assignment.getName());
			ps.setString(3, assignment.getWeight());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.closeDB();
		}
	}

}
