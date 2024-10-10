package vn.iotstar.configs;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectSqlServer {

	// Test chương trình. Kích phải chuột chọn run as->java application
	public static void main(String[] args) {

		Connection conn = null;

		final String serverName = "ADMIN";
		final String dbName = "LapTrinhWeb";
		// private final String portNumber = "1433";
		final String userID = "sa";
		final String password = "12";

		try {
			String dbURL = "jdbc:sqlserver://" + serverName + ";databaseName=" + dbName;
			conn = DriverManager.getConnection(dbURL, userID, password);
			if (conn != null) {
				DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
				System.out.println("Driver name: " + dm.getDriverName());
				System.out.println("Driver version: " + dm.getDriverVersion());
				System.out.println("Product name: " + dm.getDatabaseProductName());
				System.out.println("Product version: " + dm.getDatabaseProductVersion());
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
}
