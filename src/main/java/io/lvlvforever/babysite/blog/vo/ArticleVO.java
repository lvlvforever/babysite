package io.lvlvforever.babysite.blog.vo;

import lombok.Data;

import java.util.List;

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
    private String collection;


    private List<String> tags;




}
