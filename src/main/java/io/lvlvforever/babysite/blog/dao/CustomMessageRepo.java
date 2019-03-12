package io.lvlvforever.babysite.blog.dao;

public interface CustomMessageRepo {
    boolean findOrCreate(String token, String content);
}
