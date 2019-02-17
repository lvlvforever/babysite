package io.lvlvforever.babysite.blog.controller;

import io.lvlvforever.babysite.blog.param.TagParam;
import io.lvlvforever.babysite.blog.service.TagService;
import io.lvlvforever.babysite.common.util.CommonRetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by lvlvforever on 2019/2/17.
 */
@Controller
@RequestMapping("blog/tag")
public class TagController {

    @Autowired
    private TagService tagService;
    @ResponseBody
    @PostMapping("addTag")
    public Map<String, Object> add(TagParam param) {
        Map<String, Object> map;
        String objId = tagService.add(param);
        map = CommonRetUtil.retSuccess();
        map.put("objId", objId);
        return map;
    }



}
