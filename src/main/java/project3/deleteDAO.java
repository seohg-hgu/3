package project3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class deleteDAO {
	public static void delete(String id) throws Exception {
		//DB 종료에 따른 JDBC DRIVER 클래스 
		Class.forName("org.mariadb.jdbc.Driver"); 
		//DB접속 url 
		String url = "jdbc:mariadb://172.17.206.42:3306/userdb"; 
		//DB접속 ID 
		String userid = "user"; 
		//DB접속 패스워드 
		String userpw = "1234"; 
		//접속정보로 JDBC 연결 커넥션 생성 
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
