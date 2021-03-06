package io.github.kloping.springwebmirai.sockets;

import com.alibaba.fastjson.JSON;
import io.github.kloping.springwebmirai.service.TerminalConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import static io.github.kloping.springwebmirai.service.TerminalConfig.RECEIVERS;

/**
 * @author github-kloping
 */
@ServerEndpoint(value = "/server_terminal")
@Component
@Slf4j
public class WebSocketServer {
    public WebSocketServer() {
    }

    private static int onlineCount = 0;
    private static Map<Session, CopyOnWriteArraySet<WebSocketServer>> socketMap = new HashMap();
    private static Map<Session, TerminalConfig.Receiver> receiverMap = new ConcurrentHashMap<>();
    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        TerminalConfig.Receiver receiver = new TerminalConfig.Receiver() {
            @Override
            public void onMessage(String line, int color) {
                String t1 = line;
                try {
                    if (line.startsWith("\u001B[32m")) {
                        t1 = line.substring(line.indexOf("m") + 1, line.lastIndexOf("\u001B[m"));
                        color = 1;
                    } else if (line.startsWith("\u001B[")) {
                        t1 = line.substring(line.indexOf("m") + 1, line.lastIndexOf("\u001B[m"));
                        color = 0;
                    }
                } catch (Exception e) {
                }
                TerminalConfig.Line l = new TerminalConfig.Line()
                        .setText(t1);
                if (color == -1) {
                    l.setColor("red");
                } else if (color == 1) {
                    l.setColor("green");
                } else {
                    l.setColor("black");
                }

                sendMessage(JSON.toJSONString(l));
            }
        };
        receiverMap.put(session, receiver);
        RECEIVERS.add(receiver);

    }

    @OnClose
    public void onClose() {
        RECEIVERS.remove(receiverMap.get(session));
        receiverMap.remove(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {

    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("????????????", error);
        error.printStackTrace();
    }

    public void sendMessage(Object obj) {
        try {
            this.session.getBasicRemote().sendText(obj.toString());
        } catch (Exception e) {
            log.error("??????????????????", e);
        }
    }
}


