package io.lvlvforever.babysite.blog.service;

import io.lvlvforever.babysite.blog.model.Article;
import io.lvlvforever.babysite.blog.model.Collection;
import io.lvlvforever.babysite.blog.model.Tag;
import io.lvlvforever.babysite.blog.param.ArticleParam;
import io.lvlvforever.babysite.blog.param.CollectionParam;
import io.lvlvforever.babysite.blog.param.TagParam;
import io.lvlvforever.babysite.blog.vo.ArticleVO;
import io.lvlvforever.babysite.common.util.DateUtils;
import io.lvlvforever.babysite.common.util.ServiceUtil;
import io.lvlvforever.babysite.counter.MongoAutoIdUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lvlvforever on 2019/2/16.
 */
@Service("blogModelService")
public class ModelService {
    @Autowired
    private MongoAutoIdUtil mongoAutoIdUtil;
    public Article parseArticleParam(ArticleParam param) {
        if (param == null) {
            return null;
        }
        Article article = new Article();
        BeanUtils.copyProperties(param, article, ServiceUtil.getNullPropertyNames(param));
        article.setId(mongoAutoIdUtil.getNextSequence(param));
        article.setDate(new Date());
        return article;
    }

    public ArticleVO parseArticle(Article article) {
        ArticleVO vo = new ArticleVO();
        BeanUtils.copyProperties(article, vo, ServiceUtil.getNullPropertyNames(article));
        vo.setDate(DateUtils.parseDateToDefaultPattern(article.getDate()));
        return vo;
    }

    public List<ArticleVO> parseArticles(List<Article> articles) {
        List<ArticleVO> vos = new ArrayList<>();
        if (articles != null) {
            for (Article article : articles) {
                vos.add(parseArticle(article));
            }
        }
        return vos;

    }

    public Tag parseTagParam(TagParam param) {
        if (param == null) {
            return null;
        }
        Tag tag = new Tag();
        BeanUtils.copyProperties(param, tag, ServiceUtil.getNullPropertyNames(param));
        tag.setId(mongoAutoIdUtil.getNextSequence(param));
        return tag;
    }
    public Collection parseCollectionParam(CollectionParam param) {
        if (param == null) {
            return null;
        }
        Collection collection = new Collection();
        BeanUtils.copyProperties(param, collection, ServiceUtil.getNullPropertyNames(param));
        collection.setId(mongoAutoIdUtil.getNextSequence(param));
        return collection;
    }
}
