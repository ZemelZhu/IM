package com.im.zemel.configurer;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "net")
public class NetProperties {
    private int port;
    private String websocket;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getWebsocket() {
        return websocket;
    }

    public void setWebsocket(String websocket) {
        this.websocket = websocket;
    }
}
