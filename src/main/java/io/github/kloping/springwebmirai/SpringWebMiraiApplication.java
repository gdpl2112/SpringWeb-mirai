package io.github.kloping.springwebmirai;

import com.alibaba.fastjson.JSON;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.code.MiraiCode;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;
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

    @GetMapping("test")
    public String get(String arg){
        MessageChain message = MiraiCode.deserializeMiraiCode(arg);
        System.out.println(message);
        return ">>>>";
    }
}
