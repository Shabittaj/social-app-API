package com.learning.server.Controllers;

import com.learning.server.Model.Content;
import com.learning.server.Model.User;
import com.learning.server.Response.ApiResponse;
import com.learning.server.Service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContentControllers {

    @Autowired
    private ContentService contentService;


    @PostMapping("/content/{userId}/createContent")
    public ResponseEntity<Content> createContent(@RequestBody Content content, @PathVariable Integer userId) throws Exception{
        Content createdContent= contentService.createContent(content,userId);
        return new ResponseEntity<>(createdContent, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/content/{contentId}/delete/{userId}")
    public ResponseEntity<ApiResponse> deleteContent( @PathVariable Integer contentId,@PathVariable Integer userId) throws Exception {
        String message = contentService.deleteContent(contentId,userId);
        ApiResponse res = new ApiResponse(message, true);
        return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
    }

    @GetMapping("/content")
    public ResponseEntity<List<Content>> findAllContentHandler(){
        List<Content> allContent =  contentService.findAllContent();
        return new ResponseEntity<>(allContent,HttpStatus.ACCEPTED);
    }

    @GetMapping("/content/user/{userId}")
    public ResponseEntity<List<Content>> findContentByUserId(@PathVariable Integer userId) throws Exception {
        List<Content> findContentByUserId = contentService.findContentByUserId(userId);
        return new ResponseEntity<>(findContentByUserId, HttpStatus.OK);
    }

    @GetMapping("/content/{contentId}")
    public ResponseEntity<Content> findContentBycontentID(@PathVariable Integer contentId) throws Exception {
        Content findContentByContentId =  contentService.findContentById(contentId);
        return new ResponseEntity<>(findContentByContentId, HttpStatus.OK);
    }

    @PutMapping("/content/{contentId}/like/{userId}")
    public ResponseEntity<Content> likeContent(@PathVariable Integer contentId,@PathVariable Integer userId) throws Exception {
        Content likeContent = contentService.likeContent(contentId,userId);
        return new ResponseEntity<>(likeContent,HttpStatus.ACCEPTED);
    }

    @PutMapping("/content/{contentId}/comment/{userId}")
    public ResponseEntity<Content> commentContent(@PathVariable Integer contentId,@PathVariable Integer userId,@RequestBody String message) throws Exception {
        Content likeContent = contentService.commmentContent(contentId,userId,message);
        return new ResponseEntity<>(likeContent,HttpStatus.ACCEPTED);
    }


    @PutMapping("/content/save/{contentId}/{userId}")
    public ResponseEntity<Content> saveContent(@PathVariable Integer contentId,@PathVariable Integer userId) throws Exception {
        Content saveContent = contentService.saveContent(contentId,userId);
        return new ResponseEntity<>(saveContent,HttpStatus.OK);
    }


}
