package com.example.analyticospring.service;

import com.example.analyticospring.entity.Facebook;
import com.example.analyticospring.entity.FacebookHashtag;
import com.example.analyticospring.entity.FacebookPosts;
import com.example.analyticospring.entity.Twitter;
import com.example.analyticospring.repository.FacebookHashtagRepository;
import com.example.analyticospring.service.implementation.FacebookHashtagServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FacebookHashtagService implements FacebookHashtagServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(FacebookHashtagService.class);
    private FacebookHashtagRepository facebookHashtagRepository;

    @Autowired
    public void setFacebookHashtagRepository(FacebookHashtagRepository facebookHashtagRepository) {
        this.facebookHashtagRepository = facebookHashtagRepository;
    }

    public FacebookHashtag addFacebookHashtag(String name, FacebookPosts facebookPosts, Facebook facebook) {
        FacebookHashtag facebookHashtag = new FacebookHashtag();
        facebookHashtag.setName(name);
        facebookHashtag.setPost(facebookPosts);
        facebookHashtag.setFacebook(facebook);
        try {
            facebookHashtag = facebookHashtagRepository.save(facebookHashtag);
            logger.info("{} facebook hashtag detail successfully saved in to database for post {}", name,
                    facebookPosts.getId());
            return facebookHashtag;
        } catch (DataAccessException error) {
            logger.error("Error while saving the facebook hashtag {} for post {}, {}", name, facebookPosts.getId(),
                    error.getLocalizedMessage());
        }
        return null;
    }

    public List<Object[]> getTopHashtagsForFacebook(Facebook facebook) {
        return facebookHashtagRepository.findFacebookHashtagByFacebook(facebook);
    }



}
