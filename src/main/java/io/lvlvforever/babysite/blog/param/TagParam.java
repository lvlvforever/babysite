package io.lvlvforever.babysite.blog.param;

import io.lvlvforever.babysite.counter.AutoIncrementable;
import lombok.Data;

/**
 * Created by lvlvforever on 2019/2/16.
 */
@Data
public class TagParam implements AutoIncrementable {

    private String objectId;
    private Long id;
    private String name;

    @Override
    public String getAutoIncrementField() {
        return "tag";
    }
}
