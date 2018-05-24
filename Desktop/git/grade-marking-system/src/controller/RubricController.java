package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import core.Rubric;
import databases.SQLConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RubricController {
	
	public static ObservableList<Rubric> initRubricDB() {
		ObservableList<Rubric> list = FXCollections.observableArrayList();
		SQLConnect connection = new SQLConnect();
		Connection conn = connection.connetDB();
		try {
			String sql = "SELECT * FROM rubric";
			PreparedStatement ps = null;
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Rubric rubric = new Rubric(rs.getString("rubric_id"), rs.getString("rubric_name"));
				list.add(rubric);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.closeDB();
		}
		return list;
	}
	
	public static void editRubricDB(Rubric rubric, String name) {
		SQLConnect connection = new SQLConnect();
		Connection conn = connection.connetDB();
		try {
			String sql = "UPDATE rubric SET rubric_name = ? WHERE rubric_id = ?";
			PreparedStatement ps = null;
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, rubric.getID());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.closeDB();
		}
	}
	
	public static void addRubricDB(Rubric rubric) {
		SQLConnect connection = new SQLConnect();
		Connection conn = connection.connetDB();
		try {
			String sql = "INSERT INTO rubric (rubric_name) VALUES(?)";
			PreparedStatement ps = null;
			ps = conn.prepareStatement(sql);
			ps.setString(1, rubric.getName());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.closeDB();
		}
	}
	
	public static void delRubricDB(Rubric rubric) {
		SQLConnect connection = new SQLConnect();
		Connection conn = connection.connetDB();
		try {
			String sql = "DELETE FROM rubric WHERE rubric_id = ?";
			PreparedStatement ps = null;
			ps = conn.prepareStatement(sql);
			ps.setString(1, rubric.getID());
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
			String sql = "SELECT rubric_name FROM rubric WHERE rubric_id = ?";
			PreparedStatement ps = null;
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				name = rs.getString("rubric_name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.closeDB();
		}
		return name;
	}
	
}
