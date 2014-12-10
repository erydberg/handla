package se.rydberg.handla.service;

import java.util.List;

import se.rydberg.handla.exception.UserNotFoundException;
import se.rydberg.handla.model.User;

public interface UserService {
    public User getUserById(Integer id) throws UserNotFoundException;
    public User getUser(String username) throws UserNotFoundException;
    public void deleteUser(User user);
    public void deleteUserByUsername(String username) throws UserNotFoundException;
    public void saveUser(User user);
    public List<User> getAllUsers();
    public void deleteUserById(int id) throws UserNotFoundException;
}
