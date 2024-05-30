package com.learning.server.Controllers;

import com.learning.server.Model.Message;
import com.learning.server.Model.User;
import com.learning.server.Service.MessageService;
import com.learning.server.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageControllers {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @PostMapping("/chat/{chatId}")
    public Message createMessage(@RequestHeader("Authorization")String jwt, @PathVariable("chatId") Integer chatId,@RequestBody Message req) throws Exception {
        User user= userService.findUserByJwt(jwt);
        Message message= messageService.createMessage(user,chatId,req);
        return message;
    }

    @GetMapping("/chat/{chatId}")
    public List<Message> findMessage(@RequestHeader("Authorization")String jwt,
                                     @PathVariable("chatId") Integer chatId) throws Exception {
        User user= userService.findUserByJwt(jwt);
        List<Message> messages= messageService.findsChatMessages(chatId);
        return messages;
    }


}
