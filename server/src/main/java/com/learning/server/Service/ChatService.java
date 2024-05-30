package com.learning.server.Service;


import com.learning.server.Model.Chat;
import com.learning.server.Model.User;

import java.util.List;

public interface ChatService {

    public Chat createChat(User reqUser, User user2);

    public Chat findChatById(Integer chatId) throws Exception;

    public List<Chat> findUsersChat(Integer reqUser);
}
