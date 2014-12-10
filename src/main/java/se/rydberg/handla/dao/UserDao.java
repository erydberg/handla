package se.rydberg.handla.dao;

import java.util.List;

import se.rydberg.handla.exception.UserNotFoundException;
import se.rydberg.handla.model.User;

public interface UserDao {
    public void saveUser(User user);
    public void deleteUser(User user);
    public User getUserByName(String username) throws UserNotFoundException;
    public List<User> getAllUsers();
    public User getUserById(Integer id) throws UserNotFoundException;
    

}
