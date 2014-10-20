package se.rydberg.handla.controller;

import java.util.Iterator;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;

import se.rydberg.handla.exception.ArticleNotFoundException;
import se.rydberg.handla.exception.ArticleNotSavedException;
import se.rydberg.handla.exception.ShopListNotFoundException;
import se.rydberg.handla.exception.ShopListNotSavedException;
import se.rydberg.handla.model.Article;
import se.rydberg.handla.model.ShopList;
import se.rydberg.handla.service.ArticleService;
import se.rydberg.handla.service.ShopListService;

@RequestMapping("/article")
@Controller
@SessionAttributes("shoplist")
public class ArticleController {

	private static final Logger logger = Logger.getLogger("se.rydberg.handla.controller.ArticleController");

	@Autowired
	private ArticleService articleService;

	@Autowired
	private ShopListService shopListService;

	@RequestMapping(value="/addtoshoplist/{id}",method=RequestMethod.POST)
	public String addArticleToShopList(@ModelAttribute("article")Article article, @PathVariable String id, HttpServletRequest request){
		logger.info("start saveShopList - koppla artikel till lista med id " + id);
//		logger.info("Vi har med shoplist " + shopList.getName());
		String redirectUrl = "/shoplist/";

		if(!article.getTitle().isEmpty()){
//			ShopList shopList = null;
			try {
//				shopList = shopListService.getShopListById(Integer.parseInt(id));
				redirectUrl = "/shoplist/open/" + id;
				articleService.saveArticle(article);
				
				ShopList shopList = shopListService.getShopListById(Integer.parseInt(id));
				shopList.getArticles().add(article);
				shopListService.saveShopList(shopList);

//
//			} catch (NumberFormatException | ShopListNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
			} catch (ArticleNotSavedException | NumberFormatException | ShopListNotFoundException | ShopListNotSavedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}



		logger.info("redirecturl: " + redirectUrl);
		return "redirect:" + redirectUrl;

		////		ModelMap map = new ModelMap();
		//		Article newarticle = new Article();
		////		request.setAttribute("article", newarticle);
		//		request.setAttribute("shoplist", shopList);
		//		logger.info("värde på newarticle: " + newarticle.getId());
		//		logger.info("what about old article? " + article.getId());
		////		map.put("article", article);
		////		map.put("shoplist", shopList);
		//		
		//		return new ModelAndView("viewshoplist","article", newarticle);
		//		

	}

	//markera köpt/oköpt via restanrop
	@RequestMapping(value="/markbought/{id}/{bought}" , method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void markBoughtStatus(@PathVariable String id, @PathVariable String bought){
		logger.info("Entering restanrop markbought med id: " + id + " och ändringen " + bought);
		Integer shopListId = 0;
		try {
			Article article = articleService.getArticleById(Integer.parseInt(id));
			logger.info("found article: " + article.getTitle());
			shopListId = article.getShopList().getListId();
			article.setBought(Boolean.parseBoolean(bought));
			articleService.saveArticle(article);
		} catch (NumberFormatException | ArticleNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ArticleNotSavedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@RequestMapping("/delete/{id}/from/{shoplistid}")
	public String deleteArticle(@PathVariable String id, @PathVariable String shoplistid){
		logger.info("Entering delete article med id: " + id);
		ShopList shopList = null;
		//hitta rätt artikel i shoplist
		try {
			shopList = shopListService.getShopListById(Integer.parseInt(shoplistid));
			logger.info("kollar om jag har min shoplist med mig:" + shopList.getName() + " antal artiklar: " + shopList.getArticles().size());
			Iterator<Article> itt = shopList.getArticles().iterator();
			while(itt.hasNext()){
				Article a = itt.next();
				logger.info("jämför: a.getId().equals(Integer.parseInt(id)" + a.getId() + " " +(Integer.parseInt(id)));
				if(a.getId().equals(Integer.parseInt(id))){
					logger.info("Tar bort artikel: " + a.getTitle());
					itt.remove();
					try {
						shopListService.saveShopList(shopList);
					} catch (ShopListNotSavedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			String redirectUrl = "/shoplist/open/" + shopList.getListId();
			logger.info("redirecturl: " + redirectUrl);
			return "redirect:" + redirectUrl;

		} catch (NumberFormatException | ShopListNotFoundException e1) {
			// Lägga på felmeddelande
			return "redirect:/";
			//			e1.printStackTrace();
		}
		//kanske flytta in i catch
		//		if(shopList==null){
		//			return "redirect:/";
		//		}else{
		//			String redirectUrl = "/shoplist/open/" + shopList.getListId();
		//			logger.info("redirecturl: " + redirectUrl);
		//		    return "redirect:" + redirectUrl;		

	}

}
