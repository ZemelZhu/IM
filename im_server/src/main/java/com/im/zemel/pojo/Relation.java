package com.im.zemel.pojo;

import javax.persistence.*;

@Entity
@Table(name = "relation",indexes = {
        @Index(name = "uid_fid",  columnList="uid,fid", unique = true)
})
public class Relation{
    @Id
    @GeneratedValue
    @Column(columnDefinition="int(11) COMMENT 'id'")
    private int id;
    @Column(name = "uid",columnDefinition="int(11) COMMENT '用户id'")
    private int uid;
    @Column(name = "fid",columnDefinition="int(11) COMMENT '好友id'")
    private int fid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }
}
