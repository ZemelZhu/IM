package com.im.zemel.bean;

import com.im.zemel.pojo.Message;
import com.im.zemel.util.TimeUntil;

import java.util.Date;

public class MessageBean {
    private int id;
    private int fromId;
    private int toId;
    private int status;
    private String content;
    private String time;
    private int type;

    public Message getMessage() {
        Message m = new Message(id, fromId, toId, status, content, time);
        return m;
    }

    public MessageBean(int id, int fromId, int toId, int status, String content, Date time, int type) {
        this.id = id;
        this.fromId = fromId;
        this.toId = toId;
        this.status = status;
        this.content = content;
        this.time = TimeUntil.DateToString(time);
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MessageBean{" +
                "id=" + id +
                ", fromId=" + fromId +
                ", toId=" + toId +
                ", status=" + status +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", type=" + type +
                '}';
    }
}
