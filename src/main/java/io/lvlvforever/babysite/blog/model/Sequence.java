package io.lvlvforever.babysite.blog.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by lvlvforever on 2020/1/5.
 */
@Data
@Document(collection = "sequence")
public class Sequence {
    @Id
    private String id;
    private long seqId;
    private String collName;

}
