package application;

import db.Database;
import java.sql.*;

public class DatabaseManager {
	private Database dat;

	// constructor/initializer for WriteIt Database
	public DatabaseManager() {
		dat = new Database("jdbc:sqlite:src\\WriteIt.db");
		dat.createTables();
	}

	/**
	 * setter for user password
	 * @param p new password String
	 */
	public void setPassword(String p) {
		try {
			Connection conn = dat.connect();
			Statement stmt = conn.createStatement();
			String sql = "UPDATE password " + "SET key = '" + p + "' WHERE ROWID is 1";
			stmt.executeUpdate(sql);
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// getter for user password
	public String getPassword() {
		String password;
		try {
			Connection conn = dat.connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT key FROM password");
			password = rs.getString("key");
			rs.close();
			return password;
		}

		catch (SQLException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
}
