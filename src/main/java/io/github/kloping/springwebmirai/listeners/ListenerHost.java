package io.github.kloping.springwebmirai.listeners;

import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ListenerHost extends SimpleListenerHost {
    public ListenerHost() {
        super();
    }

    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        super.handleException(context, exception);
    }

    @Autowired
    RestTemplate restTemplate;

    @EventHandler
    public void onGroupMessage(GroupMessageEvent event) {
    }
}
