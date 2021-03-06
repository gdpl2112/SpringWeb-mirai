package io.github.kloping.springwebmirai.service.impl;

import io.github.kloping.springwebmirai.MiraiLauncherConfiguration;
import io.github.kloping.springwebmirai.entity.BotDetail;
import io.github.kloping.springwebmirai.entity.BotList;
import io.github.kloping.springwebmirai.service.IBotService;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.console.command.ConsoleCommandSender;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.contact.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author github-kloping
 */
@Service
public class BotServiceImpl implements IBotService {

    @Override
    public BotList getBotList() {
        BotList botList = new BotList();
        for (long q : MiraiLauncherConfiguration.LOGINS.keySet()) {
            BotList.BotInfo botInfo = new BotList.BotInfo()
                    .setSrc(getSrc(q)).setId(q);
            try {
                Bot bot = Bot.getInstanceOrNull(q);
                botInfo.setNickname(bot.getNick())
                        .setLoginTime(MiraiLauncherConfiguration.LOGINS.get(q))
                        .setOnline(bot.isOnline() ? 1 : 0);
            } catch (Exception e) {
            }
            botList.getList().add(botInfo);
        }
        return botList;
    }

    @Override
    public BotDetail getBotDetail(Long id) {
        Bot bot = Bot.getInstanceOrNull(id);
        BotDetail botDetail = new BotDetail();
        botDetail.setId(id);
        botDetail.setSrc(bot.getAvatarUrl());
        botDetail.setNickname(bot.getNick());
        botDetail.setLoginTime(MiraiLauncherConfiguration.LOGINS.get(id));
        for (Friend friend : bot.getFriends()) {
            botDetail.getFriends().add(
                    new BotList.BotInfo()
                            .setNickname(friend.getNick())
                            .setId(friend.getId())
                            .setSrc(friend.getAvatarUrl())
            );
        }
        for (Group group : bot.getGroups()) {
            botDetail.getGroups().put(
                    group.getId(),
                    new BotDetail.GroupInfo()
                            .setId(group.getId())
                            .setSrc(group.getAvatarUrl())
                            .setNickname(group.getName())
                            .setLevel(group.getBotPermission().getLevel())
                            .setState(group.getBotMuteRemaining())
            );
        }
        botDetail.sort();
        return botDetail;
    }

    public static String getSrc(long q) {
        return String.format("https://q1.qlogo.cn/g?b=qq&nk=%s&s=640", q);
    }

    @Override
    public BotList offline(long id) {
        try {
            if (Bot.getInstanceOrNull(id) != null)
                Bot.getInstance(id).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getBotList();
    }

    @Autowired
    MiraiLauncherConfiguration configuration;

    @Override
    public BotList online(long id) {
        try {
            if (Bot.getInstanceOrNull(id) == null) {
                configuration.reLogin(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getBotList();
    }
}
