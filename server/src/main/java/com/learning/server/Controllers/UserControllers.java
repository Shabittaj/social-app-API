package com.learning.server.Controllers;

import com.learning.server.Model.Content;
import com.learning.server.Model.User;
import com.learning.server.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserControllers  {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) throws Exception {
        return userService.registeruser(user);
    }

    @GetMapping("/")
    public List<User> fetchUser(){
        return userService.fetchUser();
    }

    @GetMapping("/profile")
    public User findUser(@RequestHeader("Authorization") String jwt) throws Exception {
        User user= userService.findUserByJwt(jwt);
        return user;
    }

    @GetMapping("/email/{email}")
    public User findUserByEmail(@PathVariable("email") String email) throws Exception {
        return userService.findByUserEmail(email);
    }

    @PutMapping("/update-details")
    public User updateUserDetails(@RequestBody User user,@RequestHeader("Authorization")String jwt) throws Exception {
        User user1 = userService.findUserByJwt(jwt);
        return userService.updateUserDetails(user,user1.getId());
    }

    @DeleteMapping("/delete/{userId}")
    public String deleteUserById(@RequestHeader("Authorization")String jwt) throws Exception {
        User user= userService.findUserByJwt(jwt);
        return userService.deleteByUserid(user.getId());
    }

    @GetMapping("/search")
    public List<User> searchUser(@RequestParam("query") String query){
        List<User> users = userService.searchUser(query);
        return users;
    }

    @PutMapping("/follow/{id1}")
    public User followUser(@PathVariable("id1") Integer id1,@RequestHeader("Authorization")String jwt ) throws Exception {
        User user =userService.findUserByJwt(jwt);
        return userService.followUser(user.getId(),id1);
    }

    @GetMapping("/save-content")
    public List<Content> findContentByUserId(@RequestHeader("Authorization")String jwt) throws Exception {
        User user= userService.findUserByJwt(jwt);
        return userService.savedContent(user.getId());
    }

}
