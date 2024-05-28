package com.learning.server.Service;

import com.learning.server.Model.Comment;

public interface CommentService {

    public Comment createComment(Comment comment,Integer contentId, Integer userId) throws Exception;

    public Comment findCommentById(Integer commentId) throws Exception;

    public Comment likeComment(Integer commentId,Integer userId) throws Exception;
}
