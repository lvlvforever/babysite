package io.lvlvforever.babysite.common.runner;

import io.lvlvforever.babysite.blog.model.Message;
import io.lvlvforever.babysite.blog.model.UserFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


/**
 * Created by lvlvforever on 2019/3/16.
 */
@Component
public class MongoIndexEnsurance implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(MongoIndexEnsurance.class);
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public void run(String... args) throws Exception {
//        logger.debug("command line run");
//        mongoTemplate.indexOps(Message.class).ensureIndex(new Index().on("createTime", Sort.Direction.DESC).expire(48, TimeUnit.HOURS));
//        mongoTemplate.indexOps(UserFile.class).ensureIndex(new Index().on("createTime", Sort.Direction.ASC).expire(48, TimeUnit.HOURS));

    }
}
