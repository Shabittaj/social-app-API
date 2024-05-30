package com.learning.server.Repository;

import com.learning.server.Model.Chat;
import com.learning.server.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat,Integer> {

    public List<Chat> findByUsersId(Integer userId);

    @Query("select c from Chat c where :user Member of c.users AND :reqUser Member of c.users")
    public Chat findChatByUserId(@Param("reqUser")User reqUser,@Param("user")User user);

}
