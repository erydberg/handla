package se.rydberg.handla.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import se.rydberg.handla.exception.ShopListNotFoundException;
import se.rydberg.handla.exception.ShopListNotSavedException;
import se.rydberg.handla.model.ShopList;
import se.rydberg.handla.model.User;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.notNullValue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/mvc-dispatcher-servlet.xml"})
public class ShopListServiceIT {
    private static final String LISTNAME = "junittest";
    private static final String LISTNAME_WITH_USERS ="junittest med användare";
    

    private Integer shoplistId = 0;
    
    
    @Autowired
    ShopListService shopListService;
    
    @Autowired
    UserService userService;
    
   
    @Test
    public void should_save_a_shop_list_and_delete_it() throws ShopListNotSavedException, ShopListNotFoundException{
        ShopList shoplist = new ShopList();
        shoplist.setName(LISTNAME);
        shopListService.saveShopList(shoplist);
        shoplistId = shoplist.getListId();
        assertTrue(shoplistId>0);
        System.out.println("id på lista " + shoplistId);
        shopListService.deleteShopListById(shoplistId);
    }
    
    @Test
    public void should_get_created_user() throws ShopListNotFoundException, ShopListNotSavedException{
        ShopList shoplist = new ShopList();
        shoplist.setName(LISTNAME);
        shopListService.saveShopList(shoplist);
        shoplistId = shoplist.getListId();
        System.out.println("ska hämta lista med id " + shoplistId);
        ShopList newshoplist = shopListService.getShopListById(shoplistId);
        assertThat(newshoplist.getName(), equalTo(LISTNAME));
    }
    
    @Test
    public void should_update_list_with_article(){
        
    }
    
    @Test
    public void should_save_a_shoplist_with_users() throws ShopListNotSavedException, ShopListNotFoundException{
        
        ShopList shoplist = new ShopList();
        shoplist.setName(LISTNAME_WITH_USERS);
        
        User user1 = new User();
        user1.setUsername("användare1");
        user1.setEnabled(true);
        user1.setPassword("password");
        userService.saveUser(user1);
        
        User user2 = new User();
        user2.setUsername("användare2");
        user2.setEnabled(true);
        user2.setPassword("password");
        userService.saveUser(user2);
        
        List<User> users = new ArrayList<User>();
        users.add(user1);
        users.add(user2);
        shoplist.setShoppers(users);
        shopListService.saveShopList(shoplist);
        
        ShopList shoplistfromdisk = shopListService.getShopListById(shoplist.getListId());
        assertThat(shoplistfromdisk.getShoppers().size(),equalTo(2));
        
        //Remove
        shoplist.removeUser(user1);
        shopListService.saveShopList(shoplist);
        ShopList shoplistfromdisk2 = shopListService.getShopListById(shoplist.getListId());
        assertThat(shoplistfromdisk2.getShoppers().size(),equalTo(1));
        
//        shoplist.rem
        
        shoplist.removeUser(user2);
        shopListService.deleteShopList(shoplist);
        userService.deleteUser(user2);
        userService.deleteUser(user1);
        
    }
 
}
