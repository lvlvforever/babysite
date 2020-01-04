package io.lvlvforever.babysite.common.config;

import io.lvlvforever.babysite.blog.model.GeneratedValue;
import io.lvlvforever.babysite.blog.model.Sequence;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;

/**
 * Created by lvlvforever on 2020/1/5.
 */
@Configuration
public class SaveMongoEventListener extends AbstractMongoEventListener<Object> {
    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        super.onBeforeConvert(event);

        Object source = event.getSource();
        if (source != null) {
            ReflectionUtils.doWithFields(source.getClass(), (field)->{
                ReflectionUtils.makeAccessible(field);
                if (field.isAnnotationPresent(GeneratedValue.class)) {
                    //设置自增ID
                    field.set(source, getNextId(source.getClass().getSimpleName()));
                }
            });
        }
    }
    /**
     * 获取下一个自增ID
     * @author yinjihuan
     * @param collName  集合名
     * @return
     */
    private Long getNextId(String collName) {
        Query query = new Query(Criteria.where("collName").is(collName));
        Update update = new Update();
        update.inc("seqId", 2);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.upsert(true);
        options.returnNew(true);
        Sequence seqId = mongoTemplate.findAndModify(query, update, options, Sequence.class);
        System.err.println("seqid=" + seqId.getSeqId());

        return seqId.getSeqId();
    }

}
