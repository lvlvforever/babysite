package io.lvlvforever.babysite.blog.controller.front;

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
@Controller("frontArticleController")
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;


    @GetMapping("")
    public String getArticle(@RequestParam String id,Model model) {
        ArticleVO vo = articleService.getArticle(id);
        if (vo == null) {
            return "error";
        }
        model.addAttribute("article", vo);
        return "front/article";

    }
}
