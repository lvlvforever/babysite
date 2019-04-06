package io.lvlvforever.babysite.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lvlvforever on 2019/2/17.
 */
@Controller
@RequestMapping("admin")
public class AdminController {
    @GetMapping("")
    public String dashboard() {
        return "admin/dashboard";
    }



}
