package yyz.njupt.db;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBManager {
	
	private static ComboPooledDataSource ds = null;
	
	static{
		try {
			
			ds = new ComboPooledDataSource();
			ds.setDriverClass("com.mysql.jdbc.Driver");
			ds.setJdbcUrl("jdbc:mysql://localhost:3306/qqdb");
			ds.setUser("root");
			ds.setPassword("0987");
			ds.setMaxPoolSize(100);
			ds.setMinPoolSize(2);
			ds.setInitialPoolSize(10);
			ds.setMaxStatements(180);
			
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}
	
	private DBManager(){
		
	}
	
	public static Connection getConnection() throws SQLException{
		Connection conn = ds.getConnection();
		return conn;
	}
	
	public static void main(String[] args) throws SQLException{
		Connection conn = getConnection();
		PreparedStatement pst = conn.prepareStatement("SELECT * FROM userData "
				+ "WHERE uid = ?");
		pst.setInt(1, 2);
		ResultSet rs = pst.executeQuery();
		
		if(rs.next()){
			System.out.println(rs.getString("petname"));
		}
	}

}
