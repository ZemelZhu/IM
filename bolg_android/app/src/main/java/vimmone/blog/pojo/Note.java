package vimmone.blog.pojo;

import static android.R.attr.author;

/**
 * @autor Vimmone
 */

public class Note {
    private Integer id;

    private String time;

    private Integer authorId;

    private String title;

    private String abstractContent;

    private Integer articleId;

    private String imgUrl;

    public Note() {
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

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstractContent() {
        return abstractContent;
    }

    public void setAbstractContent(String abstractContent) {
        this.abstractContent = abstractContent;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", abstractContent='" + abstractContent + '\'' +
                ", articleId=" + articleId +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
