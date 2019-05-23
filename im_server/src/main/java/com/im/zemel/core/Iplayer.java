package com.im.zemel.core;

import com.im.zemel.component.GlobalComponent;
import com.im.zemel.component.UserComponent;
import com.im.zemel.net.AbstractClientConnection;
import com.im.zemel.pojo.PlayerInfo;
import com.im.zemel.pojo.User;
import com.im.zemel.protoBean.CommonMsgPBOuterClass;
import com.im.zemel.task.SelfDrivenTaskQueue;
import com.im.zemel.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class Iplayer {
    protected static final Logger logger = LoggerFactory.getLogger(Iplayer.class);

    private static final int MAX_PING = 10;
    /**
     * 命令队列
     */
    private SelfDrivenTaskQueue<Task> cmdQueue = new SelfDrivenTaskQueue<Task>(
            GlobalComponent.getInstance().getUserCmdThreadPool());

    /**
     * 玩家连接引用
     */
    private AbstractClientConnection clientConn = null;

    /**
     * 玩家基础信息缓存
     */
    private PlayerInfo playerInfo = null;

    private User userInfo;

    private int id;

    private AtomicInteger ping;

    public Iplayer(AbstractClientConnection clientConn) {
        this.clientConn = clientConn;
    }

    public Iplayer() {
        ping = new AtomicInteger(0);
    }

    public <T extends Runnable> void addCommandTask(T task) {
        cmdQueue.add((Task) task);
    }

    public void finishOneCommandTask() {
        cmdQueue.complete();
    }

    public void setClientConnection(AbstractClientConnection conn) {
        this.clientConn = conn;
    }

    public void send(CommonMsgPBOuterClass.CommonMsgPB.Builder commonMessage) {
        ping.set(0);
        clientConn.sendPacket(commonMessage);
    }

    public void send(CommonMsgPBOuterClass.CommonMsgPB msgPB) {
        ping.set(0);
        clientConn.sendPacket(msgPB);
    }

    public void closeConnect() {
        clientConn.closeConnection();
    }

    public void login(User user) {
        if (userInfo == null)
            userInfo = new User();
        userInfo.setUid(user.getUid());
        id = user.getUid();
        getUserComponent().addUser(this);
    }

    public void offline() {

        getUserComponent().addOffline(this);
        this.closeConnect();
    }

    private UserComponent getUserComponent() {
        UserComponent component = (UserComponent) GlobalComponent.getInstance().getComponent(UserComponent.NAME);
        return component;
    }

    public int getId() {
        return id;
    }

    public void ping() {
        ping.getAndIncrement();
        if (ping.intValue() >= MAX_PING) {
            this.offline();
            logger.error("无心跳,下线   playerID:" + id);
        }
    }

    public void resetPing() {
        ping.set(0);
    }

    public void disconnect() {
        getUserComponent().addOffline(this);
        logger.error("playerID:" + id + "   失去连接");
    }
}
