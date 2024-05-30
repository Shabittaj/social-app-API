package com.learning.server.Service;

import com.learning.server.Model.Chat;
import com.learning.server.Model.Message;
import com.learning.server.Model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MessageService {

    public Message createMessage(User user, Integer chatId, Message req) throws Exception;

    public List<Message> findsChatMessages(Integer chatId) throws Exception;
}
