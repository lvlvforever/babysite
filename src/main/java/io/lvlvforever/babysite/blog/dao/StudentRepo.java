package io.lvlvforever.babysite.blog.dao;

import io.lvlvforever.babysite.blog.model.Message;
import io.lvlvforever.babysite.blog.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lvlvforever on 2020/1/5.
 */
@Repository
public interface StudentRepo extends BaseRepo<Student,Long> {

}
