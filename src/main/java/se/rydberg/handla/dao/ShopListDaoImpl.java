package se.rydberg.handla.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import se.rydberg.handla.exception.ShopListNotFoundException;
import se.rydberg.handla.model.ShopList;

@Repository
public class ShopListDaoImpl implements ShopListDao {

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public void deleteShopList(ShopList shopList) {
		sessionFactory.getCurrentSession().delete(shopList);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<ShopList> getAllShopLists() {
		List<ShopList> shopLists = sessionFactory.getCurrentSession().createQuery("from ShopList as ls order by ls.name asc").list();
		return shopLists;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ShopList getShopListById(Integer shopListId) throws ShopListNotFoundException {
		ShopList shopList = null;
		
		List<ShopList> shopLists = (List<ShopList>)sessionFactory.getCurrentSession().createQuery("from ShopList ls where ls.listId= :shoplistid").setString("shoplistid", Integer.toString(shopListId)).list();
		if(shopLists==null||shopLists.isEmpty()||shopLists.size()>1){
			throw new ShopListNotFoundException("Hittar inte inköpslistan med id: " + shopListId);
			
		}
		shopList = shopLists.get(0);
		return shopList;
	}

	@Override
	public void saveShopList(ShopList shopList) {
		sessionFactory.getCurrentSession().saveOrUpdate(shopList);
	}
}
