package com.learning.server.Repository;

import com.learning.server.Model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {

    @Query("select p from Content p where p.user.id = :userId")
    public List<Content> findContentByUserId(Integer userId);

//    @Query("DELETE c, usc FROM Content c LEFT JOIN user_saved_content usc ON c.id = usc.saved_content_id WHERE c.id = :contentId;")
//    public void deleteContentById(Integer contentId);
}

