package se.rydberg.handla.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import se.rydberg.handla.exception.ShopListNotFoundException;
import se.rydberg.handla.exception.ShopListNotSavedException;
import se.rydberg.handla.model.Article;
import se.rydberg.handla.model.ShopList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/mvc-dispatcher-servlet.xml"})
public class RemoveBoughtArticlesFromShoplistTest {

    @Autowired
    ShopListService shopListService;

    @Test
    public void should_remove_bought_articles_from_shoplist() throws ShopListNotSavedException, ShopListNotFoundException{
        ShopList shoplist = new ShopList();
        shoplist.setName("Test av borttag av köpta saker");
        Article article1 = new Article();
        article1.setTitle("mjölk");
        article1.setBought(false);

        Article article2 = new Article();
        article2.setTitle("Filmjölk");
        article2.setBought(true);

        Set<Article> articles = new LinkedHashSet<Article>();
        articles.add(article1);
        articles.add(article2);
        shoplist.setArticles(articles);

        shopListService.saveShopList(shoplist);
        System.out.println(shoplist.toString());
        Integer shoplistId = shoplist.getListId();
        System.out.println("sparat listan med id: " + shoplistId);
        assertEquals(2,shoplist.getArticles().size());

        System.out.println("Tar bort köpt från listan med id " + shoplistId);
        shopListService.deleteBoughtArticlesFromShopListWithId(shoplistId);

        ShopList loadedShopList = shopListService.getShopListById(shoplistId);
        System.out.println("loadedShopList: " + loadedShopList.toString());


        assertEquals(1,loadedShopList.getArticles().size());

        shopListService.deleteShopList(loadedShopList);
    }

}

