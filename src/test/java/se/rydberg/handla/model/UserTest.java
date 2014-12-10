package se.rydberg.handla.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
    
    User user;
    
    @Before
    public void init(){
        user = new User();
        user.setUsername("erik");
        user.setPassword("lösenord");
        user.setEnabled(true);
    }
    
    @Test
    public void should_return_user_information(){
        assertThat(user.getUsername(), equalTo("erik"));
        assertThat(user.getPassword(), equalTo("lösenord"));
        assertThat(user.isEnabled(),equalTo(true));
    }

}
