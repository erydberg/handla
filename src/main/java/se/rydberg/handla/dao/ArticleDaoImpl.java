package se.rydberg.handla.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import se.rydberg.handla.exception.ArticleNotFoundException;
import se.rydberg.handla.model.Article;

@Repository
public class ArticleDaoImpl implements ArticleDao {
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public void deleteArticle(Article article) {
		sessionFactory.getCurrentSession().delete(article);
	}

	
	@Override
	public void saveArticle(Article article) {
		sessionFactory.getCurrentSession().saveOrUpdate(article);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Article getArticleById(Integer id) throws ArticleNotFoundException {
		Article article = null;
		
		List<Article> articles = (List<Article>)sessionFactory.getCurrentSession().createQuery("from Article as art where art.id=?").setParameter(0, id).list();
		if(articles==null||articles.isEmpty()||articles.size()>1){
			throw new ArticleNotFoundException("Hittar inte artikeln med id: " + id);
			
		}
		article = articles.get(0);
		return article;
	}

	@Override
	public List<Article> getAllArticles() {
		@SuppressWarnings("unchecked")
		List<Article> articles = sessionFactory.getCurrentSession().createQuery("from Article as art order by art.bought,art.title asc").list();
		return articles;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> getAllArticlesByShopListId(Integer shopListId) {
		List<Article> articles = sessionFactory.getCurrentSession().createQuery("from Article art where art.fk_shoplist=? order by art.title asc").setParameter(0,shopListId).list();
		return articles;
	}
}
