package io.lvlvforever.babysite.blog.dao;

import io.lvlvforever.babysite.blog.model.Article;
import io.lvlvforever.babysite.blog.model.Collection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lvlvforever on 2019/2/16.
 */
@Repository
public interface CollectionRepo extends MongoRepository<Collection,String> {
}
