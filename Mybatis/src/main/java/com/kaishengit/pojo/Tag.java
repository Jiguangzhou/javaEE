package com.kaishengit.pojo;

public class Tag {

    private Integer id;
    private String from;
    private String tagname;
    private Integer userid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", tagname='" + tagname + '\'' +
                ", userid=" + userid +
                '}';
    }
}
