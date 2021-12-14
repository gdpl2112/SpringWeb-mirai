package io.github.kloping.springwebmirai.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

//@Component
//@ConfigurationProperties(prefix = "bots")
@Data
@Accessors(chain = true)
public class Bots {
    @Data
    @Accessors(chain = true)
    public static class Bot {
        private String env="env";
        private long id;
        private String password;
        private String protocol;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Bot bot = (Bot) o;
            return id == bot.id && Objects.equals(env, bot.env) && Objects.equals(password, bot.password) && Objects.equals(protocol, bot.protocol);
        }

        @Override
        public int hashCode() {
            return Objects.hash(env, id, password, protocol);
        }
    }

    private Set<Bot> bots = new CopyOnWriteArraySet<>();


}
