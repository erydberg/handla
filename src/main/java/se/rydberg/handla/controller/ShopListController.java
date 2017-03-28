package se.rydberg.handla.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import se.rydberg.handla.exception.ShopListNotFoundException;
import se.rydberg.handla.exception.ShopListNotSavedException;
import se.rydberg.handla.model.Article;
import se.rydberg.handla.model.ShopList;
import se.rydberg.handla.service.ShopListService;

@RequestMapping("/shoplist")
@Controller
@SessionAttributes("shoplist")
public class ShopListController {
	
	private static final Logger logger = Logger.getLogger("se.rydberg.handla.controller.ShopListController");
	
	@Autowired
	private ShopListService shopListService;
	
	@RequestMapping(value="/new",method=RequestMethod.GET)
	public ModelAndView newShopList(){
		logger.info("start newShopList");
		ShopList shopList = new ShopList();
		ModelMap map = new ModelMap();
		map.addAttribute("shoplist", shopList);
		return new ModelAndView("editshoplist", map);
	}
	
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveShopList(@ModelAttribute("shoplist")ShopList shopList){
		logger.info("start saveShopList: " + shopList.getName());
		try{
			ShopList shopListOnDisc = shopListService.getShopListById(shopList.getListId());
			shopList.setArticles(shopListOnDisc.getArticles());
		}catch(ShopListNotFoundException e){
			logger.info("Hittar inte redan sparad shoplist med id " + shopList.getName() + " alltså ny lista");
		}catch(Exception e){
			logger.info("annat fel som gör att vi inte hittar listan som kanske ska sparas");
		}
		try {
			shopListService.saveShopList(shopList);
		} catch (ShopListNotSavedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    return "redirect:/";
	}
	
	@RequestMapping(value="/open/{id}",method=RequestMethod.GET)
	public ModelAndView openShopList(@PathVariable String id, ModelMap map, HttpServletRequest request){
		ShopList shopList = null;
		try {
			shopList = shopListService.getShopListById(Integer.parseInt(id));
		} catch (NumberFormatException | ShopListNotFoundException e) {
			e.printStackTrace();
		}
		Article article = new Article();
		map.addAttribute("article", article);
		map.addAttribute("shoplist", shopList);
		logger.info("shoplist into model: " + shopList.getName() + " with no of articles: " + shopList.getArticles().size());
		return new ModelAndView("viewshoplist",map);
	}
	
	/**
	 * Deletes articles from a list that has been marked bought
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteboughtarticlesfromlistid/{id}", method=RequestMethod.GET)
	public String deleteBoughtArticlesFromListWithId(@PathVariable String id){
	    try {
            shopListService.deleteBoughtArticlesFromShopListWithId(Integer.parseInt(id));
        } catch (NumberFormatException | ShopListNotSavedException | ShopListNotFoundException e) {
            e.printStackTrace();
        }
	    return "redirect:/shoplist/open/" + id;
	}

	@RequestMapping(value="/delete/{id}")
	public String deleteShopList(@PathVariable String id){
		try {
			shopListService.deleteShopListById(Integer.parseInt(id));
		} catch (NumberFormatException | ShopListNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 return "redirect:/";
	}
	
	//Method to handle different updates from view shoplist - not implemented yet
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String updateShopList(){
		
		return "";
	}
	
	//via rest - bara i testsyfte just nu
	@RequestMapping(value="/restallshoplists",method=RequestMethod.GET)
	public @ResponseBody List getAllShopList(){
		List<ShopList> shoplists = shopListService.getAllShopLists();
		return shoplists;
	}
}
