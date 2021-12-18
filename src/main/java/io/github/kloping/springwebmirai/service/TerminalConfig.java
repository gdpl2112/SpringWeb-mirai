package io.github.kloping.springwebmirai.service;


import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Configuration;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Configuration
public class TerminalConfig {
    @Data
    @Accessors(chain = true)
    public static final class Line {
        private String text;
        private String color;
    }

    public static interface Receiver {
        void onMessage(String line,int color);
    }

    public static final Set<Receiver> recs = new CopyOnWriteArraySet<>();

}
