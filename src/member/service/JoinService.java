package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.dao.MemberDao;
import member.model.Member;

public class JoinService {
	private MemberDao memberDao = new MemberDao();

	Connection con = null;

	public void join(JoinRequest joinReq) {

		try {
			con = ConnectionProvider.getConnection();  // 위에서 선언해줬기때문에 try resource 구문사용불가 
			con.setAutoCommit(false);				   // 자동 commit을 안하게 만듬.
			
			Member m = memberDao.selectById(con, joinReq.getId());

			if (m != null) {
				JdbcUtil.rollback(con); 				// JdbcUtil.rollback(con); 은 selectById와 insert 가 하나의 트랜젝션인걸 알려주기위해 넣어줬을뿐 빼줘도 됨.
				throw new DuplicateIdException();
			}

			Member member = new Member();
			member.setId(joinReq.getId());
			member.setName(joinReq.getName());
			member.setPassword(joinReq.getPassword());

			memberDao.insert(con, member);				// insert와 selectById는 같은 connection이 필요하므로 파라미터로 con을 넣어줘야함.
			
			con.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(con);
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(con);
		}
	}
}
