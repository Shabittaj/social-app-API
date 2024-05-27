package com.learning.server.Controllers;

import com.learning.server.Model.Content;
import com.learning.server.Model.User;
import com.learning.server.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserControllers {

    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    public User registerUser(@RequestBody User user) throws Exception {
        return userService.registeruser(user);
    }

    @GetMapping("/user")
    public List<User> fetchUser(){
        return userService.fetchUser();
    }

    @GetMapping("/user/id/{userId}")
    public User findByUserId(@PathVariable("userId") Integer userId) throws Exception {
        return userService.findByUserId(userId);
    }

    @GetMapping("/user/email/{email}")
    public User findByUserId(@PathVariable("email") String email) throws Exception {
        return userService.findByUserEmail(email);
    }

    @PutMapping("/user/update-details/{userId}")
    public User updateUserDetails(@RequestBody User user, @PathVariable("userId") Integer userId) throws Exception {
        return userService.updateUserDetails(user,userId);
    }

    @DeleteMapping("/user/delete/{userId}")
    public String deleteUserById(@PathVariable("userId") Integer userId) throws Exception {
        return userService.deleteByUserid(userId);
    }

    @GetMapping("/user/search")
    public List<User> searchUser(@RequestParam("query") String query){
        List<User> users = userService.searchUser(query);
        return users;
    }

    @PutMapping("/user/follow/{id1}/{id2}")
    public User followUser(@PathVariable("id1") Integer id1,@PathVariable("id2") Integer id2 ) throws Exception {
        return userService.followUser(id1,id2);
    }

    @GetMapping("/user/save-content/{userId}")
    public List<Content> findContentByUserId(@PathVariable("userId") Integer userId) throws Exception {
        return userService.savedContent(userId);
    }

}
