package com.im.zemel.component;

import com.im.zemel.inf.IComponent;
import com.im.zemel.core.Iplayer;
import com.im.zemel.type.RedisMessageType;
import com.im.zemel.util.RedisServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component
public class UserComponent implements IComponent {
    public static final String NAME = "UserComponent";
    private Map<Integer, Iplayer> users;
    private Map<Integer, Iplayer> offlineUser;
    private Object locker = new Object();
    @Autowired
    private RedisServiceUtil redisServiceUtil;

    @Override
    public String getName() {
        return null;
    }


    @PostConstruct
    @Override
    public boolean start() {
        users = new ConcurrentHashMap<Integer, Iplayer>();
        offlineUser = new ConcurrentHashMap<Integer, Iplayer>();
        GlobalComponent.getInstance().addComponent(NAME, this);
        return false;
    }

    public Iplayer getUser(int id) {
        return users.get(id);
    }

    public void addUser(Iplayer player) {
        synchronized (locker) {
            Iplayer iplayer = users.get(player);
            if (iplayer != null && iplayer != player) {
                player.offline();
                logger.error("玩家下线  playerID:" + player.getId());
            }
            users.put(player.getId(), player);
            redisServiceUtil.setKey(RedisMessageType.LOGIN+player.getId());
            offlineUser.remove(player.getId());
        }
    }

    public void addOffline(Iplayer player) {
        offlineUser.put(player.getId(), player);
        users.remove(player.getId());
        redisServiceUtil.setKey(RedisMessageType.LOGIN + player.getId(), TimeUnit.MINUTES, 1);
    }

    public Map<Integer, Iplayer> getAllPlyser() {
        return users;
    }

    public Map<Integer, Iplayer> getOfflineUser() {
        return offlineUser;
    }


}
