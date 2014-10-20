package se.rydberg.handla.dao;

import java.util.List;

import se.rydberg.handla.exception.ArticleNotFoundException;
import se.rydberg.handla.model.Article;

public interface ArticleDao {
	public void deleteArticle(Article article);
	public void saveArticle(Article article);
	public Article getArticleById (Integer id) throws ArticleNotFoundException;
	public List<Article> getAllArticles();
	public List<Article> getAllArticlesByShopListId(Integer shopListId);
}
