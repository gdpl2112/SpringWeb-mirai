package io.github.kloping.springwebmirai.entity;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.experimental.Accessors;
import net.mamoe.mirai.message.code.MiraiCode;
import net.mamoe.mirai.message.data.MessageChain;

@Data
@Accessors(chain = true)
public class GroupEvent {
    private long botId;
    private int serId;
    private long from;
    private long sender;
    private String mess;
    private String messageCode;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public MessageChain toMessageChain() {
        return MiraiCode.deserializeMiraiCode(messageCode);
    }
}
