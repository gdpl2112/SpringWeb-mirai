package io.github.kloping.springwebmirai;

import io.github.kloping.initialize.FileInitializeValue;
import io.github.kloping.springwebmirai.entity.Bots;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.io.File;

@SpringBootApplication
public class SpringWebMiraiApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringWebMiraiApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public String botConfName(Environment env) {
        return env.equals("env") ? "botsConfiguration.json" : "test.json";
    }

    @Bean
    public Bots bots(
            @Autowired
            @Qualifier("botConfName") String name) {
        File file =
                new File(this.getClass().getClassLoader().getResource(name).getFile());
        Bots bots = new Bots();
        bots.getBots().add(
                new Bots.Bot().setId(1111).setPassword("1111")
        );
        bots = FileInitializeValue.getValue(file.getAbsolutePath(), bots);
        return bots;
    }
    /**
     * 这个bean会自动注册使用了@ServerEndpoint注解声明的对象
     * 没有的话会报404
     *
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
