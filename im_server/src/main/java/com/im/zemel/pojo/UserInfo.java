package com.im.zemel.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserInfo {
    @Id
    @Column(columnDefinition="int(11) COMMENT 'id'")
    private Integer uid;
    @Column(columnDefinition="int(11) COMMENT '好友数量'")
    private Integer friendCount;
    @Column(columnDefinition="int(11) COMMENT '文章数量'")
    private Integer noteCount;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getFriendCount() {
        return friendCount;
    }

    public void setFriendCount(Integer friendCount) {
        this.friendCount = friendCount;
    }

    public Integer getNoteCount() {
        return noteCount;
    }

    public void setNoteCount(Integer noteCount) {
        this.noteCount = noteCount;
    }
}
