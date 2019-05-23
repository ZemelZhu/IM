package com.im.zemel.pojo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class User{
    @Id
    @Column(columnDefinition="int(11) COMMENT 'id'")
    private Integer uid;
    @Column(columnDefinition="varchar(100) COMMENT '密码'")
    private String password;
    @Column(columnDefinition="varchar(255) COMMENT '名字'")
    private String name;
    @Column(columnDefinition="varchar(255) COMMENT '头像'")
    private String avatar;
    @Column(columnDefinition="varchar(255) COMMENT '状态'")
    private String state;
    @Column(columnDefinition="varchar(100) COMMENT '邮箱'")
    private String email;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", state='" + state + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
