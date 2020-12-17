package article.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import article.model.ArticleContent;
import jdbc.JdbcUtil;

public class ArticleContentDao {
	public ArticleContent insert(Connection conn, ArticleContent content) throws SQLException {
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(
					"INSERT INTO article_content "
					+ "(article_no, content) VALUES (?,?) ");
			
			pstmt.setLong(1, content.getNumber());
			pstmt.setString(2, content.getContent());
			int InsertedCount = pstmt.executeUpdate();
			if (InsertedCount == 1) {
				return content;
			} else {
				return null;
			}
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public ArticleContent selectById(Connection con, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM article_content WHERE article_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			ArticleContent content = null;
			if (rs.next()) {
				content = new ArticleContent(rs.getInt("article_no"), rs.getString("content"));
			}
			return content;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}
	
	public int Update(Connection con, int no, String content) throws SQLException {
		String sql = "UPDATE article_content "
				+ "SET content = ? "
				+ "WHERE article_no = ?";
		
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, content);
			pstmt.setInt(2, no);
			
			int cnt = pstmt.executeUpdate();
			return  cnt;
		}
	}
	
	public int remove(Connection con, int no) throws SQLException {
		String sql = "DELETE FROM article_content "
				+ "WHERE article_no = ? ";
		
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, no);
			
			int cnt = pstmt.executeUpdate();
			return cnt;
		}
	}

	public void delete(Connection con, int no) throws SQLException { // 리무브랑같음, 강사님코드
		String sql = "DELETE article_content "
				+ "WHERE article_no = ? ";
		
		try(PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate();
		}
	}

	
}
