package io.github.kloping.springwebmirai;

import io.github.kloping.file.FileUtils;
import io.github.kloping.initialize.FileInitializeValue;
import io.github.kloping.springwebmirai.entity.Bots;
import net.mamoe.mirai.utils.BotConfiguration;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.File;

class SpringWebMiraiApplicationTests {

    @Test
    void contextLoads() throws Exception {
        Bots bots = new Bots();
        bots.getBots().add(new Bots.Bot().setId(111).setPassword("111")
                .setProtocol(BotConfiguration.MiraiProtocol.ANDROID_WATCH.name()));
        bots.getBots().add(new Bots.Bot().setId(222).setPassword("2222")
                .setProtocol(BotConfiguration.MiraiProtocol.ANDROID_WATCH.name()));

        FileInitializeValue.putValues("./output.json", bots);
    }
}
