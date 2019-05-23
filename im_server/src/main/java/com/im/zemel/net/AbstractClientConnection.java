package com.im.zemel.net;

import com.im.zemel.protoBean.CommonMsgPBOuterClass;
import com.im.zemel.core.Iplayer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractClientConnection {
    protected static final Logger logger = LoggerFactory.getLogger(NettyClientConnection.class);

    protected byte[] readBytes;

    private Iplayer holder;

    private Channel channel = null;

    public AbstractClientConnection(Channel channel, int readByteslen) {
        this.channel = channel;
        readBytes = new byte[readByteslen];
    }

    public Iplayer getHolder()
    {
        return holder;
    }

    public void setHolder(Iplayer holder)
    {
        this.holder = holder;
        if (this.holder != null)
        {
            this.holder.setClientConnection(this);
        }
    }
    /*
     * 发送信息
     */
    public void send(Object packet) {
        if (isConnected()) {
            channel.writeAndFlush(packet);
        }
    }

    public boolean isConnected() {
        if (channel != null && channel.isActive()) {
            return true;
        } else {
            return false;
        }
    }

    public void closeConnection() {
        if (isConnected()) {
            sendClose();
            channel.close();
        }
    }

    private void sendClose() {
        CommonMsgPBOuterClass.CommonMsgPB.Builder me = CommonMsgPBOuterClass.CommonMsgPB.newBuilder();
        me.setType(CommonMsgPBOuterClass.MessageType.Error);
        sendPacket(me);
    }

    public void sendPacket(byte[] message) {
        ByteBuf buf = Unpooled.wrappedBuffer(message);
        send(new BinaryWebSocketFrame(buf));
    }

    public void sendPacket(CommonMsgPBOuterClass.CommonMsgPB.Builder commonMessage) {
        sendPacket(commonMessage.build().toByteArray());
    }

    public void sendPacket(CommonMsgPBOuterClass.CommonMsgPB msgPB) {
        sendPacket(msgPB.toByteArray());
    }

    public void disconnect() {
        if(holder!=null)
            holder.disconnect();
    }
}
