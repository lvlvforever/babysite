package io.lvlvforever.babysite.blog.dao;

/**
 * Created by lvlvforever on 2019/3/16.
 */
public interface CustomUserFileRepo {
    boolean findOrCreate(String token, String content);
}
