package se.rydberg.handla.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.rydberg.handla.dao.ArticleDao;
import se.rydberg.handla.exception.ArticleNotDeletedException;
import se.rydberg.handla.exception.ArticleNotFoundException;
import se.rydberg.handla.exception.ArticleNotSavedException;
import se.rydberg.handla.exception.ShopListNotFoundException;
import se.rydberg.handla.model.AnalyzeCentence;
import se.rydberg.handla.model.Article;
import se.rydberg.handla.model.TitleDetail;

@Service
public class ArticleServiceImpl implements ArticleService {

	private ArticleDao articleDao;
	
	@Autowired
	public ArticleServiceImpl(ArticleDao articleDao){
		this.articleDao = articleDao;
	}
	
	@Override
	@Transactional
	public void deleteArticle(Article article)
			throws ArticleNotDeletedException {
		articleDao.deleteArticle(article);

	}

	@Override
	@Transactional
	public void deleteArticleById(Integer id) throws ArticleNotDeletedException {
		
		try {
			Article articleToDelete = articleDao.getArticleById(id);
			deleteArticle(articleToDelete);
		} catch (ArticleNotFoundException e) {
			e.printStackTrace();
			throw new ArticleNotDeletedException("Kunde inte hitta och ta bort artikeln med id: " + id);
		}
	}

	@Override
	@Transactional
	public void saveArticle(Article article) throws ArticleNotSavedException {
	    AnalyzeCentence analyze = new AnalyzeCentence();
	    TitleDetail details = analyze.splitCentence(article.getTitle());
	    article.setTitle(details.getTitle());
	    article.setQuantity(details.getQuantity());
	    article.setUnit(details.getUnit());
		articleDao.saveArticle(article);

	}

	@Override
	@Transactional
	public Article getArticleById(Integer id) throws ArticleNotFoundException {
		return articleDao.getArticleById(id);
		
	}

	@Override
	@Transactional
	public List<Article> getAllArticlesByShopListId(Integer shopListId)
			throws ShopListNotFoundException {
		return articleDao.getAllArticlesByShopListId(shopListId);
	}

}
