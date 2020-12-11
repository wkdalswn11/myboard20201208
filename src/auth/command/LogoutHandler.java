package auth.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.command.CommandHandler;

public class LogoutHandler implements CommandHandler{
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session =req.getSession(false); // getSession() 처럼  파라미터가 없다면 그냥 항상 만드는것, boolean 형타입으로 파라미터를받으면 항상만드는것이아님. false로 
		if (session != null) {						// 파라미터를 받으면 session에 있으면 가져오고 없으면 만들지않음.
			session.invalidate();	// 세션자체를 날려버리는것 . 즉 로그인되어있는 authUser 세션을 날려버려서 로그아웃시키는 역할
		}
		res.sendRedirect(req.getContextPath() + "/index.jsp");
		return null;
	}
}
