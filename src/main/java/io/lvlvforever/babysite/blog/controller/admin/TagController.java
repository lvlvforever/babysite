package io.lvlvforever.babysite.blog.controller.admin;

import io.lvlvforever.babysite.blog.model.Tag;
import io.lvlvforever.babysite.blog.param.TagParam;
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
@RequestMapping("admin/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("addTagView")
    public String addTagView() {
        return "admin/addTag";
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
    @GetMapping("")
    public String tag(Model model) {
        List<Tag> tags = tagService.list();
        model.addAttribute("tags", tags);
        return "admin/tag";
    }

    @ResponseBody
    @GetMapping("list")
    public Map<String, Object> list(Model model) {
        Map<String, Object> map = CommonRetUtil.retSuccess();
        map.put("data", tagService.list());
        return map;
    }


}
