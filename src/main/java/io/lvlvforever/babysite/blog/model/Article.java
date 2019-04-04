package io.lvlvforever.babysite.blog.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by lvlvforever on 2019/2/16.
 */
@Data
@Document(collection = "article")
public class Article {

    @Id
    private String objectId;
    private Long id;
    private String name;

    private String code;
    private Date date;

    private Collection collection;
    private Set<Tag> tags;




}
