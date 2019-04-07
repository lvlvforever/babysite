package io.lvlvforever.babysite.common.runner;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by lvlvforever on 2019/4/7.
 */
@Component
public class FakeLog implements CommandLineRunner {

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public void run(String... args) throws Exception {
        String path = "E:\\log\\log.json";
        List<String> lines = FileUtils.readLines(new File(path), "UTF-8");
        for (String line : lines) {
            System.err.println(line);

            if (StringUtils.isBlank(line) || !line.startsWith("{")) {
                continue;
            }
            if (!line.contains("bookGroup")) {
                continue;
            }
            String url = "https://ptactivity.reader.qq.com/activity/sumWithJson?data=";
            String data = URLEncoder.encode(line, "UTF-8");
            url += data;

            String result = restTemplate.getForObject(url, String.class);

            System.err.println(result);
        }


    }
}
