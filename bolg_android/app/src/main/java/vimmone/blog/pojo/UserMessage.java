package vimmone.blog.pojo;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Vimmone
 * \* Date: 2018/3/24
 * \* Description:
 * \
 */
public class UserMessage {
    private int uid;
    private String name;
    private String avatar;
    private String state;
    private String email;
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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
        return "UserMessage{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", state='" + state + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

