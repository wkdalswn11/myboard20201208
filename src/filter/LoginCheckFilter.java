package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginCheckFilter
 */

public class LoginCheckFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginCheckFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException { // ServletRequest에서는 session객체를 얻을수없음
		HttpServletRequest request = (HttpServletRequest) req;																// 그래서 HttpServletRequest 로 강제형변환 해야함.
		HttpServletResponse response = (HttpServletResponse) res;															// ServletResponse 도 마찬가지임.
		
		HttpSession session = request.getSession(false);
		
		if(session == null || session.getAttribute("authUser") == null ) {				
		// 로그인이 안되어 있으면
		// 로그인 화면으로 redirect
			response.sendRedirect(request.getContextPath() + "/login.do");
		} else {
		// 로그인이 되어 있으면
		chain.doFilter(req, res);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
