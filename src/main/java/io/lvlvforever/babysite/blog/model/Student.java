package io.lvlvforever.babysite.blog.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by lvlvforever on 2020/1/5.
 */
@Data
@Document(collection = "student")
public class Student {
    @Id
    @GeneratedValue
    private long id;
    private String name;

}
