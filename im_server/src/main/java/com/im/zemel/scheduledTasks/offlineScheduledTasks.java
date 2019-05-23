package com.im.zemel.scheduledTasks;

import com.im.zemel.component.GlobalComponent;
import com.im.zemel.component.UserComponent;
import com.im.zemel.core.Iplayer;
import com.im.zemel.type.RedisMessageType;
import com.im.zemel.util.RedisServiceUtil;
import com.im.zemel.util.SessionUntil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;

@Component
public class offlineScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(offlineScheduledTasks.class);
    @Autowired
    private RedisServiceUtil redisServiceUtil;

    @Scheduled(fixedRate = 120 * 1000)
    public void reportCurrent() {
        UserComponent component = (UserComponent) GlobalComponent.getInstance().getComponent(UserComponent.NAME);
        Map<Integer, Iplayer> offlineUser = component.getOfflineUser();
        Iterator<Map.Entry<Integer, Iplayer>> iterator = offlineUser.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Iplayer> next = iterator.next();
            Integer uid = next.getKey();
            if (!redisServiceUtil.hasKey(RedisMessageType.LOGIN + uid)) {
                SessionUntil.offline(uid);
                iterator.remove();
                next.getValue().closeConnect();
                logger.error("playerID:"+uid +"  准备清除");
            }
        }

    }
}
