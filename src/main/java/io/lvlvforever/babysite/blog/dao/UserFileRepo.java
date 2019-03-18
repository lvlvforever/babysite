package io.lvlvforever.babysite.blog.dao;

import io.lvlvforever.babysite.blog.model.Tag;
import io.lvlvforever.babysite.blog.model.UserFile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lvlvforever on 2019/3/16.
 */
@Repository
public interface UserFileRepo extends MongoRepository<UserFile,String>,CustomUserFileRepo{
    boolean existsByToken(String token);

    UserFile findByToken(String token);
}
