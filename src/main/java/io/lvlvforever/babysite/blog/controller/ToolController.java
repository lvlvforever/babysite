package io.lvlvforever.babysite.blog.controller;

import io.lvlvforever.babysite.blog.dao.MessageRepo;
import io.lvlvforever.babysite.blog.model.Message;
import io.lvlvforever.babysite.common.util.CommonRetUtil;
import io.lvlvforever.babysite.common.util.DateUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Created by lvlvforever on 2019/2/21.
 */
@RestController
@RequestMapping("/tool")
public class ToolController {

    @Autowired
    private MessageRepo messageRepo;
    @GetMapping("time2stamp")
    public Map<String, Object> time2stamp(@NotBlank String time) {
        Map<String, Object> map = CommonRetUtil.retSuccess();
        map.put("time", DateUtils.parseDateToTimestampWithDefaultPattern(time));
        return map;
    }
    @GetMapping("stamp2time")
    public Map<String, Object> stamp2time(@NotNull Long stamp) {

        Map<String, Object> map = CommonRetUtil.retSuccess();
        map.put("time", DateUtils.parseTimestamp2DefaultPatter(stamp));
        return map;
    }
    @GetMapping("getMessage")
    public Map<String, Object> getMessage(@NotNull String token) {

        Map<String, Object> map = CommonRetUtil.retNotFound();

        Message message = messageRepo.findByToken(token);
        if (message != null) {
            map = CommonRetUtil.retSuccess();
            map.put("content", message.getContent());
        }
        return map;
    }
    @PostMapping("storeMessage")
    public Map<String, Object> storeMessage(@NotNull String content) {

        Map<String, Object> map = CommonRetUtil.retSuccess();
        boolean flag = false;
        String token;
        do {
            token = RandomStringUtils.randomAlphanumeric(4);
            flag = messageRepo.findOrCreate(token, content);
        } while (!flag);
        map.put("token", token);
        return map;
    }

}
