package com.im.zemel.bean;

import java.util.List;

public class MessageList implements Comparable<MessageList> {
    private int id;
    private String name;
    private String avatar;
    private List<MessageBean> mList;

    public MessageList(Integer uid, String name, String avatar, List<MessageBean> messages) {
        this.id = uid;
        this.name = name;
        this.avatar = avatar;
        this.mList = messages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<MessageBean> getmList() {
        return mList;
    }

    public void setmList(List<MessageBean> mList) {
        this.mList = mList;
    }

    @Override
    public int compareTo(MessageList o) {
        if (o.getmList() == null)
            return -1;
        else if (mList == null)
            return 1;
        if (mList.size() > 0 && o.getmList().size() > 0) {
            String time1 = mList.get(mList.size() - 1).getTime();
            String time2 = o.getmList().get(o.getmList().size() - 1).getTime();
            return time1.compareTo(time2);
        } else {
            return mList.size() - o.getmList().size();
        }
    }
}
