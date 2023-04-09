package db;

import java.sql.*;

public class Database {
	private String url;

	/**
	 * const for Database
	 * 
	 * @param u the filepath for Database
	 */
	public Database(String u) {
		this.url = u;
	}

	// set up connection to WriteIt.db
	public Connection connect() {
		// SQLite connection string

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(this.url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	// create tables for data storage
	public void createTables() {
		// String to create password table
		String password = "CREATE TABLE IF NOT EXISTS password (\n key text NOT NULL);";

		// String to set default password
		String defPassword = "INSERT INTO password VALUES ('p')";

		// String to create table for user data
		String user = "CREATE TABLE IF NOT EXISTS user (\n" + "id integer PRIMARY KEY, \n" + "name text NOT NULL,\n"
				+ "title text NOT NULL, \n" + "schoolDept text NOT NULL, " + "\n email text NOT NULL, \n"
				+ "phone real \n);";

		// String to set default user data
		String defUser = "INSERT INTO user(name, title, schoolDept, email, phone) VALUES(?, ?, ?, ?, ?)";

		// String to create table for semester list
		String semesters = "CREATE TABLE IF NOT EXISTS semesters (\n" + "id integer PRIMARY KEY, \n"
				+ "semesterName text NOT NULL \n);";

		// String to create table for course list
		String courses = "CREATE TABLE IF NOT EXISTS courses (\n" + "id integer PRIMARY KEY, \n"
				+ "courseName text NOT NULL \n);";

		// String to create table for program list
		String programs = "CREATE TABLE IF NOT EXISTS programs (\n" + "id integer PRIMARY KEY, \n"
				+ "programName text NOT NULL \n);";

		// String to create table for characteristics
		// personal chars -- type = 0; academic chars type = 1
		String characteristics = "CREATE TABLE IF NOT EXISTS characteristics (\n" + "id integer PRIMARY KEY, \n"
				+ "type integer, \n" + "description text NOT NULL \n);";

		// create tables
		try (Connection conn = this.connect(); Statement stmt = conn.createStatement()) {
			stmt.execute(password);
			stmt.execute(user);
			stmt.execute(semesters);
			stmt.execute(courses);
			stmt.execute(programs);
			stmt.execute(characteristics);

			// query for number of rows in password
			ResultSet rs = stmt.executeQuery("SELECT Count(rowid) FROM password");
			// if no password has been set, set to default
			if (rs.getInt(1) == 0) {
				stmt.executeUpdate(defPassword);
				System.out.println("Set default password");
			}
			
			// query for number of rows in user
			rs = stmt.executeQuery("SELECT Count(rowid) FROM user");
			// if no user data has been entered, set to default
			if (rs.getInt(1) == 0) {
				PreparedStatement pstmt = conn.prepareStatement(defUser);
				 
				 // set parameters
				 pstmt.setString(1, "Ahmad Yazdankhah");
				 pstmt.setString(2, "Lecturer");
				 pstmt.setString(3, "SJSU Computer Science Department");
				 pstmt.setString(4, "ayazdankhah@sjsu.edu");
				 pstmt.setString(5, "No phone listed in the syllabus");
				 
				 // update database
				 pstmt.execute();
				System.out.println("Set default user data");
			}
			
			rs.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
