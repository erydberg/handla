package se.rydberg.handla.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import se.rydberg.handla.exception.UserNotFoundException;
import se.rydberg.handla.model.User;

@Repository
public class UserDaoImpl implements UserDao {
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void saveUser(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    @Transactional
    public User getUserByName(String username) throws UserNotFoundException {
        User user = null;
//      user = (User)sessionFactory.getCurrentSession().get(User.class,username);        
        @SuppressWarnings("unchecked")
        List<User> users = (List<User>)sessionFactory.getCurrentSession().createQuery("from User ur where ur.username=:username").setString("username", username).list();
        if(users==null||users.isEmpty()){
            throw new UserNotFoundException("Hittar inte användaren med användarnamnet " + username);
        }
        
        user = users.get(0);
        return user;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        @SuppressWarnings("unchecked")
        List<User> users = sessionFactory.getCurrentSession().createQuery("from User as u order by u.username asc").list();
        return users;
    }

    @Override
    @Transactional
    public User getUserById(Integer id) throws UserNotFoundException {
        User user = null;
        user = (User)sessionFactory.getCurrentSession().get(User.class,id);
        if(user==null){
            throw new UserNotFoundException("Hittar inte användaren med id "+ id);
        }
        return user;
    }

}
