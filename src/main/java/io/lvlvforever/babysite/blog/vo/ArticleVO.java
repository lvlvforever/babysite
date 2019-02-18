package io.lvlvforever.babysite.blog.vo;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * Created by lvlvforever on 2019/2/17.
 */
@Data
public class ArticleVO {
    private String objectId;
    private Long id;
    private String name;

    private String code;
    private String date;
}
