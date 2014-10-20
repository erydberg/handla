package se.rydberg.handla.service;

import java.util.List;

import se.rydberg.handla.exception.ArticleNotDeletedException;
import se.rydberg.handla.exception.ArticleNotFoundException;
import se.rydberg.handla.exception.ArticleNotSavedException;
import se.rydberg.handla.exception.ShopListNotFoundException;
import se.rydberg.handla.model.Article;

public interface ArticleService {
	public void deleteArticle(Article article) throws ArticleNotDeletedException;
	public void deleteArticleById(Integer id) throws ArticleNotDeletedException;
	public void saveArticle(Article article) throws ArticleNotSavedException;
	public Article getArticleById(Integer id) throws ArticleNotFoundException;
	public List<Article> getAllArticlesByShopListId(Integer shopListId) throws ShopListNotFoundException;
	

}
