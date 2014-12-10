package se.rydberg.handla.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;

import se.rydberg.handla.exception.UserNotFoundException;
import se.rydberg.handla.model.User;
import se.rydberg.handla.service.UserService;

@RequestMapping("/user")
@Controller
public class UserController {
    private static final Logger logger = Logger.getLogger("se.rydberg.handla.controller.UserController");
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView listUser(HttpServletRequest request){
        List<User> users = userService.getAllUsers();
        ModelMap map = new ModelMap();
        map.put("users", users);
        return new ModelAndView("listusers",map);
    }
    
    @RequestMapping(value="/new", method=RequestMethod.GET)
    public ModelAndView createUser(){
        User user = new User();
        ModelMap map = new ModelMap();
        map.addAttribute("user",user);
        return new ModelAndView("edituser",map);
    }
    
    @RequestMapping(value="/save", method=RequestMethod.POST)
    public String saveUser(@ModelAttribute("user")User user, HttpServletRequest request){
        System.out.println("användare håller på att sparas: " + user.toString());
        userService.saveUser(user);
        //TODO fixa ett bättre ställe att gå tillbaka till än just start
        
        return "redirect:/user";
    }
    
    @RequestMapping(value="/open/{id}", method=RequestMethod.GET)
    public ModelAndView openUser(@PathVariable String id){
        User user = null;
        try {
            user = userService.getUserById(Integer.parseInt(id));
        } catch (UserNotFoundException e) {

            e.printStackTrace();
        }
        ModelMap map = new ModelMap();
        map.addAttribute("user",user);
        return new ModelAndView("edituser",map);
    }
    
    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public String deleteUser(@PathVariable String id){
        try {
            userService.deleteUserById(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UserNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return "redirect:/user";
    }
}
