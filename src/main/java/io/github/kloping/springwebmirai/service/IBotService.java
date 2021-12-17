package io.github.kloping.springwebmirai.service;

import io.github.kloping.springwebmirai.entity.BotDetail;
import io.github.kloping.springwebmirai.entity.BotList;

public interface IBotService {
    BotList getBotList();

    BotDetail getBotDetail(Long id);
}
