package se.rydberg.handla;

import static org.mockito.Mockito.mock;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import se.rydberg.handla.dao.ArticleDao;
import se.rydberg.handla.exception.ArticleNotFoundException;
import se.rydberg.handla.exception.ArticleNotSavedException;
import se.rydberg.handla.model.Article;
import se.rydberg.handla.service.ArticleService;
import se.rydberg.handla.service.ArticleServiceImpl;

public class TestArticleService {

	private ArticleDao articleDao;
	private ArticleService articleService;

	@Before
	public void doSetup(){
		articleDao = mock(ArticleDao.class);
		articleService = new ArticleServiceImpl(articleDao);
	}

	@Test
	public void saveSmallArticle() throws ArticleNotSavedException{
		final Article article = new Article();
		article.setTitle("gurka");
		
		Mockito.doAnswer(new Answer<Object>(){
			@Override
			public Object answer(InvocationOnMock invocationOnMock) throws Throwable{
				Object[] args = invocationOnMock.getArguments();
				Article toBeSaved = (Article) args[0];
				toBeSaved.setId(40);
				assertEquals(article.getTitle(), toBeSaved.getTitle());
				assertTrue(toBeSaved.getId()>0);
				return null;
			}

		}).when(articleDao).saveArticle(Matchers.any(Article.class));
		articleService.saveArticle(article);
		//		assertNull(article.getId());
		//		articleService.saveArticle(article);
		//		


		//		when(articleService.saveArticle(article)).thenReturn("saved");
	}
	
	@Test
	public void getArticleById() throws ArticleNotFoundException{
		Integer id = 42;
		String title = "meningen med livet";
		boolean bought = true;
		Article returnArticle = new Article();
		returnArticle.setId(id);
		returnArticle.setTitle(title);
		returnArticle.setBought(bought);
		when(articleService.getArticleById(id)).thenReturn(returnArticle);
		
		Article article = articleService.getArticleById(id);
		assertEquals(article.getId(),returnArticle.getId());
		assertEquals(article.getTitle(), returnArticle.getTitle());
		assertEquals(article.isBought(), returnArticle.isBought());
		
		Mockito.verify(articleDao).getArticleById(id);
		
	}


}
