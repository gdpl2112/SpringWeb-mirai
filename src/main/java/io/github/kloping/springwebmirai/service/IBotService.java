package io.github.kloping.springwebmirai.service;

import io.github.kloping.springwebmirai.entity.BotDetail;
import io.github.kloping.springwebmirai.entity.BotList;

/**
 * @author github-kloping
 */
public interface IBotService {
    /**
     * Returns all bot No matter what state
     *
     * @return all bot
     */
    BotList getBotList();

    /**
     * To get a bot detail info
     *
     * @param id
     * @return
     */
    BotDetail getBotDetail(Long id);

    /**
     * Make one bot offline
     *
     * @param id bot id
     * @return {@link IBotService#getBotList} can see
     */
    BotList offline(long id);

    /**
     * Make one bot online
     * @param id bot id
     * @return {@link IBotService#getBotList} can see
     */
    BotList online(long id);
}
