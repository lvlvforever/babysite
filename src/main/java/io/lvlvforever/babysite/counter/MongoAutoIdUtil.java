
package io.lvlvforever.babysite.counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Component
public class MongoAutoIdUtil {
    @Autowired
    private MongoTemplate template;

    public Long getNextSequence(String collectionName) {
        MongoSequence seq = template.findAndModify(Query.query(where("_id").is(collectionName)),
                new Update().inc("seq", 1),
                options().upsert(true).returnNew(true),
                MongoSequence.class
        );
        return seq.getSeq();
    }

    public Long getNextSequence(AutoIncrementable param) {
        return getNextSequence(param.getAutoIncrementField());
    }
}

