package io.github.kloping.springwebmirai.service;


import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Configuration;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author github-kloping
 */
@Configuration
public class TerminalConfig {
    @Data
    @Accessors(chain = true)
    public static final class Line {
        private String text;
        private String color;
    }

    public static interface Receiver {
        /**
         * on console message
         * @param line message line
         * @param color It should be color
         */
        void onMessage(String line,int color);
    }

    /**
     * Console Message Receivers
     */
    public static final Set<Receiver> RECEIVERS = new CopyOnWriteArraySet<>();

}
