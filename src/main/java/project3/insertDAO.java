package project3;

import java.sql.*;
import java.sql.Statement;
import java.sql.DriverManager;

public class insertDAO {

	
	public static boolean create(DTO dto) throws Exception {

		boolean flag = false;

		Connection con = null;
		Statement stmt = null; // 데이터를 전송하는 객체

		String name = dto.getName();
		String sex=dto.getS();
		String id = dto.getId();
		char[] passwd = dto.getPassword();
		String phone= dto.getPhone();
		String password;

		String sql = "INSERT INTO guest(name, sex, id, password, phone) VALUES";

		try {
			password=new String(passwd);
			sql += "('" + new String(name.getBytes(), "UTF-8") + "','"

					+ new String(sex.getBytes(), "UTF-8") + "','"
					
					+ new String(id.getBytes(), "UTF-8") + "','"

					+ new String(password.getBytes(), "UTF-8") + "','"

					+ new String(phone.getBytes(), "UTF-8") + "')";

			Class.forName("org.mariadb.jdbc.Driver");

			con = DriverManager.getConnection(

					"jdbc:mariadb://172.17.206.42:3306/userdb", "user", "1234");

			stmt = (Statement)con.createStatement();

			stmt.executeUpdate(sql);
		

			flag = true;

		} catch (Exception e) {

			System.out.println(e);

			flag = false;

		} finally {

			try {

				if (stmt != null)

					stmt.close();

				if (con != null)

					con.close();

			} catch (Exception e) {

				System.out.println(e.getMessage());

			}

		}

		return flag;

	}

}
