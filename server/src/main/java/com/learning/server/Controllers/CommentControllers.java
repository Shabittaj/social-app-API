package com.learning.server.Controllers;

import com.learning.server.Model.Comment;
import com.learning.server.Model.User;
import com.learning.server.Service.CommentService;
import com.learning.server.Service.UserService;
import com.sun.source.doctree.CommentTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentControllers {

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/create-comment/{contentId}")
    public Comment createComment(@RequestBody Comment comment,
                                 @PathVariable Integer contentId,
                                 @RequestHeader("Authorization") String jwt) throws Exception{
        User user=  userService.findUserByJwt(jwt);
        Comment comment1= commentService.createComment(comment,contentId,user.getId());
        return comment1;
    }

    @GetMapping("/{commentId}")
    public Comment findComment(@PathVariable Integer commentId,
                                 @RequestHeader("Authorization") String jwt) throws Exception{
        User user=  userService.findUserByJwt(jwt);
        Comment comment1= commentService.findCommentById(commentId);
        return comment1;
    }

    @PutMapping("/like-comment/{commentId}")
    public Comment likeComment(@PathVariable Integer commentId,
                                 @RequestHeader("Authorization") String jwt) throws Exception{
        User user=  userService.findUserByJwt(jwt);
        Comment comment1= commentService.likeComment(commentId,user.getId());
        return comment1;
    }

}
