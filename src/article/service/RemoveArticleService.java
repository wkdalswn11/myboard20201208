package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

public class RemoveArticleService {
	
	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao contentDao = new ArticleContentDao();
	
	public void remove(RemoveRequest removeReq) {
		Connection con = null;
		try {
			
			con = ConnectionProvider.getConnection();
			con.setAutoCommit(false);
			
			Article article = articleDao.selectById(con, removeReq.getArticleNumber());
			if (article == null) {
				throw new ArticleNotFoundException();
			}
			articleDao.remove(con, removeReq.getArticleNumber());
            contentDao.remove(con, removeReq.getArticleNumber());
            
            con.commit();
			


		} catch (SQLException e) {
			JdbcUtil.rollback(con);
			throw new RuntimeException(e);		
		} finally {
			JdbcUtil.close(con);
		}
	}
}
