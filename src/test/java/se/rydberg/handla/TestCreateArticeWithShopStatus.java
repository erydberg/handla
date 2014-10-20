package se.rydberg.handla;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.Test;

import se.rydberg.handla.model.Article;
import se.rydberg.handla.model.ShopStatus;
import se.rydberg.handla.model.TypeOfArticle;

public class TestCreateArticeWithShopStatus {
	
	@Test
	public void testCreateArticle(){
		String articleName = "testnamn";
		Integer articleId = 12;
		
		Timestamp now = new Timestamp(System.currentTimeMillis());
//		Enum<ShopStatus> status = ShopStatus.HANDLA;
		
		TypeOfArticle type = new TypeOfArticle();
		type.setTypeId(10);
		type.setName("mejeri");
		
		
		Article article = new Article();
		article.setId(articleId);
		article.setTitle(articleName);
		article.setBought(true);
		article.setUpdated((Timestamp) now);
		article.setType(type);
		
		//Test
		assertEquals(article.getId(),articleId);
		assertEquals(article.getTitle(),articleName);
		assertEquals(article.isBought(), true);
		assertEquals(article.getUpdated(), now);
		assertEquals(article.getType().getName(), type.getName());
	}

}
