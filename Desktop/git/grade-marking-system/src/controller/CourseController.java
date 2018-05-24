package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import core.Course;
import databases.SQLConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CourseController {
	
	public static ObservableList<Course> initCourseDB() {
		ObservableList<Course> list = FXCollections.observableArrayList();
		SQLConnect connection = new SQLConnect();
		Connection conn = connection.connetDB();
		try {
			String sql = "SELECT * FROM course";
			PreparedStatement ps = null;
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Course course = new Course(rs.getString("course_id"), rs.getString("course_name"));
				list.add(course);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.closeDB();
		}
		return list;
	}
	
	
	public static void addCourse(Course course) {
		SQLConnect connection = new SQLConnect();
		Connection conn = connection.connetDB();
		try {
			String sql = "INSERT INTO course (course_name) VALUES(?)";
			PreparedStatement ps = null;
			ps = conn.prepareStatement(sql);
			ps.setString(1, course.getName());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.closeDB();
		}
	}
	
	public static String getNameDB(String id) {
		String name = "";
		SQLConnect connection = new SQLConnect();
		Connection conn = connection.connetDB();
		try {
			String sql = "SELECT course_name FROM course WHERE course_id = ?";
			PreparedStatement ps = null;
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				name = rs.getString("course_name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.closeDB();
		}
		return name;
	}
}
