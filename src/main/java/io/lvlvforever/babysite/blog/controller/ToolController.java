package io.lvlvforever.babysite.blog.controller;

import io.lvlvforever.babysite.common.util.CommonRetUtil;
import io.lvlvforever.babysite.common.util.DateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Created by lvlvforever on 2019/2/21.
 */
@RestController
@RequestMapping("/tool")
public class ToolController {

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

}
