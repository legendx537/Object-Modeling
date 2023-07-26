package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.crio.jukebox.entities.User;

public class UserRepository implements IUserRepository {

    private final Map<Integer, User> userMap;
    private Integer autoIncrement = 0;

    public UserRepository() {
        userMap = new HashMap<Integer, User>();
    }

    public UserRepository(Map<Integer, User> userMap) {
        this.userMap = userMap;
        this.autoIncrement = userMap.size();
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            autoIncrement++;
            User newUser = new User(autoIncrement, user.getName());
            userMap.put(autoIncrement, newUser);
            return newUser;
        }
        userMap.put(user.getId(), user);
        return user;
    }
    
    @Override
    public Optional<User> findById(String id) {
       Integer userId = Integer.parseInt(id);
       return Optional.ofNullable( userMap.get( userId ) );      
    }

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(User entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

 
    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub

    }



}
