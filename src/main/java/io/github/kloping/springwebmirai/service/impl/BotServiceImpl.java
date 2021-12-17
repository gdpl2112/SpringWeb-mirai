package io.github.kloping.springwebmirai.service.impl;

import io.github.kloping.springwebmirai.MiraiLauncherConfiguration;
import io.github.kloping.springwebmirai.entity.BotDetail;
import io.github.kloping.springwebmirai.entity.BotList;
import io.github.kloping.springwebmirai.service.IBotService;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.contact.Group;
import org.springframework.stereotype.Service;

@Service
public class BotServiceImpl implements IBotService {
    @Override
    public BotList getBotList() {
        BotList botList = new BotList();
        for (long q : MiraiLauncherConfiguration.logins.keySet()) {
            Bot bot = Bot.getInstance(q);
            BotList.BotInfo botInfo = new BotList.BotInfo()
                    .setId(q).setNickname(bot.getNick())
                    .setSrc(getSrc(q))
                    .setLoginTime(MiraiLauncherConfiguration.logins.get(q));
            botList.getList().add(botInfo);
        }
        return botList;
    }

    @Override
    public BotDetail getBotDetail(Long id) {
        Bot bot = Bot.getInstance(id);
        BotDetail botDetail = new BotDetail();
        botDetail.setId(id);
        botDetail.setSrc(getSrc(id));
        botDetail.setNickname(bot.getNick());
        botDetail.setLoginTime(MiraiLauncherConfiguration.logins.get(id));
        for (Friend friend : bot.getFriends()) {
            botDetail.getFriends().add(
                    new BotList.BotInfo()
                            .setNickname(friend.getNick())
                            .setId(friend.getId())
                            .setSrc(getSrc(friend.getId()))
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
        return botDetail;
    }

    public static String getSrc(long q) {
        return String.format("https://q1.qlogo.cn/g?b=qq&nk=%s&s=640", q);
    }
}
