package io.lvlvforever.babysite.blog.dao.impl;

import io.lvlvforever.babysite.blog.dao.CustomUserFileRepo;
import io.lvlvforever.babysite.blog.model.UserFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.querydsl.QuerydslUtils;

import java.util.Date;


/**
 * Created by lvlvforever on 2019/3/16.
 */
public class CustomUserFileRepoImpl implements CustomUserFileRepo {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public boolean findOrCreate(String token, String fileObjectId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("token").is(token));
        Update update = new Update();
        update.set("token", token);
        update.set("fileObjectId", fileObjectId);
        update.set("createTime", new Date());
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.upsert(true);
        options.returnNew(true);
        UserFile file = mongoTemplate.findAndModify(query, update, options, UserFile.class);
        return file != null;
    }
}
