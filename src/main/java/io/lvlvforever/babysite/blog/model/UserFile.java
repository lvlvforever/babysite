package io.lvlvforever.babysite.blog.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by lvlvforever on 2019/3/16.
 */
@Document("message")
@Data
public class UserFile {
    @Id
    private String id;
    private String token;
    private String fileObjectId;
    private Date createTime;
}