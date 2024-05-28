package com.learning.server.Service;

import com.learning.server.Model.Comment;
import com.learning.server.Model.Content;
import com.learning.server.Model.User;
import com.learning.server.Repository.CommentRepository;
import com.learning.server.Repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ContentService contentService;

    @Override
    public Comment createComment(Comment comment, Integer contentId, Integer userId) throws Exception {
        User user= userService.findByUserId(userId);
        Content content= contentService.findContentById(contentId);

        comment.setUser(user);
        comment.setComment(comment.getComment());
        comment.setTimestamp(LocalDateTime.now());

        Comment saveComment = commentRepository.save(comment);
        content.getCommentPost().add(saveComment);
        contentRepository.save(content);
        return saveComment;
    }

    @Override
    public Comment findCommentById(Integer commentId) throws Exception {
        Optional<Comment> comment= commentRepository.findById(commentId);
        if(comment.isEmpty()){
            throw new Exception("comment is not available");
        }
        return comment.get();
    }

    @Override
    public Comment likeComment(Integer commentId, Integer userId) throws Exception {
        Comment comment=findCommentById(commentId);
        User user= userService.findByUserId(userId);
        if(comment.getLikes().contains(user)){
            comment.getLikes().remove(user);
            }else {
            comment.getLikes().add(user);
        }
        return commentRepository.save(comment);
    }
}
