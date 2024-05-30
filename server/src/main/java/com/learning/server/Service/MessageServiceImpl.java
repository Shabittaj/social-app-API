package com.learning.server.Service;

import com.learning.server.Model.Chat;
import com.learning.server.Model.Message;
import com.learning.server.Model.User;
import com.learning.server.Repository.ChatRepository;
import com.learning.server.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ChatService chatService;

    @Override
    public Message createMessage(User user, Integer chatId, Message req) throws Exception {

        Chat chat= chatService.findChatById(chatId);
        Message message=new Message();

        message.setChat(chat);
        message.setUser(user);
        message.setContent(req.getContent());
        message.setImage(req.getImage());
        message.setTimestamp(LocalDateTime.now());
        Message saveMessage = messageRepository.save(message);
        chat.getMessages().add(saveMessage);
        chatRepository.save(chat);
        return saveMessage;

    }

    @Override
    public List<Message> findsChatMessages(Integer chatId) throws Exception {
        Chat chat= chatService.findChatById(chatId);
        return messageRepository.findByChatId(chatId);
    }
}
