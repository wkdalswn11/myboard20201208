package jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcUtil {

	public static void rollback(Connection con) {
		try {
			if (con != null) {
			  con.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(AutoCloseable... ins) { // (AutoCloseable... ins) 는 ... ins 를하면 괄호안에서 배열처럼쓸수있게함.
		for (AutoCloseable i : ins) {
			if (i != null) {
				try {
					i.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
