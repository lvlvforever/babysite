package io.lvlvforever.babysite.blog.model;

import com.sun.javafx.beans.IDProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("message")
@Data
public class Message {
    @Id
    private String id;
    private String token;
    private String content;
    private Date createTime;
}
