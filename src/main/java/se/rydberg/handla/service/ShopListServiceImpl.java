package se.rydberg.handla.service;

import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.rydberg.handla.dao.ShopListDao;
import se.rydberg.handla.exception.ShopListNotFoundException;
import se.rydberg.handla.exception.ShopListNotSavedException;
import se.rydberg.handla.model.Article;
import se.rydberg.handla.model.ShopList;

@Service
public class ShopListServiceImpl implements ShopListService {

	@Autowired
	private ShopListDao shopListDao;
	
	@Override
	@Transactional
	public void deleteShopList(ShopList shopList) {
		shopListDao.deleteShopList(shopList);
	}

	@Override
	@Transactional
	public void deleteShopListById(Integer id) throws ShopListNotFoundException {
		try {
			ShopList shopList = shopListDao.getShopListById(id);
			this.deleteShopList(shopList);
		} catch (ShopListNotFoundException e) {
			throw new ShopListNotFoundException("Kan inte hitta lista med id: " + id + " för borttag");
		}
	}

	@Override
	@Transactional
	public List<ShopList> getAllShopLists() {
		return shopListDao.getAllShopLists();
	}

	@Override
	@Transactional
	public void saveShopList(ShopList shopList) throws ShopListNotSavedException {
		shopListDao.saveShopList(shopList);
	}
	
	@Override
	@Transactional
	public ShopList getShopListById(Integer id) throws ShopListNotFoundException {
		return shopListDao.getShopListById(id);
	}
	
	@Override
	@Transactional
	public void deleteBoughtArticlesFromShopListWithId(Integer id) throws ShopListNotSavedException{
	    ShopList shoplist;
        try {
            shoplist = getShopListById(id);
            Iterator<Article> itt = shoplist.getArticles().iterator();
            while(itt.hasNext()){
                Article article = itt.next();
                System.out.println("artikel: " + article.getTitle() + " bought: " + article.isBought());
                if(article.isBought()){
                    System.out.println("artikel: " + article.getTitle() + " bought: " + article.isBought());
                    itt.remove();
                }
            }
            saveShopList(shoplist);    
        } catch (ShopListNotFoundException e) {
            //No need to do anything, no shoplist, no articles to remove
        }
	    
	}
}
