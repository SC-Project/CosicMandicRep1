package equinoobstim1.db.source;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	private static Connection conn;
	private	static final String	LOCK_TIMEOUT_INTERVAL= "5000";
	
	private	static	void	setLockTimeOut()	throws	SQLException {
		Statement stmt =	conn.createStatement();
		String cmd = "SET LOCK_TIMEOUT " +	LOCK_TIMEOUT_INTERVAL	;
		stmt.execute(cmd);
	}

	public static Connection getConnection(String dbName) {
		if (conn == null)
			try {
				open(dbName);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		return conn;
	}
	

	public static void open(String dbName) throws ClassNotFoundException, SQLException {
		if (conn != null)
			return;
		String driver = "net.sourceforge.jtds.jdbc.Driver"; //Ime parametara
		//String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url = "jdbc:jtds:sqlserver://127.0.0.1:1433/master;instance=SQLEXPRESS";
		//String url = "jdbc:jtds:sqlserver://localhost" + ";databaseName=master;integratedSecurity=true";
		System.out.println(url);
		String username = "sa";  
		String password = "root";
		Class.forName(driver); //Registrovanje drajvera
		conn = DriverManager.getConnection(url);
		conn.setAutoCommit(false);
		DatabaseMetaData metaData = DBConnection.getConnection("master").getMetaData();
		ResultSet set = metaData.getTableTypes();
		while (set.next()){
			System.out.println(set.getString("TABLE_TYPE"));
		}
		setLockTimeOut(); 
	}

	public static void close() {
		try {
			if (conn != null)
				conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void setConnection(Connection con) {
		conn = con;
	}

}
