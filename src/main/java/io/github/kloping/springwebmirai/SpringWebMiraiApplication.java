package io.github.kloping.springwebmirai;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.kloping.springwebmirai.entity.EventResponse;
import io.github.kloping.springwebmirai.entity.GroupEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class SpringWebMiraiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebMiraiApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping(value = {"/test", "/测试"})
    public EventResponse get(String w) {
        GroupEvent arg = JSONObject.parseObject(w, GroupEvent.class);
        EventResponse response = new EventResponse();
        response.setResponseType("text");
        response.setBotId(arg.getBotId());
        response.setSerId(arg.getSerId());
        response.setFrom(arg.getFrom());
        response.setBody("测试成功");
        response.setSender(arg.getSender());
        return response;
    }
}
