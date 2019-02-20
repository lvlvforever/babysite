package io.lvlvforever.babysite.blog.controller;

import io.lvlvforever.babysite.blog.service.ArticleService;
import io.lvlvforever.babysite.blog.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by lvlvforever on 2019/2/17.
 */
@Controller
@RequestMapping("")
public class PageController {
    @Autowired
    private ArticleService articleService;
    @GetMapping("")
    public String index(Model model) {

        List<ArticleVO> articles = articleService.queryArticles();
        model.addAttribute("articles", articles);
        return "front/index";
    }

    @GetMapping("contact")
    public String contact() {
        return "front/contact";
    }
    @GetMapping("tool")
    public String tool() {
        return "tool/tool";
    }
    @GetMapping("tool/timestamp")
    public String timestamp() {
        return "tool/timestamp";
    }
}
