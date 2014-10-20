package se.rydberg.handla.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import se.rydberg.handla.model.ShopList;
import se.rydberg.handla.service.ShopListService;

@RequestMapping("/")
@Controller
public class StartController {
	@Autowired
	private ShopListService shopListService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView start(HttpServletRequest request){
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String name = user.getUsername(); 
	    request.setAttribute("username", name);
	    
	    //Hämta listor
	    List<ShopList> shoplists = shopListService.getAllShopLists();
	    
	    ModelMap map = new ModelMap();
	    map.put("shoplists", shoplists);
	    
		return new ModelAndView("start", map);
	}
	
	
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login() {
 
		return "login";
 
	}
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(HttpServletRequest request) {
 
		request.setAttribute("error", "Felaktigt användarnamn eller lösenord");
		return "login";
	}
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout() {
 
		return "login";
 
	}
}
