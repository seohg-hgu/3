package project3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class searchDAO {
	public static void searchpw(String id) throws Exception {
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
        String sql = sb.append("select * from " + table + " where")
                .append(" id='")
                .append(id)
                .append("';").toString();
        
        stmt=conn.createStatement();
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                 Main.receivedPW=rs.getString("password");
                 Main.tname=rs.getString("name");
                 Main.tsex=rs.getString("sex");
                 Main.tp=rs.getString("phone");
            }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            
        }
	}
	public static void search(String id) throws Exception {
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
        String sql = sb.append("select * from " + table + " where")
                .append(" id = '")
                .append(id)
                .append("';").toString();
        System.out.println(sql);
        stmt=conn.createStatement();
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                 Main.receivedID=rs.getString("id");
                 System.out.println(Main.receivedID);
            }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            
        }
	}
	public static void searchAll() throws Exception {
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
		
        String sql = "select * from " + table +";";
        
        stmt=conn.createStatement();
        try {
            ResultSet rs = stmt.executeQuery(sql);
            for(int j=0;j<Main.content.length;j++) {
            	for(int k=0;k<Main.content.length;k++) {
            		Main.content[j][k]=null;
            	}
            }
            int i=0;
            while(rs.next()) {
                Main.content[i][0]=rs.getString("name");
	        	Main.content[i][1]=rs.getString("sex");
	        	Main.content[i][2]=rs.getString("id");
	        	Main.content[i][3]=rs.getString("password");
	        	Main.content[i][4]=rs.getString("phone");
	        	i++;
            }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            
        }
	}
}
