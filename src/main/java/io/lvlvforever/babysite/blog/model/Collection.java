package io.lvlvforever.babysite.blog.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by lvlvforever on 2019/2/16.
 */
@Data
@Document(collection = "collection")
public class Collection {
    @Id
    private String objectId;
    private Long id;
    private String name;
}
