package com.museum.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.museum.entity.Article;
import com.museum.entity.Category;
import com.museum.util.JdbcUtil;

public class ArticleDao implements JdbcDao<Article, Integer> {
	private Connection conn;
	private String query;
	private PreparedStatement pstm;
	private Statement stml;

	public ArticleDao() throws SQLException {
		conn = JdbcUtil.getConnection();
	}

	@Override
	public Boolean save(Article article) throws SQLException {
		query = "insert into article (name, category, date_created, creator_name) values(?,?,?,?)";
		pstm = conn.prepareStatement(query);
		pstm.setString(1, article.getName());
		pstm.setString(2, article.getCategory().toString());
		pstm.setDate(3, Date.valueOf(article.getCreatedDate()));
		pstm.setString(4, article.getCreatorName());

		int count = pstm.executeUpdate();
		if (count > 0) {
			System.out.println(count + " rows affected!");
			return true;
		}
		return false;
	}

	@Override
	public Collection<Article> findAll() throws SQLException {
		List<Article> articles = new ArrayList<Article>();
		query = "select * from article";

		stml = conn.createStatement();
		ResultSet rs = stml.executeQuery(query);

		while (rs.next()) {
			articles.add(new Article(rs.getInt("id"), rs.getString("name"), Category.valueOf(rs.getString("category")),
					rs.getDate("date_created").toLocalDate(), rs.getString("creator_name")));
		}
		return articles;
	}

	@Override
	public Article findById(Integer id) throws SQLException {
		Article article = null;
		query = "select * from article where id = ?";
		pstm = conn.prepareStatement(query);
		pstm.setInt(1, id);

		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			article = new Article(rs.getInt("id"), rs.getString("name"), Category.valueOf(rs.getString("category")),
					rs.getDate("date_created").toLocalDate(), rs.getString("creator_name"));
		}

		return article;
	}

	@Override
	public Boolean delete(Integer id) throws SQLException {
		query = "delete from article where id = ?";
		pstm = conn.prepareStatement(query);
		pstm.setInt(1, id);

		int count = pstm.executeUpdate();

		if (count > 0) {
			System.out.println(count + " rows affected!");
			return true;
		}
		return false;
	}

	@Override
	public Boolean update(Article article) throws SQLException {
		query = "update article set name = ?, category = ?,date_created  = ?,creator_name = ? where  id  = ?";
		pstm = conn.prepareStatement(query);
		pstm.setString(1, article.getName());
		pstm.setString(2, article.getCategory().toString());
		pstm.setDate(3, Date.valueOf(article.getCreatedDate()));
		pstm.setString(4, article.getCreatorName());
		pstm.setInt(5, article.getId());
		int count = pstm.executeUpdate();
	
		if (count > 0) {
			System.out.println(count + " rows affected!");
			return true;
		}
		return false;
	}

}
