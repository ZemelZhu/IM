package com.im.zemel.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Vimmone
 * \* Date: 2018/4/14
 * \* Description:
 * \
 */
@Entity
public class CommentUser {
    @Id
    @GeneratedValue
    @Column(columnDefinition="int(11) COMMENT 'id'")
    private  Integer id;
    @Column(columnDefinition="int(11) COMMENT '评论id'")
    private Integer commentId;
    @Column(columnDefinition="int(11) COMMENT '评论的用户id'")
    private Integer userId;

    @Override
    public String toString() {
        return "CommentUser{" +
                "commentId=" + commentId +
                ", userId=" + userId +
                '}';
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}

