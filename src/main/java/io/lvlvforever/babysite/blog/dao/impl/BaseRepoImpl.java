package io.lvlvforever.babysite.blog.dao.impl;

import io.lvlvforever.babysite.blog.dao.BaseRepo;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import java.io.Serializable;

/**
 * Created by lvlvforever on 2020/1/5.
 */
public class BaseRepoImpl<T, ID extends Serializable> extends SimpleMongoRepository<T, ID> implements BaseRepo<T, ID> {

    public BaseRepoImpl(MongoEntityInformation<T, ID> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
    }
}
