package com.learning.server.Controllers;

import com.learning.server.Model.Chat;
import com.learning.server.Model.User;
import com.learning.server.Request.CreateChatRequest;
import com.learning.server.Service.ChatService;
import com.learning.server.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
public class ChatControllers {

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;


    @PostMapping("/create-chat")
    public Chat createChat(@RequestHeader("Authorization")String jwt,
                           @RequestBody CreateChatRequest request) throws Exception {
        User user= userService.findUserByJwt(jwt);
        User user1 = userService.findByUserId(request.getUserId());
        Chat chat= chatService.createChat(user,user1);
        return chat;
    }


    @GetMapping("/find-chat")
    public List<Chat> findChatByuserId(@RequestHeader("Authorization")String jwt) throws Exception {
        User user= userService.findUserByJwt(jwt);
        List<Chat> usersChat = chatService.findUsersChat(user.getId());
        return usersChat;
    }

    @GetMapping("/find-chat/id/{id}")
    public Chat findChatById(@RequestHeader("Authorization")String jwt,@PathVariable("id")Integer id) throws Exception {
        User user= userService.findUserByJwt(jwt);
        Chat userChat= chatService.findChatById(id);
        return userChat;
    }
}
