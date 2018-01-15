package se.rydberg.handla;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import se.rydberg.handla.service.ShopListService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/mvc-dispatcher-servlet.xml"})
public class TestAutowireCanBeRemoved {
    
    @Autowired
    @Qualifier("shopListService")
    ShopListService shopListService;
    
    @Test
    public void shouldAutoWire(){
        assertNotNull(shopListService);
    }
}
