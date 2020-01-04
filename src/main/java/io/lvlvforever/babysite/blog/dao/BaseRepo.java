package io.lvlvforever.babysite.blog.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Created by lvlvforever on 2020/1/5.
 */
@NoRepositoryBean
public interface BaseRepo<T, ID extends Serializable> extends MongoRepository<T, ID> {

}
