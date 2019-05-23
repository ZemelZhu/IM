package com.im.zemel.pojo;

import com.im.zemel.util.TimeUntil;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "message",indexes = {
        @Index(name = "to_from_id",  columnList="from_id,to_id", unique = false)
})
public class Message {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "int(11) COMMENT 'id'")
    private int id;
    @Column(name = "from_id",columnDefinition = "int(11) COMMENT '发送人id'")
    private int fromId;
    @Column(name = "to_id",columnDefinition = "int(11) COMMENT '接收人id'")
    private int toId;
    @Column(columnDefinition = "tinyint(4) COMMENT '消息状态'")
    private int status;
    @Column(columnDefinition = "varchar(1000) COMMENT '内容'")
    private String content;
    @Column(columnDefinition = "datetime COMMENT '发送时间'")
    private Date time;
    @Column(columnDefinition = "tinyint(4) COMMENT '消息类型'")
    private int type;

    public Message() {
    }

    public Message(int fromId, int toId, String content, int contentType) {
        this.fromId = fromId;
        this.toId = toId;
        this.content = content;
        this.time = TimeUntil.getNowData();
        this.type = contentType;
    }

    public Message(int id, int fromId, int toId, int status, String content, String time) {
        this.id = id;
        this.fromId = fromId;
        this.toId = toId;
        this.status = status;
        this.content = content;
        this.time = TimeUntil.StringToDate(time);

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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
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
        return "Message{" +
                "id=" + id +
                ", fromId=" + fromId +
                ", toId=" + toId +
                ", status=" + status +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", type=" + type +
                '}';
    }
}
