package application;

import db.Database;
import java.util.*;
import java.sql.*;

public class DatabaseManager {
	private Database dat;
	private Connection conn;

	// constructor/initializer for WriteIt Database
	public DatabaseManager() {
		dat = new Database("jdbc:sqlite:src\\db\\WriteIt.db");
		dat.createTables();
		conn = dat.connect();
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
	public String getSingleStringVarFromRow(String table, String col, int id) {
		String rString; // string to return
		try {
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
	 * method to get list of string vars form column in table
	 * 
	 * @param table target table
	 * @param col   target column
	 * @return ArrayList of strings from table
	 */
	public List<String> getAllSingleStringVars(String table, String col) {
		List<String> rStringList = new ArrayList<String>();
		; // string to return
		try {
			PreparedStatement pstmt = conn.prepareStatement("SELECT " + col + " FROM " + table);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				rStringList.add(rs.getString(col));
			}
			rs.close();
			return rStringList;
		}

		catch (SQLException e) {
			e.printStackTrace();
			return rStringList;
		}
	}

	/**
	 * method to get list of string vars form column in table
	 * 
	 * @param table target table
	 * @param col   target column
	 * @return ArrayList of strings from table
	 */
	public List<String> getAllSingleStringVars(String table, String col, int type) {
		List<String> rStringList = new ArrayList<String>();
		; // string to return
		try {
			PreparedStatement pstmt = conn.prepareStatement("SELECT " + col + " FROM " + table + " WHERE type = ? ");
			pstmt.setDouble(1, type);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				rStringList.add(rs.getString(col));
			}
			rs.close();
			return rStringList;
		}

		catch (SQLException e) {
			e.printStackTrace();
			return rStringList;
		}
	}

	/**
	 * method to set password
	 * 
	 * @param p new password
	 */
	public void setPassword(String p) {
		try {
			String sql = "UPDATE password SET key = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * method to add values to table with single data column
	 * 
	 * @param table target table
	 * @param col   target column
	 * @param p     String to insert
	 */
	public void addSingleStringVar(String table, String col, String p) {
		List<String> rList = new ArrayList<>(); // ArrayList to hold current db entries in column col
		try {
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
	 * 
	 * @param table target table
	 * @param col   target column
	 * @param p     String to insert
	 */
	public void addSingleStringVarWithType(String table, String col, String p, int type) {
		List<String> rList = new ArrayList<>(); // ArrayList to hold current db entries in column col
		try {
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
			// e.printStackTrace();
		}
	}

	/**
	 * method to set value of target table column
	 * 
	 * @param table target table
	 * @param col   target column
	 * @param p     String to update
	 */
	public void setSingleStringVar(String table, String col, String p) {
		try {
			String sql = "UPDATE " + table + " SET " + col + " = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p);
			pstmt.execute();
		}

		catch (SQLException e) {
			System.out.println(e.getMessage());
			// e.printStackTrace();
		}
	}

	// method to print table results to screen (for testing)
	public void printTable() {
		final String QUERY = "SELECT name, title, schoolDept, email, phone FROM user";
		System.out.println("printing . . .");
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(QUERY);
			while (rs.next()) {
				System.out.println("Name: " + rs.getString("name") + "  Title: " + rs.getString("title")
						+ " School and Department: " + rs.getString("schoolDept") + " email: " + rs.getString("email")
						+ " phone: " + rs.getString("phone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
