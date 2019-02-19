/**
 * Description:
 * User: daipeng
 * Date: 2019-02-19 16:28
 * Project:BookAtomConsole
 */
package io.lvlvforever.babysite.common.controller;

import io.lvlvforever.babysite.common.service.MongoGridFsService;
import io.lvlvforever.babysite.common.util.CommonRetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("common/gridfs")
public class GridFSFileController {
    @Autowired
    private MongoGridFsService mongoGridFsService;
    @PostMapping("upload")
    public Map<String, Object> uploadFile(MultipartFile file) {

        Map<String, Object> map;
        try {
            String objectId = mongoGridFsService.storeFileToGridFs(file.getInputStream(), file.getOriginalFilename());
            map = CommonRetUtil.retSuccess();
            map.put("objectId", objectId);
        } catch (Exception e) {
            map = CommonRetUtil.retServerBusy();
        }
        return map;
    }
}
