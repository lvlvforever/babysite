package io.lvlvforever.babysite.blog.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by lvlvforever on 2020/1/5.
 */
@Data
@Document(collection = "room")
public class Classroom {
    @Id
    private String id;

    @DBRef
    private List<Student> studentList;

}
