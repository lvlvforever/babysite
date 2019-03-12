package io.lvlvforever.babysite.blog.dao.impl;

import io.lvlvforever.babysite.blog.dao.CustomMessageRepo;
import io.lvlvforever.babysite.blog.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Date;


public class CustomMessageRepoImpl implements CustomMessageRepo {

    @Autowired
    private MongoTemplate mongoTemplate;



    @Override
    public boolean findOrCreate(String token, String content) {
        Query query = new Query();
        query.addCriteria(Criteria.where("token").is(token));
        Update update = new Update();
        update.set("token", token);
        update.set("content", content);
        update.set("createTime", new Date());
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.upsert(true);
        options.returnNew(true);
        Message message = mongoTemplate.findAndModify(query, update, options, Message.class);

        return message != null;
    }
}
