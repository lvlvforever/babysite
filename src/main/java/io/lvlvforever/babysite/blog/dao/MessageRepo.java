package io.lvlvforever.babysite.blog.dao;

import io.lvlvforever.babysite.blog.model.Article;
import io.lvlvforever.babysite.blog.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends MongoRepository<Message,String>,CustomMessageRepo {

    Message findByToken(String token);

}
