package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import auth.service.User;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.dao.MemberDao;
import member.model.Member;

public class RemoveMemberService {
	private MemberDao memberDao = new MemberDao();
	
	public void removeMember(User user, String password)  {
		Connection conn = null;
		// 0. 커넥션 얻기
		try {
		conn = ConnectionProvider.getConnection();
		conn.setAutoCommit(false);
		// 1. dao의 selectById로 member 객체 얻기
		Member member = memberDao.selectById(conn, user.getId());
		if (member == null) {
			// 	  1.1 member없으면 MemberNotFoundException 발생
			throw new MemberNotFoundException();
		}
		if (!member.matchPassword(password)) {
			// 2. password와 member.password가 같은 지 확인
			// 	  2.1 다르면 InvalidPasswordException 발생
			throw new InvalidPasswordException();
		}
		// 3. delete() 실행
		memberDao.delete(conn, user.getId());
		conn.commit();

		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		
		
	}
}
