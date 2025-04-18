package com.museum.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import com.museum.dao.ArticleDao;
import com.museum.entity.Article;
import com.museum.entity.Category;
import com.museum.exception.ResourceAlreadyExistException;
import com.museum.exception.ResourceNotFoundException;

public class ArticleService {

	private ArticleDao articleDao;

	public ArticleService() throws SQLException {
		articleDao = new ArticleDao();
	}

	public void addArticle(String name, Category category, LocalDate createdDate, String creatorName)
			throws  ResourceAlreadyExistException,SQLException {
		Optional<Article> isExist = articleDao.findAll().stream()
				.filter(article -> article.getName().equalsIgnoreCase(name)).findFirst();

		if (isExist.isEmpty()) {
			Article newArticle = new Article(null, name, category, createdDate, creatorName);
			boolean status = articleDao.save(newArticle);
			if (status)
				System.out.println("Article added Sucessfully!!");
			else
				System.out.println("Article failed to add!!");
		} else
			throw new ResourceAlreadyExistException("Article already exist with same name");
	}

	public void displayAllArticle() throws SQLException {
		articleDao.findAll().stream().forEach(article -> System.out.println(article));
	}

	public void displayArticle(int id) throws ResourceNotFoundException,SQLException {
		Article art = articleDao.findById(id);
		if (art != null)
			System.out.println(art);
		else
			throw new ResourceNotFoundException("Article not found with id " + id);
	}
	
	public void deleteArticle(int id) throws SQLException {
		Boolean status = articleDao.delete(id);
		if (status)
			System.out.println("Article deleted Sucessfully!!");
		else
			System.out.println("Article failed to delete!!");
		
	}
	
	public void updateArticle(int id, String name, Category category, LocalDate createdDate, String creatorName) throws SQLException,ResourceNotFoundException {
		Article art = articleDao.findById(id);
		if (art != null) {
		Boolean status = articleDao.update(new Article(id, name, category, createdDate, creatorName));
		if (status)
			System.out.println("Article deleted Sucessfully!!");
		else
			System.out.println("Article failed to delete!!");
		}
		else
			throw new ResourceNotFoundException("Article not found with id " + id);
	
		
	}

}
