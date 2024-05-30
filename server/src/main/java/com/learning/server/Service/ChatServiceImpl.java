package com.learning.server.Service;

import com.learning.server.Model.Chat;
import com.learning.server.Model.User;
import com.learning.server.Repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;


    @Override
    public Chat createChat(User reqUser, User user2) {
        Chat isExists = chatRepository.findChatByUserId(reqUser,user2);
        if(isExists!=null){
            return isExists;
        }
        Chat chat=new Chat();
        chat.getUsers().add(reqUser);
        chat.getUsers().add(user2);
        chat.setTimestamp(LocalDateTime.now());

        return chatRepository.save(chat);
    }

    @Override
    public Chat findChatById(Integer chatId) throws Exception {
        Optional<Chat> chat =  chatRepository.findById(chatId);
        if(chat.isEmpty()){
            throw new Exception("chat not found with id: "+chatId);
        }
        return chat.get();
    }

    @Override
    public List<Chat> findUsersChat(Integer reqUser) {
        List<Chat> usersChat = chatRepository.findByUsersId(reqUser);
        return usersChat;
    }
}
