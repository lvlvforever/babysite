package io.lvlvforever.babysite.blog.controller;

import io.lvlvforever.babysite.blog.model.Collection;
import io.lvlvforever.babysite.blog.model.Tag;
import io.lvlvforever.babysite.blog.service.ArticleService;
import io.lvlvforever.babysite.blog.service.CollectionService;
import io.lvlvforever.babysite.blog.service.TagService;
import io.lvlvforever.babysite.blog.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
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
    @Autowired
    private TagService tagService;
    @Autowired
    private CollectionService collectionService;


    @Value("${server.base.url}")
    private String baseUrl;



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
    @GetMapping("tool/message")
    public String message(Model model) {
        String messageUrl = baseUrl + "/tool/getMessage?token=";
        model.addAttribute("messageUrl", messageUrl);
        model.addAttribute("baseUrl", baseUrl);
        return "tool/message";
    }
    @GetMapping("tool/myfile")
    public String myfile(Model model) {
        String fileUrl = baseUrl + "/tool/getFile?token=";
        model.addAttribute("fileUrl", fileUrl);
        model.addAttribute("baseUrl", baseUrl);
        return "tool/myfile";
    }
    @GetMapping("tool/base64")
    public String base64() {
        return "tool/base64";
    }


    @GetMapping("tag")
    public String tag(Model model) {
        List<Tag> tags = tagService.list();
        model.addAttribute("tags", tags);
        return "backend/tag";
    }

    @GetMapping("collection")
    public String collection(Model model) {
        List<Collection> collections = collectionService.list();
        model.addAttribute("collections", collections);
        return "backend/collection";
    }
}
