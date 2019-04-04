package io.lvlvforever.babysite.blog.service;

import io.lvlvforever.babysite.blog.dao.ArticleRepo;
import io.lvlvforever.babysite.blog.dao.TagRepo;
import io.lvlvforever.babysite.blog.model.Tag;
import io.lvlvforever.babysite.blog.param.TagParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.util.List;

/**
 * Created by lvlvforever on 2019/2/16.
 */
@Service("tagService")
public class TagService {
    @Autowired
    private TagRepo repo;
    @Autowired
    private ModelService modelService;

    public String add(TagParam param) {
        Tag tag = modelService.parseTagParam(param);
        Tag stored = repo.save(tag);
        return stored.getObjectId();
    }

    public void remove(String objectId) {
        repo.deleteById(objectId);
    }

    public List<Tag> list() {
        return repo.findAll();
    }

}
