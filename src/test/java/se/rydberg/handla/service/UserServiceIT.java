package se.rydberg.handla.service;

import static org.hamcrest.CoreMatchers.equalTo;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import se.rydberg.handla.dao.UserDao;
import se.rydberg.handla.exception.ShopListNotSavedException;
import se.rydberg.handla.exception.UserNotFoundException;
import se.rydberg.handla.model.ShopList;
import se.rydberg.handla.model.User;
import static org.junit.Assert.assertThat;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/mvc-dispatcher-servlet.xml"})
public class UserServiceIT {

    
    private static final String USER_NAME = "junittestuser";
    @Autowired
    UserService userService;
    
    
    @Test
    public void should_save_user_and_load_it_again() throws UserNotFoundException{
        User user = new User();
        user.setUsername(USER_NAME);
        user.setPassword("password");
        user.setRole("ROLE_USER");
        user.setEnabled(true);
        userService.saveUser(user);
        System.out.println(user.toString());
        
        User userfromdb = userService.getUser(USER_NAME);
        assertThat(userfromdb.getUsername(), equalTo(USER_NAME));

        
    }
    
    @Test
    public void should_remove_a_test_saved_user(){
        try {
            User userToDelete = userService.getUser(USER_NAME);
            assertThat(userToDelete.getUsername(), equalTo(USER_NAME));
            userService.deleteUser(userToDelete);
           
        } catch (UserNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        //TODO kolla hur man kollar om ett exception kastas på bra sätt
        boolean diceHasbeenThrown = false;
        try {
            User checkForDeletedUser = userService.getUser(USER_NAME);
        } catch (UserNotFoundException e) {
            diceHasbeenThrown = true;
            assertThat(e.toString(),equalTo("se.rydberg.handla.exception.UserNotFoundException"));
        }
        assertThat(diceHasbeenThrown, equalTo(true));
        
        
    }

}
