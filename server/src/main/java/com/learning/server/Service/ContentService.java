package com.learning.server.Service;


import com.learning.server.Model.Content;


import java.util.List;

public interface ContentService {

    public Content createContent(Content content, Integer userId) throws Exception;

    public String deleteContent(Integer contentId, Integer userId) throws Exception;

    public List<Content> findAllContent();

    public List<Content> findContentByUserId(Integer userId) throws Exception;

    public Content findContentById(Integer contentId) throws Exception;

    public Content likeContent(Integer contentId,Integer userId) throws Exception;

    public Content saveContent(Integer contentId, Integer userId) throws Exception;
}

