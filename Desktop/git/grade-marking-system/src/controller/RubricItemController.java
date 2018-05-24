package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import core.Rubric;
import core.RubricItem;
import databases.SQLConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RubricItemController {
	
	public static ObservableList<RubricItem> initRubricDB(Rubric rubric) {
		ObservableList<RubricItem> list = FXCollections.observableArrayList();
		SQLConnect connection = new SQLConnect();
		Connection conn = connection.connetDB();
		try {
			String sql = "SELECT * FROM rubric_item WHERE rubric_id = ?";
			PreparedStatement ps = null;
			ps = conn.prepareStatement(sql);
			ps.setString(1, rubric.getID());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				RubricItem item = new RubricItem(rs.getString("rubric_id"), rs.getString("assesment_name"), rs.getString("rubric_item_weight"));
				list.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.closeDB();
		}
		return list;
	}
	
	public static ObservableList<RubricItem> initRubricDB(String id) {
		ObservableList<RubricItem> list = FXCollections.observableArrayList();
		SQLConnect connection = new SQLConnect();
		Connection conn = connection.connetDB();
		try {
			String sql = "SELECT * FROM rubric_item WHERE rubric_id = ?";
			PreparedStatement ps = null;
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				RubricItem item = new RubricItem(rs.getString("rubric_id"), rs.getString("assesment_name"), rs.getString("rubric_item_weight"));
				list.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.closeDB();
		}
		return list;
	}
	
	public static void clearRubricItemDB(Rubric rubric) {
		SQLConnect connection = new SQLConnect();
		Connection conn = connection.connetDB();
		try {
			String sql = "DELETE FROM rubric_item WHERE rubric_id = ?";
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
	
	public static void addRubricDB(Rubric rubric, String name, String weight) {
		SQLConnect connection = new SQLConnect();
		Connection conn = connection.connetDB();
		try {
			String sql = "INSERT INTO rubric_item (rubric_id, assesment_name, rubric_item_weight) VALUES(?, ?, ?)";
			PreparedStatement ps = null;
			ps = conn.prepareStatement(sql);
			ps.setString(1, rubric.getID());
			ps.setString(2, name);
			ps.setString(3, weight);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.closeDB();
		}
	}
	
	public static Double getTotalWeight(Rubric rubric) {
		Double totalWeight = 0.0;
		SQLConnect connection = new SQLConnect();
		Connection conn = connection.connetDB();
		try {
			String sql = "SELECT rubric_item_weight FROM rubric_item WHERE rubric_id = ?";
			PreparedStatement ps = null;
			ps = conn.prepareStatement(sql);
			ps.setString(1, rubric.getID());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				totalWeight = totalWeight + rs.getDouble("rubric_item_weight");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.closeDB();
		}
		return totalWeight;
	}
}
