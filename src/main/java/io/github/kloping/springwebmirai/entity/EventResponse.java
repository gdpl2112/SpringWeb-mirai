package io.github.kloping.springwebmirai.entity;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EventResponse {
    private long botId;
    private long from;
    private long sender;
    private int serId;
    private String responseType = "";
    private Object body;
}
