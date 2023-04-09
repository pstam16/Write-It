package application;

import db.Database;
import java.util.*;
import java.sql.*;

public class DatabaseManager {
	private Database dat;

	// constructor/initializer for WriteIt Database
	public DatabaseManager() {
		dat = new Database("jdbc:sqlite:src\\db\\WriteIt.db");
		dat.createTables();
	}

	// getter for single var table, no id row
	/**
	 * 
	 * @param table name of target table
	 * @param col   name of target column
	 * @return String retrieved from table, col
	 */
	public String getSingleStringVar(String table, String col) {
		String rString; // string to return
		try {
			Connection conn = dat.connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT " + col + " FROM " + table);
			rString = rs.getString(col);
			rs.close();
			return rString;
		}

		catch (SQLException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	// getter for single var table with id row
	/**
	 * 
	 * @param table name of target table
	 * @param col   name of target column
	 * @param id    int of row id
	 * @return String retrieved from table, col
	 */
	public String getSingleStringVar(String table, String col, int id) {
		String rString; // string to return
		try {
			Connection conn = dat.connect();
			PreparedStatement pstmt = conn.prepareStatement("SELECT " + col + " FROM " + table + " WHERE id = ? ");
			pstmt.setDouble(1, id);
			ResultSet rs = pstmt.executeQuery();
			rString = rs.getString(col);
			rs.close();
			return rString;
		}

		catch (SQLException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	/**
	 * method to set password
	 * @param p new password
	 */
	public void setPassword(String p) {
		try {
			Connection conn = dat.connect();
			String sql = "UPDATE password SET key = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p);
			pstmt.execute();
		}	catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * method to add values to table with single data column
	 * @param table	target table
	 * @param col	target column
	 * @param p		String to insert
	 */
	public void setSingleStringVar(String table, String col, String p) {
		List<String> rList = new ArrayList<>(); // ArrayList to hold current db entries in column col
		try {
			Connection conn = dat.connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT " + col + " FROM " + table);
			// collect results of query into ArrayList
			while (rs.next()) {
				rList.add(rs.getString(col));
			}
			// add if not duplicate
			if (!rList.contains(p)) {
				String sql = "INSERT INTO " + table + "(" + col + ") VALUES ('" + p + "')";
				stmt.executeUpdate(sql);
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * method to add values to table with one data column, one type id
	 * @param table	target table
	 * @param col	target column
	 * @param p		String to insert
	 */
	public void setSingleStringVarWithType(String table, String col, String p, int type) {
		List<String> rList = new ArrayList<>(); // ArrayList to hold current db entries in column col
		try {
			Connection conn = dat.connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT " + col + " FROM " + table + " WHERE type = " + type);
			// collect results of query into ArrayList
			while (rs.next()) {
				rList.add(rs.getString(col));
			}
			// add if not duplicate
			if (!rList.contains(p)) {
				String sql = "INSERT INTO " + table + "('" + col + "', type) VALUES (?, ?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, p);
				pstmt.setInt(2, type);
				pstmt.execute();
				
			}
		}

		catch (SQLException e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}
	}
	
	// method to upload user data to database
	public void setUserData(String name, String title, String schoolDept, String email, String phone) {
		try {
			Connection conn = dat.connect();
			// query string for SQLite data insertion
			String sql = "UPDATE user SET name = ?, "
					+ "title = ?, schoolDept = ?, email = ?, phone = ?";
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 
			 // set parameters
			 pstmt.setString(1, name);
			 pstmt.setString(2, title);
			 pstmt.setString(3, schoolDept);
			 pstmt.setString(4, email);
			 pstmt.setString(5, phone);
			 
			 // update database
			 pstmt.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
			e.getMessage();
		}
	}
	
	// method to print table results to screen (for testing)
	public void printTable() {
		final String QUERY = "SELECT name, title, schoolDept, email, phone FROM user";
		System.out.println("printing . . .");
		try {
			Connection conn = dat.connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(QUERY);
			while(rs.next()) {
				System.out.println("Name: " + rs.getString("name") + "  Title: " + rs.getString("title")
				+ " School and Department: " + rs.getString("schoolDept") + " email: " + rs.getString("email") +
				" phone: " + rs.getString("phone"));
			}
		} catch (SQLException e){ 
			e.printStackTrace();
		}
	}

}
