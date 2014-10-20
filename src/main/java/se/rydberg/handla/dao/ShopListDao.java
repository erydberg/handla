package se.rydberg.handla.dao;

import java.util.List;

import se.rydberg.handla.exception.ShopListNotFoundException;
import se.rydberg.handla.model.ShopList;

public interface ShopListDao {
	public void saveShopList(ShopList shopList);
	public void deleteShopList(ShopList shopList);
	public List<ShopList> getAllShopLists();
	public ShopList getShopListById(Integer shopListId) throws ShopListNotFoundException;

}
