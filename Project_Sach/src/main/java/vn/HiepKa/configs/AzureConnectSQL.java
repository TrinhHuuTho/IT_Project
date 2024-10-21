package vn.HiepKa.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AzureConnectSQL {

	private static final String URL = "jdbc:sqlserver://tomekeeperserver.database.windows.net:1433;database=TomeKeeper";
	private static final String USER = "HTho@tomekeeperserver";
	private static final String PASSWORD = "0379282465Tho";

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			throw new SQLException("SQLServer JDBC Driver not found", e);
		}
	}

	public static void main(String[] args) {
		try {
			new AzureConnectSQL();
			System.out.println(AzureConnectSQL.getConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
