package io.lvlvforever.babysite.blog.service;

import io.lvlvforever.babysite.blog.dao.UserFileRepo;
import io.lvlvforever.babysite.blog.model.UserFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lvlvforever on 2019/3/16.
 */
@Service
public class UserFileService {
    @Autowired
    private UserFileRepo userFileRepo;
    public boolean existsByToken(String token) {
        return userFileRepo.existsByToken(token);
    }

    public boolean findOrCreate(String token, String fileObjectId) {
        return userFileRepo.findOrCreate(token, fileObjectId);
    }

    public UserFile findByToken(String token) {
        return userFileRepo.findByToken(token);
    }
}
