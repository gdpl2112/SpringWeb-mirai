package io.github.kloping.springwebmirai.listeners;

import com.alibaba.fastjson.JSONObject;
import io.github.kloping.arr.ArrSerializer;
import io.github.kloping.springwebmirai.entity.EventResponse;
import io.github.kloping.springwebmirai.entity.GroupEvent;
import io.github.kloping.url.UrlUtils;
import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.PlainText;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;

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

    private static final ArrSerializer arrSerializer = new ArrSerializer();

    static {
        arrSerializer.add(new ArrSerializer.Rule<PlainText>(PlainText.class) {
            @Override
            public String serializer(PlainText o) {
                return o.toString().replaceAll("\\[", "\\[")
                        .replaceAll("]", "\\]");
            }
        });
        arrSerializer.add(new ArrSerializer.Rule<Image>(Image.class) {
            @Override
            public String serializer(Image o) {
                return String.format("[图片:%s]", o.getImageId());
            }
        });
    }

    private static int serIdIndex = 0;

    public static synchronized int getSerId() {
        return ++serIdIndex;
    }

    @EventHandler
    public void onGroupMessage(GroupMessageEvent event) {
        try {
            GroupEvent ge = new GroupEvent();
            String str = arrSerializer.serializer(event.getMessage());
            ge.setMess(str)
                    .setFrom(event.getSubject().getId())
                    .setSender(event.getSender().getId())
                    .setMessageCode(event.getMessage().serializeToMiraiCode())
                    .setBotId(event.getBot().getId())
                    .setSerId(getSerId());

            str = URLEncoder.encode(str);
            String ges = URLEncoder.encode(ge.toString());
            String url = String.format("http://localhost:20044/%s?w=%s", str, ges);
            EventResponse response =
                    JSONObject.parseObject(UrlUtils.getStringFromHttpUrl(url), EventResponse.class);

            if (response.getResponseType().equals("empty")) {
                return;
            } else if (response.getResponseType().equals("text")) {
                event.getSubject().sendMessage(response.getBody().toString());
            }
        } catch (Exception e) {

        }
    }
}
