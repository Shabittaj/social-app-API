package com.learning.server.Repository;

import com.learning.server.Model.Content;
import com.learning.server.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {


    public User findByEmail(String email);

    @Query("select u from User u where u.firstName LIKE %:query% OR u.lastName LIKE %:query% OR u.email LIKE %:query%")
    public List<User> searchUser(String query);

//    @Query("SELECT s FROM User s JOIN s.savedContent u WHERE u.id = :userId")
//    public List<Content> findContentByUserId(Integer userId);

//    public List<Content> findBySavedContent(Integer userId);


}
