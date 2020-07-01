package project3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class updateDAO {
	public static void update(DTO dto) throws Exception {

		
		Connection con = null;
		PreparedStatement pstmt = null;
		String name = dto.getName();
		String sex=dto.getS();
		String id = dto.getId();
		char[] passwd = dto.getPassword();
		String phone= dto.getPhone();
		String password;
		String table="guest";
		try {
			password=new String(passwd);
			String sql = "update "+table+" set name=?, sex=?, password=?, phone=? where id = ?;";
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mariadb://172.17.206.42:3306/userdb", "user", "1234");
			 pstmt = con.prepareStatement(sql);
	         pstmt.setString(1, name);
	         pstmt.setString(2, sex);
	         pstmt.setString(3, password);
	         pstmt.setString(4, phone);
	         pstmt.setString(5, id);
	         pstmt.executeUpdate();
		}catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed())
                    pstmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
	}
}
