package io.lvlvforever.babysite.blog.controller;

import io.lvlvforever.babysite.blog.param.CollectionParam;
import io.lvlvforever.babysite.blog.param.TagParam;
import io.lvlvforever.babysite.blog.service.CollectionService;
import io.lvlvforever.babysite.blog.service.TagService;
import io.lvlvforever.babysite.common.util.CommonRetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by lvlvforever on 2019/2/17.
 */
@Controller
@RequestMapping("blog/collection")
public class CollectionController {

    @Autowired
    private CollectionService service;

    @GetMapping("addCollectionView")
    public String addCollectionView() {
        return "backend/addCollection";
    }


    @ResponseBody
    @PostMapping("add")
    public Map<String, Object> add(CollectionParam param) {
        Map<String, Object> map;
        String objId = service.add(param);
        map = CommonRetUtil.retSuccess();
        map.put("objId", objId);
        return map;
    }
    @ResponseBody
    @DeleteMapping("remove")
    public Map<String, Object> delete(@RequestParam String objectId) {
        Map<String, Object> map = CommonRetUtil.retSuccess();
        service.remove(objectId);
        return map;
    }



}
