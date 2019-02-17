package io.lvlvforever.babysite.blog.param;

import io.lvlvforever.babysite.counter.AutoIncrementable;
import io.lvlvforever.babysite.counter.CountIndex;
import lombok.Data;

import java.util.Date;

/**
 * Created by lvlvforever on 2019/2/16.
 */
@Data
public class ArticleParam implements AutoIncrementable {

    private String name;
    private String content;

    @Override
    public String getAutoIncrementField() {
        return CountIndex.ARTICLE.toString();
    }

}
