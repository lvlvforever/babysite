package io.lvlvforever.babysite.blog.controller;

import io.lvlvforever.babysite.blog.dao.MessageRepo;
import io.lvlvforever.babysite.blog.model.Message;
import io.lvlvforever.babysite.common.service.MongoGridFsService;
import io.lvlvforever.babysite.common.util.CommonRetUtil;
import io.lvlvforever.babysite.common.util.DateUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    private MongoGridFsService mongoGridFsService;
    @Autowired
    private MongoDbFactory mongoDbFactory;
    @Value("${server.base.url}")
    private String baseUrl;

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
            token = RandomStringUtils.randomAlphanumeric(5).toLowerCase();
            flag = messageRepo.findOrCreate(token, content);
        } while (!flag);
        map.put("token", token);
        return map;
    }

    @PostMapping("storeFile")
    public Map<String, Object> storeFile(MultipartFile file) {
        Map<String, Object> map;
        try {
            String objectId = mongoGridFsService.storeFileToGridFs(file.getInputStream(), file.getOriginalFilename());
            map = CommonRetUtil.retSuccess();

            String token;
            boolean flag = false;
//            do{
//                token = RandomStringUtils.randomAlphanumeric(4).toLowerCase();
//                flag =
//
//            }while ()



            map.put("", objectId);

            map.put("url", "/common/gridfs/image/" + objectId);
            map.put("baseUrl", baseUrl);
//            map.put("url")
        } catch (Exception e) {
            map = CommonRetUtil.retServerBusy();
        }
        return map;
    }

}
