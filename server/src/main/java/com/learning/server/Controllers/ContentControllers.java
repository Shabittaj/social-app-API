package com.learning.server.Controllers;

import com.learning.server.Model.Content;
import com.learning.server.Model.User;
import com.learning.server.Response.ApiResponse;
import com.learning.server.Service.ContentService;
import com.learning.server.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/content")
public class ContentControllers {

    @Autowired
    private ContentService contentService;

    @Autowired
    private UserService userService;

    @PostMapping("/create-content")
    public ResponseEntity<Content> createContent(@RequestBody Content content,@RequestHeader("Authorization")String jwt) throws Exception{
        User user = userService.findUserByJwt(jwt);
        Content createdContent= contentService.createContent(content,user.getId());
        return new ResponseEntity<>(createdContent, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-content/{contentId}")
    public ResponseEntity<ApiResponse> deleteContent( @PathVariable Integer contentId,@RequestHeader("Authorization")String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        String message = contentService.deleteContent(contentId,user.getId());
        ApiResponse res = new ApiResponse(message, true);
        return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
    }

    @GetMapping("/all-content")
    public ResponseEntity<List<Content>> findAllContentHandler(){
        List<Content> allContent =  contentService.findAllContent();
        return new ResponseEntity<>(allContent,HttpStatus.ACCEPTED);
    }

    @GetMapping("/user-content")
    public ResponseEntity<List<Content>> findContentByUserId(@RequestHeader("Authorization")String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        List<Content> findContentByUserId = contentService.findContentByUserId(user.getId());
        return new ResponseEntity<>(findContentByUserId, HttpStatus.OK);
    }

    @GetMapping("/{contentId}")
    public ResponseEntity<Content> findContentBycontentID(@PathVariable Integer contentId) throws Exception {
        Content findContentByContentId =  contentService.findContentById(contentId);
        return new ResponseEntity<>(findContentByContentId, HttpStatus.OK);
    }

    @PutMapping("/{contentId}/like")
    public ResponseEntity<Content> likeContent(@PathVariable Integer contentId,@RequestHeader("Authorization")String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Content likeContent = contentService.likeContent(contentId,user.getId());
        return new ResponseEntity<>(likeContent,HttpStatus.ACCEPTED);
    }

    @PutMapping("/{contentId}/comment")
    public ResponseEntity<Content> commentContent(@PathVariable Integer contentId,@RequestHeader("Authorization")String jwt,@RequestBody String message) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Content likeContent = contentService.commmentContent(contentId,user.getId(),message);
        return new ResponseEntity<>(likeContent,HttpStatus.ACCEPTED);
    }


    @PutMapping("/save-content/{contentId}")
    public ResponseEntity<Content> saveContent(@PathVariable Integer contentId,@RequestHeader("Authorization")String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Content saveContent = contentService.saveContent(contentId,user.getId());
        return new ResponseEntity<>(saveContent,HttpStatus.OK);
    }


}
