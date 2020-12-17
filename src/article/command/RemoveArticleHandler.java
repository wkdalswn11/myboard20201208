package article.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleData;
import article.service.ArticleNotFoundException;
import article.service.ReadArticleService;
import article.service.RemoveArticleService;
import article.service.RemoveRequest;
import auth.service.User;
import mvc.command.CommandHandler;

public class RemoveArticleHandler implements CommandHandler{
	private static final String FORM_VIEW = "readArticle";
	
	private ReadArticleService readService = new ReadArticleService();
	private RemoveArticleService removeService = new RemoveArticleService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("get")) {
			return processForm(req,res);
		} else if (req.getMethod().equalsIgnoreCase("post")) {
			return processSubmit(req, res);
		} else {
			res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception{
		User authUser = (User) req.getSession().getAttribute("authUser");
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		
		
		RemoveRequest removeReq = new RemoveRequest(authUser.getId(),
				no,
				req.getParameter("title"),
				req.getParameter("content"));
		req.setAttribute("removeReq", removeReq);
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		removeReq.validate(errors);
		
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		try {
			removeService.remove(removeReq);
			return "removeSuccess";
		} catch (ArticleNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) throws IOException{
		try {
			String noVal = req.getParameter("no");
			int no = Integer.parseInt(noVal);
			
			
			
			ArticleData articleData = readService.getArticle(no, false);
			User authUser = (User) req.getSession().getAttribute("authUser");
			if (!canRemove(authUser, articleData)) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
			
			RemoveRequest removeReq = new RemoveRequest(
					authUser.getId(), 
					no, 
					articleData.getArticle().getTitle(), 
					articleData.getContent());
			
			req.setAttribute("removeReq", removeReq);
			removeService.remove(removeReq);
			return "removeSuccess";
			
		} catch (ArticleNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

	private boolean canRemove(User authUser, ArticleData articleData) {
		String writerId = articleData.getArticle().getWriter().getId();
		return authUser.getId().equals(writerId);
	}
}
