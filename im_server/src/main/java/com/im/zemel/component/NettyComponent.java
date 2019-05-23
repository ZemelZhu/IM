package com.im.zemel.component;

import com.im.zemel.inf.IComponent;
import com.im.zemel.net.WebSocketFrameHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class NettyComponent implements IComponent {
    public static final String NAME = "NettyComponent";
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyComponent.class);
    private static final String WEBSOCKET_PATH = "/websocket";
    private EventLoopGroup bossGroup = null;
    private EventLoopGroup workerGroup = null;
    @Override
    public String getName()
    {
        return getClass().getName();
    }


    @PostConstruct
    @Override
    public boolean start() {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        try
        {
            ServerBootstrap bootStrap = new ServerBootstrap();
            bootStrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class);
            acceptorInit(bootStrap);
            bootStrap.bind(5000).sync();

        }
        catch (Exception e)
        {
            LOGGER.error("netty Error:" + e.toString());
            return false;
        }
        finally
        {

        }
        GlobalComponent.getInstance().addComponent(NAME,this);
        return true;
    }

    private void acceptorInit(ServerBootstrap bootStrap) {
        bootStrap.childHandler(new ChannelInitializer<SocketChannel>()
        {

            @Override
            protected void initChannel(SocketChannel ch)
                    throws Exception
            {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("http-codec", new HttpServerCodec());
                pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
                pipeline.addLast("Compression", new WebSocketServerCompressionHandler());
                pipeline.addLast("Protocol",new WebSocketServerProtocolHandler(WEBSOCKET_PATH, null, true));
                pipeline.addLast("handler", new WebSocketFrameHandler());
            }
        });
    }

}
