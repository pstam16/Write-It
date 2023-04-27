package application;

import db.Database;
import java.util.*;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;

public class DatabaseManager {
	private Database dat;
	private Connection conn;

	// constructor/initializer for WriteIt Database
	public DatabaseManager() {
		dat = new Database("jdbc:sqlite:src\\db\\WriteIt.db");
		dat.createTables();
		conn = dat.connect();
	}

	/**
	 * getter for single string var from table with id row
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
	 * getter for single int var from table with id row
	 * @param table name of target table
	 * @param col   name of target column
	 * @param id    int of row id
	 * @return String retrieved from table, col
	 */
	public int getSingleIntVarFromRow(String table, String col, int id) {
		int i; // string to return
		try {
			PreparedStatement pstmt = conn.prepareStatement("SELECT " + col + " FROM " + table + " WHERE id = ? ");
			pstmt.setDouble(1, id);
			ResultSet rs = pstmt.executeQuery();
			i = rs.getInt(col);
			rs.close();
			return i;
		}

		catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * method to return id of target string
	 * 
	 * @param table target table
	 * @param col   target column in table
	 * @param p     searched string
	 * @return row id of string
	 */
	public int getIdfromStringVar(String table, String col, String p) {
		try {
			PreparedStatement pstmt = conn.prepareStatement("SELECT id FROM " + table + " WHERE " + col + " = ? ");
			pstmt.setString(1, p);
			ResultSet rs = pstmt.executeQuery();
			int r = rs.getInt(0);
			rs.close();
			return r;
		}

		catch (SQLException e) {
			e.printStackTrace();
			return -1;
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
	public List<String> getAllSingleStringVars(String table, String col, String type, int id) {
		List<String> rStringList = new ArrayList<>();
		; // string to return
		try {
			PreparedStatement pstmt = conn.prepareStatement("SELECT " + col + " FROM " + table + " WHERE " + type + "  = ? ");
			pstmt.setDouble(1, id);
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
	 * method to get list of integer vars form column in table
	 * 
	 * @param table target table
	 * @param col   target column
	 * @return ArrayList of strings from table
	 */
	public List<Integer> getAllSingleIntVars(String table, String col, String type, int id) {
		List<Integer> rList = new ArrayList<>();
		; // string to return
		try {
			PreparedStatement pstmt = conn.prepareStatement("SELECT " + col + " FROM " + table + " WHERE " + type + "  = ? ");
			pstmt.setDouble(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				rList.add(rs.getInt(col));
			}
			rs.close();
			return rList;
		}

		catch (SQLException e) {
			e.printStackTrace();
			return rList;
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
	 * @param p     Object to insert
	 */
	public void addSingleVar(String table, String col, Object p) {
		List<Object> rList = new ArrayList<>(); // ArrayList to hold current db entries in column col
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT " + col + " FROM " + table);
			// collect results of query into ArrayList
			int i = 1;
			while (rs.next()) {
				rList.add(rs.getObject(col));
				i++;
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
	public void addSingleStringVarWithId(String table, String col, String p, String idName, int id) {
		List<String> rList = new ArrayList<>(); // ArrayList to hold current db entries in column col
		try {
			// collect all matching vars
			rList = this.getAllSingleStringVars(table, col, idName, id);
			// add if not duplicate
			if (!rList.contains(p)) {
				String sql = "INSERT INTO " + table + "('" + col + "', type) VALUES (?, ?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, p);
				pstmt.setInt(2, id);
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

	// method to calculate hashcode from first + last name
	public int getNameHash(String first, String last) {
		return (first + last).hashCode();
	}

	/**
	 * method to add new recommendation
	 * 
	 * @param firstName
	 * @param lastName
	 * @param gender
	 * @param schoolName
	 * @param selectedDate
	 * @param program
	 * @param semester
	 * @param year
	 * @param courses
	 * @param grades
	 * @param personalChars
	 * @param academicChars
	 */
	public void addRecommendation(String firstName, String lastName, String gender, String schoolName,
			LocalDate selectedDate, String program, String semester, String year, List<String> courses,
			Map<String, String> grades, List<String> personalChars, List<String> academicChars) {
		// insert recommendation data to table
		String rec = "INSERT INTO recommendations(hash, firstName, lastName, gender, schoolName, selectedDate,"
				+ " program, semester, year) VALUES(?,?,?,?,?,?,?,?,?)\n"; // string for student data insertion
		String grade = "INSERT INTO grades (studentID, courseID, grade) VALUES (?,?,?);"; // string for grade data
																							// insertion
		String chars = "INSERT INTO studentChars(studentID, characteristicID) VALUES (?,?);";
		try {
			// insert student data
			PreparedStatement pstmt = conn.prepareStatement(rec);
			pstmt.setInt(1, this.getNameHash(firstName, lastName));
			pstmt.setString(2, firstName);
			pstmt.setString(3, lastName);
			pstmt.setString(4, gender);
			pstmt.setString(5, schoolName);
			pstmt.setDate(6, Date.valueOf(selectedDate));
			pstmt.setString(7, program);
			pstmt.setString(8, semester);
			pstmt.setString(9, year);
			pstmt.execute();

			// insert grade data
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT courseName from courses"); // collect course names from db
			List<String> courseList = new ArrayList<>();
			while (rs.next()) {
				courseList.add(rs.getString("courseName")); // add course names to List
			}
			pstmt = conn.prepareStatement(grade);
			for (String course : courses) {
				pstmt.setInt(1, this.getNameHash(firstName, lastName)); // student id (hash)
				pstmt.setInt(2, courseList.indexOf(course) + 1); // course id (arrayList location + 1, since SQLite
																	// numbers beginning at 1)
				pstmt.setString(3, grades.get(course)); // course grade from HashMap
				pstmt.execute(); // upload to db
			}

			// insert characteristics data
			rs = stmt.executeQuery("SELECT description from characteristics"); // collect characteristics from db
			List<String> charList = new ArrayList<>();
			while (rs.next()) {
				charList.add(rs.getString("description")); // add characteristics to List
			}

			pstmt = conn.prepareStatement(chars);
			for (String personal : personalChars) {
				pstmt.setInt(1, this.getNameHash(firstName, lastName)); // student ID
				pstmt.setInt(2, charList.indexOf(personal) + 1); // characteristic id
				pstmt.execute();
			}

			for (String academic : academicChars) {
				pstmt.setInt(1, this.getNameHash(firstName, lastName)); // student ID
				pstmt.setInt(2, charList.indexOf(academic) + 1); // characteristic id
				pstmt.execute();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// method to return last inserted row from target table
	public int getLastInsertID(String table) {
		try {
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(id) AS max_id FROM " + table);
			ResultSet rs = pstmt.executeQuery();
			return rs.getInt("max_id");
		} catch (SQLException e) {
			e.getMessage();
			return -1;
		}

	}

	/**
	 * method to return target data from recommendation id
	 * 
	 * @param id
	 * @param dataID
	 * @param dataTable
	 * @param text
	 * @param sourceTable
	 * @return
	 */
	public List<String> getDataFromStudent(int id, String dataID, String dataTable, String text, String sourceTable) {
		List<String> rList = new ArrayList<>();
			// retrieve student id hash from recommendations table
			int h = this.getSingleIntVarFromRow("recommendations", "hash", id);
			
//			// retrieve student's data id's from  table
			List<Integer> ids = this.getAllSingleIntVars(dataTable, dataID, "studentID", h);
//			
			// retrieve names, add to list for return
			for (int i : ids) {
				rList.addAll(this.getAllSingleStringVars(sourceTable, text, "id", i));
			}
			return rList;
	}

	
	/**
	 * method to return target data (with type id) from recommendation id
	 * 
	 * @param id
	 * @param dataID
	 * @param dataTable
	 * @param text
	 * @param sourceTable
	 * @param int type
	 * @return
	 */
	public List<String> getDataFromStudent(int id, String dataID, String dataTable, String text, String sourceTable, int type) {
		List<String> rList = new ArrayList<>();
		try {
			// retrieve student id hash from recommendations table
			int h = this.getSingleIntVarFromRow("recommendations", "hash", id);
			
			// retrieve student's data id's from  table
			List<Integer> ids = this.getAllSingleIntVars(dataTable, dataID, "studentID", h);
			// retrieve names, add to list for return
			for (int i : ids) {
				PreparedStatement pstmt = conn.prepareStatement("SELECT " + text + " FROM " + sourceTable + " WHERE type IS " + type + " AND id IS " + i);
				ResultSet rs = pstmt.executeQuery();
				String s = rs.getString(text);
				if (!(s== null)) {
					rList.add(s);
					}
			}

			return rList;
		} catch (SQLException e) {
			e.getMessage();
			return rList;
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
			e.getMessage();
		}
	}

}
