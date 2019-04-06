package io.lvlvforever.babysite.blog.controller.admin;

import io.lvlvforever.babysite.blog.param.ArticleParam;
import io.lvlvforever.babysite.blog.service.ArticleService;
import io.lvlvforever.babysite.blog.vo.ArticleVO;
import io.lvlvforever.babysite.common.util.CommonRetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by lvlvforever on 2019/2/16.
 */
@Controller("adminArticleController")
@RequestMapping("admin/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @ResponseBody
    @PostMapping("add")
    public Map<String, Object> addArticle(ArticleParam param) {
        Map<String, Object> map = CommonRetUtil.retSuccess();
        String objId = articleService.add(param);
        map.put("objectId", objId);
        return map;
    }

    @GetMapping("addArticleView")
    public String addArticlePre() {
        return "admin/addArticle";
    }


    @GetMapping("")
    public String queryArticles(Model model) {

        List<ArticleVO> articles = articleService.queryArticles();
        model.addAttribute("articles", articles);
        return "admin/articles";
    }

    @ResponseBody
    @DeleteMapping("remove")
    public Map<String, Object> deleteArticle(@RequestParam  String objectId) {
        boolean flag = articleService.delete(objectId);
        Map<String, Object> map = CommonRetUtil.retSuccess();
        return map;
    }
}
