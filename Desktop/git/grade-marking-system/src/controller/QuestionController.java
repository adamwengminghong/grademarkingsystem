package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import core.Assignment;
import core.Question;
import core.Rubric;
import databases.SQLConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class QuestionController {
	
	public static ObservableList<Question> initQuestionDB(Assignment assignment) {
		ObservableList<Question> list = FXCollections.observableArrayList();
		SQLConnect connection = new SQLConnect();
		Connection conn = connection.connetDB();
		try {
			String sql = "SELECT * FROM question WHERE question_assignment_id = ?";
			PreparedStatement ps = null;
			ps = conn.prepareStatement(sql);
			ps.setString(1, assignment.getID());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Question question = new Question(rs.getString("question_id"), rs.getString("question_assignment_id"), rs.getString("question_rubric_id"), rs.getString("question_name"), rs.getString("question_weight"));
				list.add(question);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.closeDB();
		}
		return list;
	}
	
	public static ObservableList<Question> initQuestionDB() {
		ObservableList<Question> list = FXCollections.observableArrayList();
		SQLConnect connection = new SQLConnect();
		Connection conn = connection.connetDB();
		try {
			String sql = "SELECT * FROM question";
			PreparedStatement ps = null;
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Question question = new Question(rs.getString("question_id"), rs.getString("question_assignment_id"), rs.getString("question_rubric_id"), rs.getString("question_name"), rs.getString("question_weight"));
				list.add(question);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.closeDB();
		}
		return list;
	}
	
	
	public static List<Question> getQuestionList(Assignment assignment) {
		List<Question> questions = new ArrayList<Question>();
		SQLConnect connection = new SQLConnect();
		Connection conn = connection.connetDB();
		try {
			String sql = "SELECT * FROM question WHERE question_assignment_id = ?";
			PreparedStatement ps = null;
			ps = conn.prepareStatement(sql);
			ps.setString(1, assignment.getID());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Question question = new Question(rs.getString("question_id"), rs.getString("question_assignment_id"), rs.getString("question_rubric_id"), rs.getString("question_name"), rs.getString("question_weight"));
				questions.add(question);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.closeDB();
		}
		return questions;
	}
	
	public static void addQuestion(String questionName, String questionWeight, Rubric rubric, Assignment assignment) {
		SQLConnect connection = new SQLConnect();
		Connection conn = connection.connetDB();
		try {
			String sql = "INSERT INTO question (question_assignment_id, question_rubric_id, question_name, question_weight) VALUES(?, ?, ?, ?)";
			PreparedStatement ps = null;
			ps = conn.prepareStatement(sql);
			ps.setString(1, assignment.getID());
			ps.setString(2, rubric.getID());
			ps.setString(3, questionName);
			ps.setString(4, questionWeight);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.closeDB();
		}
	}

}
