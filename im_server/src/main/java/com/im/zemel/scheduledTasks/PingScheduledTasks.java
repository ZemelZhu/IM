package com.im.zemel.scheduledTasks;

import com.im.zemel.component.GlobalComponent;
import com.im.zemel.component.UserComponent;
import com.im.zemel.core.Iplayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Map;

@Component
public class PingScheduledTasks {
    static final Logger logger = LoggerFactory.getLogger(PingScheduledTasks.class);
    private  static final SimpleDateFormat dataFromat = new SimpleDateFormat("HH:mm:ss");
    @Scheduled(fixedRate = 10*1000)
    public void reportCurrent(){
        UserComponent component = (UserComponent) GlobalComponent.getInstance().getComponent(UserComponent.NAME);
        Map<Integer, Iplayer> allPlyser = component.getAllPlyser();
        for(Iplayer iplayer:allPlyser.values())
        {
            iplayer.ping();
        }
    }
}
