package se.rydberg.handla.service;

import java.util.List;

import se.rydberg.handla.exception.ShopListNotFoundException;
import se.rydberg.handla.exception.ShopListNotSavedException;
import se.rydberg.handla.model.ShopList;
import se.rydberg.handla.model.User;

public interface ShopListService {
	public void deleteShopList(ShopList shopList);
	public void deleteShopListById(Integer id) throws ShopListNotFoundException;
	public List<ShopList> getAllShopLists();
	public void saveShopList(ShopList shopList) throws ShopListNotSavedException;
	public ShopList getShopListById(Integer id) throws ShopListNotFoundException;
}
