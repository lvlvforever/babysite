package io.lvlvforever.babysite.blog.controller.front;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSFile;
import io.lvlvforever.babysite.blog.dao.MessageRepo;
import io.lvlvforever.babysite.blog.model.Message;
import io.lvlvforever.babysite.blog.model.UserFile;
import io.lvlvforever.babysite.blog.service.ArticleService;
import io.lvlvforever.babysite.blog.service.UserFileService;
import io.lvlvforever.babysite.blog.vo.ArticleVO;
import io.lvlvforever.babysite.common.service.MongoGridFsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by lvlvforever on 2019/2/17.
 */
@Controller
@RequestMapping("")
public class PageController {
    @Autowired
    private MongoGridFsService mongoGridFsService;
    @Autowired
    private UserFileService userFileService;

    @Autowired
    private MongoDbFactory mongoDbFactory;
    @Autowired
    private ArticleService articleService;
    @Value("${server.base.url}")
    private String baseUrl;

    @Autowired
    private MessageRepo messageRepo;
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
        String messageUrl = baseUrl + "/message?token=";
        model.addAttribute("messageUrl", messageUrl);
        model.addAttribute("baseUrl", baseUrl);
        return "tool/message";
    }
    @GetMapping("tool/myfile")
    public String myfile(Model model) {
        String fileUrl = baseUrl + "/file?token=";
        model.addAttribute("fileUrl", fileUrl);
        model.addAttribute("baseUrl", baseUrl);
        return "tool/myfile";
    }
    @GetMapping("tool/base64")
    public String base64() {
        return "tool/base64";
    }


    /**
     * 下载文件
     */
    @ResponseBody
    @GetMapping("file")
    public void downloadFile(@RequestParam String token, HttpServletResponse response) {
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
            String downloadName = URLEncoder.encode(name, "UTF-8").replaceAll("\\+", "%20");
            response.setContentLengthLong(file.getLength());

            response.setHeader("Content-Disposition", "attachment;filename*=utf-8'zh_cn'" + downloadName);
            GridFSBucket bucket = GridFSBuckets.create(mongoDbFactory.getDb());
            bucket.downloadToStream(file.getId(), out);
            System.err.println(file.getId() + file.getFilename());
            out.flush();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @GetMapping("message")
    public String getMessage(@NotNull String token, Model model) {


        Message message = messageRepo.findByToken(token);
        if (message != null) {
            model.addAttribute("token", token);
            model.addAttribute("message", message.getContent());
        }

        return "tool/myMessage";
    }

    @GetMapping("login")
    public String login() {
        return "admin/login";
    }
}
