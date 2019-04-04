package io.lvlvforever.babysite.blog.controller;

import io.lvlvforever.babysite.blog.param.TagParam;
import io.lvlvforever.babysite.blog.service.TagService;
import io.lvlvforever.babysite.common.util.CommonRetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ConcurrentModel;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by lvlvforever on 2019/2/17.
 */
@Controller
@RequestMapping("blog/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("addTagView")
    public String addTagView() {
        return "backend/addTag";
    }


    @ResponseBody
    @PostMapping("add")
    public Map<String, Object> add(TagParam param) {
        Map<String, Object> map;
        String objId = tagService.add(param);
        map = CommonRetUtil.retSuccess();
        map.put("objId", objId);
        return map;
    }
    @ResponseBody
    @DeleteMapping("remove")
    public Map<String, Object> delete(@RequestParam String objectId) {
        Map<String, Object> map = CommonRetUtil.retSuccess();
        tagService.remove(objectId);
        return map;
    }



}
