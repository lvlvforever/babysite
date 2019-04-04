package io.lvlvforever.babysite.blog.service;

import io.lvlvforever.babysite.blog.dao.CollectionRepo;
import io.lvlvforever.babysite.blog.dao.TagRepo;
import io.lvlvforever.babysite.blog.model.Collection;
import io.lvlvforever.babysite.blog.model.Tag;
import io.lvlvforever.babysite.blog.param.CollectionParam;
import io.lvlvforever.babysite.blog.param.TagParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lvlvforever on 2019/2/16.
 */
@Service
public class CollectionService {
    @Autowired
    private CollectionRepo repo;
    @Autowired
    private ModelService modelService;

    public String add(CollectionParam param) {
        Collection tag = modelService.parseCollectionParam(param);
        Collection stored = repo.save(tag);
        return stored.getObjectId();
    }

    public void remove(String objectId) {
        repo.deleteById(objectId);
    }

    public List<Collection> list() {
        return repo.findAll();
    }
}
