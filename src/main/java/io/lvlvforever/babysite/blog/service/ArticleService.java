package io.lvlvforever.babysite.blog.service;

import io.lvlvforever.babysite.blog.dao.ArticleRepo;
import io.lvlvforever.babysite.blog.model.Article;
import io.lvlvforever.babysite.blog.param.ArticleParam;
import io.lvlvforever.babysite.blog.param.TagParam;
import io.lvlvforever.babysite.blog.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.util.List;

/**
 * Created by lvlvforever on 2019/2/16.
 */
@Service("articleService")
public class ArticleService {
    @Autowired
    private ArticleRepo repo;
    @Autowired
    private ModelService modelService;

    public String add(ArticleParam param) {
        if (param == null) {
            return null;
        }
        Article article = modelService.parseArticleParam(param);
        Article saved = repo.save(article);
        return saved.getObjectId();
    }

    public boolean delete(String objectId) {
        repo.deleteById(objectId);
        return true;
    }



    public List<ArticleVO> queryArticles() {
        List<Article> articles = repo.findAll();
        List<ArticleVO> vos = modelService.parseArticles(articles);
        return vos;
    }



}
