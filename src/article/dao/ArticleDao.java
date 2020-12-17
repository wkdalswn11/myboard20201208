package article.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import article.model.Article;
import article.model.Writer;
import jdbc.JdbcUtil;

public class ArticleDao {
	
	public Article selectById(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try { 
			String sql = "SELECT * FROM article WHERE article_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			Article article = null;
			if (rs.next()) {
				article = convertArticle(rs);
			}
			return article;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
		
		
	}
	
	public Article insert(Connection conn, Article article) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = ("INSERT INTO article "
					+ "(writer_id, writer_name, title, regdate, moddate, read_cnt) "
					+ "VALUES (?, ?, ?,SYSDATE, SYSDATE, 0) ");
		try {
			pstmt = conn.prepareStatement(sql,
							new String[] {"article_no", "regdate", "moddate"});
			
			pstmt.setString(1, article.getWriter().getId());
			pstmt.setString(2, article.getWriter().getName());
			pstmt.setString(3, article.getTitle());
			
			int cnt = pstmt.executeUpdate();
			
			if (cnt == 1) {
				rs = pstmt.getGeneratedKeys();
				int key = 0;
				Date regDate = null;
				Date modDate = null;
				
				if (rs.next()) {
					key = rs.getInt(1);
					regDate = rs.getTimestamp(2);
					modDate = rs.getTimestamp(3);
				}
				return new Article(key,
						article.getWriter(),
						article.getTitle(),
						regDate,
						modDate,
						0);
			} else {
				return null;
			}
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}
	
	
	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT count(*) FROM article";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs, stmt);
		}
	}
	
	public List<Article> select(Connection conn, int pageNum, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT rn, article_no, writer_id, writer_name, title, regdate, moddate, read_cnt "
                + "FROM (SELECT article_no, writer_id, writer_name, title, regdate, moddate, read_cnt, ROW_NUMBER() "
                + "OVER (ORDER BY article_no DESC) rn FROM article ) WHERE rn BETWEEN ? AND ?";
				
		
		
		
		
		/*
		String sql = "SELECT * FROM article "
				+ "order by article_no desc "				
				+ "LIMIT ?, ? "; // 시작 row_num(zerobase), 갯수 
		*/
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (pageNum-1) * size + 1);
			pstmt.setInt(2, pageNum * size);
			rs = pstmt.executeQuery();
			List<Article> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertArticle(rs));
			}
			return result;
		
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}
	private Article convertArticle(ResultSet rs) throws SQLException {
		
		return new Article(rs.getInt("article_no"),
				new Writer(
						rs.getString("writer_id"),
						rs.getString("writer_name")
						),
				rs.getString("title"),
				rs.getTimestamp("regdate"),
				rs.getTimestamp("moddate"),
				rs.getInt("read_cnt"));
	}

	public void increaseReadCount(Connection conn, int no) throws SQLException {
		String sql = "UPDATE article "
				+ "SET read_cnt = read_cnt + 1 "
				+ "WHERE article_no = ? ";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		}
	}
	
	public int update(Connection con, int no, String title) throws SQLException {
		String sql = "UPDATE article "
				+ "SET title = ?, moddate = SYSDATE "
				+ "WHERE article_no = ?";
		
		try(PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, title);
			pstmt.setInt(2, no);
			
			int cnt = pstmt.executeUpdate();
			return  cnt;
		}
	}
	
	public int remove(Connection con, int no) throws SQLException {
		String sql = "DELETE FROM article "
				+ "WHERE article_no = ? ";
		
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, no);
			
			int cnt = pstmt.executeUpdate();
			return cnt;
		}
	}

	public void delete(Connection con, int no) throws SQLException { // 위에 remove와같음 선생님코드.
		String sql = "DELETE article WHERE article_no=? ";
		
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate();
		}
	}

	
	
}
