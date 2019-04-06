package io.lvlvforever.babysite.blog.controller.admin;

import io.lvlvforever.babysite.blog.model.Collection;
import io.lvlvforever.babysite.blog.param.CollectionParam;
import io.lvlvforever.babysite.blog.param.TagParam;
import io.lvlvforever.babysite.blog.service.CollectionService;
import io.lvlvforever.babysite.blog.service.TagService;
import io.lvlvforever.babysite.common.util.CommonRetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by lvlvforever on 2019/2/17.
 */
@Controller
@RequestMapping("admin/collection")
public class CollectionController {

    @Autowired
    private CollectionService service;

    @GetMapping("addCollectionView")
    public String addCollectionView() {
        return "admin/addCollection";
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
    @GetMapping("")
    public String collection(Model model) {
        List<Collection> collections = service.list();
        model.addAttribute("collections", collections);
        return "admin/collection";
    }


}
