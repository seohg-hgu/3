package project3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class deleteDAO {
	public static void delete(String id) throws Exception {
		Class.forName("org.mariadb.jdbc.Driver"); 
		String url = "jdbc:mariadb://172.17.206.42:3306/userdb"; 
		String userid = "user"; 
		String userpw = "1234"; 
		Connection conn = DriverManager.getConnection(url,userid,userpw); 
		String table="guest";
		Statement stmt = null;
		
		StringBuilder sb = new StringBuilder();
        String sql = sb.append("delete from " + table + " where")
                .append(" id = '")
                .append(id)
                .append("';").toString();
        stmt=conn.createStatement();
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
}
