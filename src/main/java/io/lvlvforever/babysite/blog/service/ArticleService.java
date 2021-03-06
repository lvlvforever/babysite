package io.lvlvforever.babysite.blog.service;

import io.lvlvforever.babysite.blog.dao.ArticleRepo;
import io.lvlvforever.babysite.blog.model.Article;
import io.lvlvforever.babysite.blog.param.ArticleParam;
import io.lvlvforever.babysite.blog.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    public ArticleVO getArticle(String id) {

        Article article = repo.findById(id).orElse(null);
        if (article == null) {
            return null;
        }
        ArticleVO vo = modelService.parseArticle(article);
        return vo;


    }


    public List<ArticleVO> queryArticles() {
        Sort sort = new Sort(Sort.Direction.DESC, "date");
        List<Article> articles = repo.findAll(sort);
        List<ArticleVO> vos = modelService.parseArticles(articles);
        return vos;
    }



}
