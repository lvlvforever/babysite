
package io.lvlvforever.babysite.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.util.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 定义了公用的返回值
 *
 *
 *
 *
 */
public class CommonRetUtil {
    public static Map<String, Object> retSuccess() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 1);
        map.put("msg", "success");
        return map;
    }
    public static Map<String, Object> retNotLogin() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", -2);
        map.put("msg", "用户未登录");
        return map;
    }

    public static Map<String, Object> retParamInvalid() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", -1);
        map.put("msg", "参数不完整或异常");
        return map;
    }

    public static Map<String, Object> retServerBusy() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", -99);
        map.put("msg", "服务端繁忙，请稍后再试");
        return map;
    }
    public static Map<String, Object> retNotFound() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", -404);
        map.put("msg", "请求资源未找到");
        return map;
    }

    public static void writeResponse(HttpServletResponse response, Map<String, Object> result) {
        if (result == null) {
            return;
        }
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods","GET,POST,PUT,DELETE,HEAD");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Content-Type","application/json; charset=UTF-8");
        try(PrintWriter writer = response.getWriter()){

            ObjectMapper mapper = new ObjectMapper();
            writer.write(mapper.writeValueAsString(result));


            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
