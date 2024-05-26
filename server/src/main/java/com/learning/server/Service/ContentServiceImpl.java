package com.learning.server.Service;

import com.learning.server.Model.Content;
import com.learning.server.Model.User;
import com.learning.server.Repository.ContentRepository;
import com.learning.server.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Override
    public Content createContent(Content content, Integer userId) throws Exception {
        User user = userService.findByUserId(userId);
        Content createcontent = new Content();
        createcontent.setCaption(content.getCaption());
        createcontent.setImage(content.getImage());
        createcontent.setVideo(content.getVideo());
        createcontent.setUser(user);

        return contentRepository.save(createcontent);
    }

    @Override
    public String deleteContent(Integer contentId, Integer userId) throws Exception {
        User user = userService.findByUserId(userId);
        Content content = findContentById(contentId);
        // Check if the user is the owner of the content
        if (!content.getUser().getId().equals(userId)) {
            throw new Exception("You can't access others' contents.");
        }
        // Fetch all users who have saved content
        List<User> allUsers = userService.fetchUser();
        // Remove the content from each user's saved content list
        for (User u : allUsers) {
            if (u.getSavedContent().contains(content)) {
                u.getSavedContent().remove(content);
                userRepository.save(u); // Ensure you save the updated user entity
            }
        }
        // Delete the content
        contentRepository.delete(content);
        return "Content deleted successfully for userId: " + userId;
    }


    @Override
    public List<Content> findAllContent() {
        return contentRepository.findAll();
    }

    @Override
    public List<Content> findContentByUserId(Integer userId) throws Exception {
        return contentRepository.findContentByUserId(userId);
    }

    @Override
    public Content findContentById(Integer contentId) throws Exception {
        Optional<Content> content = contentRepository.findById(contentId);
        if(content.isEmpty()){
            throw new Exception("content does not exists with contentId: "+contentId);
        }
        return content.get();    }

    @Override
    public Content likeContent(Integer contentId, Integer userId) throws Exception {
        User user=userService.findByUserId(userId);
        Content content= findContentById(contentId);
        if(content.getLikePost().contains(user)){
            content.getLikePost().remove(user);
        }else{
            content.getLikePost().add(user);
        }

        return contentRepository.save(content);
    }

    @Override
    public Content commmentContent(Integer contentId, Integer userId, String message) throws Exception {
        User user=userService.findByUserId(userId);
        Content content= findContentById(contentId);
        content.getCommentPost().add(user.getId(),message);
//        content.getCommentPost().add(message);

        return contentRepository.save(content);
    }

    @Override
    public Content saveContent(Integer contentId, Integer userId) throws Exception {
        User user=userService.findByUserId(userId);
        Content content= findContentById(contentId);
        if(user.getSavedContent().contains(content)){
            user.getSavedContent().remove(content);
        }else {
            user.getSavedContent().add(content);
        }
        userRepository.save(user);
        return content;

    }
}
