package com.im.zemel.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Vimmone
 * \* Date: 2018/4/13
 * \* Description:
 id, //评论唯一id
 owner_user_id, //发表评论的用户id
 target_user_id, //评论的目标用户id
 content, //评论内容
 likeCount, //该评论被点赞的数量
 created_at, //创建时间
 parent_id, //评论的目标id
 parent_type //评论的目标类型


 */
@Entity
public class Comment {
    @Id
    @GeneratedValue
    @Column(columnDefinition="int(11) COMMENT 'id'")
    private Integer id;
    @Column(columnDefinition="int(11) COMMENT '发表评论的用户id'")
    private Integer ownerUserId;
    @Column(columnDefinition="int(11) COMMENT '评论的目标用户id'")
    private Integer targetUserId;
    @Column(columnDefinition="varchar(20000) COMMENT '评论内容'")
    private String content;
    @Column(columnDefinition="int(11) COMMENT '该评论被点赞的数量'")
    private Integer likeCount=0;
    @Column(columnDefinition="varchar(100) COMMENT '创建时间'")
    private String createdAt;
    @Column(columnDefinition="int(11) COMMENT '评论的目标id'")
    private Integer parentId;
    @Column(columnDefinition="int(11) COMMENT '评论的目标文章'")
    private Integer parentArticle;//评论的文章id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(Integer ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public Integer getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(Integer targetUserId) {
        this.targetUserId = targetUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getParentArticle() {
        return parentArticle;
    }

    public void setParentArticle(Integer parentArticle) {
        this.parentArticle = parentArticle;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", ownerUserId=" + ownerUserId +
                ", targetUserId=" + targetUserId +
                ", content='" + content + '\'' +
                ", likeCount=" + likeCount +
                ", createdAt='" + createdAt + '\'' +
                ", parentId=" + parentId +
                ", parentArticle=" + parentArticle +
                '}';
    }
}

