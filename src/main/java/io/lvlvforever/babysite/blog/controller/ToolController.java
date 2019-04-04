package io.lvlvforever.babysite.blog.controller;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSFile;
import io.lvlvforever.babysite.blog.dao.MessageRepo;
import io.lvlvforever.babysite.blog.model.Message;
import io.lvlvforever.babysite.blog.model.UserFile;
import io.lvlvforever.babysite.blog.service.UserFileService;
import io.lvlvforever.babysite.common.service.MongoGridFsService;
import io.lvlvforever.babysite.common.util.CommonRetUtil;
import io.lvlvforever.babysite.common.util.DateUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Map;

/**
 * Created by lvlvforever on 2019/2/21.
 */
@Controller
@RequestMapping("/tool")
public class ToolController {
    @Autowired
    private MongoGridFsService mongoGridFsService;
    @Autowired
    private UserFileService userFileService;

    @Autowired
    private MongoDbFactory mongoDbFactory;
    @Value("${server.base.url}")
    private String baseUrl;

    @Autowired
    private MessageRepo messageRepo;
    @ResponseBody
    @GetMapping("time2stamp")
    public Map<String, Object> time2stamp(@NotBlank String time) {
        Map<String, Object> map = CommonRetUtil.retSuccess();
        map.put("time", DateUtils.parseDateToTimestampWithDefaultPattern(time));
        return map;
    }
    @ResponseBody
    @GetMapping("stamp2time")
    public Map<String, Object> stamp2time(@NotNull Long stamp,Integer timeType) {

        Map<String, Object> map = CommonRetUtil.retSuccess();
        String time = timeType == 1 ? DateUtils.parseTimestamp2DefaultPattern(stamp) : DateUtils.parseTimestampInSeconds2DefaultPattern(stamp);

        map.put("time", time);
        return map;
    }
    @GetMapping("getMessage")
    public String getMessage(@NotNull String token, Model model) {


        Message message = messageRepo.findByToken(token);
        if (message != null) {
            model.addAttribute("token", token);
            model.addAttribute("message", message.getContent());
        }
        return "/tool/myMessage";
    }
    @ResponseBody
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
    @ResponseBody
    @PostMapping("storeFile")
    public Map<String, Object> storeFile(MultipartFile file) {
        Map<String, Object> map;
        try {
            String objectId = mongoGridFsService.storeFileToGridFs(file.getInputStream(), file.getOriginalFilename());
            map = CommonRetUtil.retSuccess();

            String token;
            boolean flag = false;
            do {
                token = RandomStringUtils.randomAlphanumeric(4).toLowerCase();
                flag = userFileService.findOrCreate(token, objectId);
                System.err.println(token);
            } while (!flag);
            map.put("token", token);
            map.put("baseUrl", baseUrl);
        } catch (Exception e) {
            map = CommonRetUtil.retServerBusy();
        }
        return map;
    }
    @ResponseBody
    @GetMapping("getFile")
    public void downloadFile(@RequestParam  String token,HttpServletResponse response) {
        UserFile userFile = userFileService.findByToken(token);
        if (userFile == null) {
            return;
        }
        String objectId = userFile.getFileObjectId();
        GridFSFile file = mongoGridFsService.findFileInGridFs(objectId);
        if (file == null) {
            return;
        }
        try (OutputStream out = response.getOutputStream()) {

            String name = file.getFilename();
            response.setContentType("application/octet-stream");
            String downloadName = URLEncoder.encode(name,"UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-Disposition", "attachment;filename*=utf-8'zh_cn'"+downloadName);
            GridFSBucket bucket = GridFSBuckets.create(mongoDbFactory.getDb());
            bucket.downloadToStream(file.getId(), out);
            System.err.println(file.getId()+file.getFilename());
            out.flush();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @ResponseBody
    @GetMapping("basicBase64")
    public Map<String, Object> basicBase64(@RequestParam(required = false) String content, @RequestParam(required = false) String encoded) {
        Map<String, Object> map = CommonRetUtil.retSuccess();
        if (StringUtils.isNotBlank(content)) {
            byte[] result = Base64.getEncoder().encode(content.getBytes());
            map.put("data", new String(result));
        }else{
            byte[] result = Base64.getDecoder().decode(encoded.getBytes());
            map.put("data", new String(result));
        }
        return map;
    }
    @ResponseBody
    @GetMapping("urlSafeBase64")
    public Map<String, Object> urlSafeBase64(@RequestParam(required = false) String content, @RequestParam(required = false) String encoded) {
        Map<String, Object> map = CommonRetUtil.retSuccess();
        if (StringUtils.isNotBlank(content)) {
            byte[] result = Base64.getUrlEncoder().encode(content.getBytes());
            map.put("data", new String(result));
        }else{
            byte[] result = Base64.getUrlDecoder().decode(encoded.getBytes());
            map.put("data", new String(result));
        }
        return map;
    }
    @ResponseBody
    @GetMapping("mimeBase64")
    public Map<String, Object> mimeBase64(@RequestParam(required = false) String content, @RequestParam(required = false) String encoded) {
        Map<String, Object> map = CommonRetUtil.retSuccess();
        if (StringUtils.isNotBlank(content)) {
            byte[] result = Base64.getMimeEncoder().encode(content.getBytes());
            map.put("data", new String(result));
        }else{
            byte[] result = Base64.getMimeDecoder().decode(encoded.getBytes());
            map.put("data", new String(result));
        }
        return map;
    }


}
