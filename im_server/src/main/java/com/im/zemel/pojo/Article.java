package com.im.zemel.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name = "article",indexes = {
        @Index(name = "author_id",  columnList="author", unique = false)
})
public class Article {
    @Id
    @GeneratedValue
    @Column(columnDefinition="int(11) COMMENT 'id'")
    private Integer id;
    @Column(columnDefinition="varchar(100) COMMENT '创建时间'")
    private String time;
    @Column(name = "author",columnDefinition="varchar(100) COMMENT '作者'")
    private Integer author;
    @Column(columnDefinition="varchar(255) COMMENT '标题'")
    private String title;
    @Column(columnDefinition="varchar(20000) COMMENT '内容'")
    private String content;
    @Column(columnDefinition="int(11) COMMENT '点赞数'")
    private Integer viewCount=0;
    @Column(columnDefinition="int(11) COMMENT '评论数'")
    private Integer commentNumber=0;
    @Column(columnDefinition="int(11) COMMENT 'noteID'")
    private Integer NoteId;

    public Integer getNoteId() {
        return NoteId;
    }

    public void setNoteId(Integer noteId) {
        NoteId = noteId;
    }

    public Article() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Integer commentNumber) {
        this.commentNumber = commentNumber;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", author=" + author +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", viewCount=" + viewCount +
                ", commentNumber=" + commentNumber +
                ", NoteId=" + NoteId +
                '}';
    }
}

