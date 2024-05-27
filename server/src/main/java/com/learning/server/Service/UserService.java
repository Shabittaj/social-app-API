package com.learning.server.Service;


import com.learning.server.Model.Content;
import com.learning.server.Model.User;

import java.util.List;

public interface UserService {

    public User registeruser(User user) throws Exception;

    public List<User> fetchUser();

    public User findByUserId(Integer userId) throws Exception;

    public User findByUserEmail(String email) throws Exception;

    public User updateUserDetails(User user, Integer userId) throws Exception;

    public String deleteByUserid(Integer userId) throws Exception;

    public List<User> searchUser(String query);

    public User followUser(Integer id1, Integer id2) throws Exception;

    public List<Content> savedContent(Integer userId);

}
