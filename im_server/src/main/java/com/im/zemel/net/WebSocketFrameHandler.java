package com.im.zemel.net;

import com.google.protobuf.InvalidProtocolBufferException;
import com.im.zemel.protoBean.CommonMsgPBOuterClass;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebSocketFrameHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketFrameHandler.class);

    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {
        if (frame instanceof TextWebSocketFrame) {

        } else {
            if ((frame instanceof BinaryWebSocketFrame)) {

                NettyClientConnection conn = (NettyClientConnection) ctx.channel().attr(AttributeKey.valueOf("CLIENT_CON")).get();
                int len = frame.content().readableBytes();
                frame.content().readBytes(conn.getReadBytes(), 0, len);
                @SuppressWarnings("unchecked")
                CommonMsgPBOuterClass.CommonMsgPB commonMessage = CommonMsgPBOuterClass.CommonMsgPB.PARSER.parseFrom(conn.getReadBytes(), 0, len);
                //frame.content().readBytes(conn.getReadBytes());
                // CommonMsgPBOuterClass.CommonMsgPB commonMessage = CommonMsgPBOuterClass.CommonMsgPB.parseFrom
                commonMessage.getContent();
                // 处理消息
                conn.process(conn, commonMessage);
            }
        }

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        NettyClientConnection conn = new NettyClientConnection(ctx.channel(), 40960);
        ctx.channel().attr(AttributeKey.valueOf("CLIENT_CON")).set(conn);
        logger.error("init websocket " + conn);

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        super.channelInactive(ctx);
        NettyClientConnection conn = (NettyClientConnection) ctx.channel().attr(AttributeKey.valueOf("CLIENT_CON")).get();
        if(conn!=null)
            conn.disconnect();
        System.out.println("disconnect" + ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("心跳");
    }
}
